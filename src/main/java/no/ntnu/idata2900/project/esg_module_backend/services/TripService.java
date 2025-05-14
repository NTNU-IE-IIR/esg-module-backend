package no.ntnu.idata2900.project.esg_module_backend.services;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.TripDtoHandler;
import no.ntnu.idata2900.project.esg_module_backend.dtos.DataPointDto;
import no.ntnu.idata2900.project.esg_module_backend.dtos.TripDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripRepository;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataListener;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataSource;
import no.ntnu.idata2900.project.esg_module_backend.utils.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for managing fishing trips. This class handles the lifecycle
 * of trips including starting, stopping, and data collection during the trip.
 * It implements the DataListener interface to receive and process ship data
 * from a data source.
 *
 * @author Group 14
 * @version v0.2.0 (2025.04.22)
 */
@Service
public class TripService implements DataListener {
  private final Logger logger = LoggerFactory.getLogger(TripService.class);
  private final DataSource dataSource;
  private final TripDtoHandler tripDtoHandler;
  private final TripRepository tripRepository;

  /**
   * Constructs a new TripService with the required dependencies.
   *
   * @param dataSource      The data source that provides ship data during trips
   * @param tripDtoHandler The handler for sending boat data to WebSocket clients
   * @param tripRepository  The trip repository for database communication
   */
  @Autowired
  TripService(DataSource dataSource, TripDtoHandler tripDtoHandler,
              TripRepository tripRepository) {
    this.tripRepository = tripRepository;
    //datasource initialization
    this.dataSource = dataSource;
    this.dataSource.setDataListener(this);
    this.restoreActiveTrips();
    this.dataSource.start();

    this.tripDtoHandler = tripDtoHandler;
  }


  /**
   * Finds a trip if it is active. Returns a trip belonging to the registration mark if there is
   * an active one. If there is no active trip, this method returns null.
   *
   * @param registrationMark The registration mark of the ship
   * @return A tripId of type Long if there is an active trip. Null if there is no active trip.
   */
  public Long findTripIdIfActive(String registrationMark) {
    Optional<Trip> trip = tripRepository.findTripByRegistrationMarkAndActiveTrue(registrationMark);
    return trip.map(Trip::getId).orElse(null);
  }

  public List<TripDto> getActiveTripData(Long tripId) {
    Optional<Trip> trip = tripRepository.findById(tripId);

    if (trip.isPresent()) {
      return createTripDtos(trip.get());
    } else {
      throw new IllegalArgumentException("Trip not found with id: " + tripId);
    }
  }

  /**
   * Takes a trip object and maps it to a list of TripDtos.
   *
   * @param trip The trip to create TripDtos from.
   * @return A list of TripDtos, one for each datapoint.
   */
  private List<TripDto> createTripDtos(Trip trip) {
    return trip.getDataPoints().stream().map(dp ->
        new TripDto(new DataPointDto(dp), trip.getFishingSessions(), trip.getFuelConsumed(),
            trip.getTargetFuelConsumed(), trip.getTripDistance())).toList();
  }


  /**
   * Stops a trip with a tripId and saves area and comments to the database.
   *
   * @param tripId   If of the trip the user wants to stop.
   * @param comments The comments the user has given about the trip.
   * @param area     The area the trip was conducted in.
   */
  public void stopTrip(Long tripId, String comments, String area) {
    Trip trip = tripRepository.findById(tripId)
        .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + tripId));
    dataSource.removeClient(trip);
    trip.end();
    trip.setComments(comments);
    trip.setArea(area);
    tripRepository.save(trip);
  }

  /**
   * Starts a new fishing trip. This method creates a new Trip object,
   * initializes it, sets up the data listener, and starts the data source
   * to begin collecting ship data.
   */
  public Long startTrip(String registrationMark, String name) {
    Trip trip = new Trip(name, registrationMark);
    tripRepository.save(trip);
    dataSource.addClient(trip);
    return trip.getId();
  }

  /**
   * This method will deactivate all trips with the given registration mark. This is necessary
   * because sometimes trips will not be stopped properly, and multiple trips can be active at the
   * same time.
   *
   * @param registrationMark The registration mark of the trips to deactivate.
   */
  @Transactional
  public void deactivateTripsByRegMark(String registrationMark) {
    tripRepository.deactivateActiveTripsByRegistrationMark(registrationMark);
  }

  /**
   * Restores all active trips on startup.
   */
  private void restoreActiveTrips() {
    List<Trip> activeTrips = tripRepository.findByActiveTrue();
    List<DataPoint> clients =
        activeTrips.stream().map(trip -> trip.getDataPoints().getLast()).toList();
    dataSource.restoreClients(clients);
  }

  /**
   * Handles ship data received from the data source. This method is called
   * whenever new data is available from the data source.
   * If a trip is active, the data is added to the trip. If a WebSocket
   * connection is established, the data is also sent to the client.
   *
   * @param dp The ship data received from the data source
   */
  @Override
  public void onDataReceived(DataPoint dp) {
    logger.info("Received data point: {}", dp);

    DataPointDto dpDto = new DataPointDto(dp);

    Optional<Trip> trip = tripRepository.findById(dp.getTrip().getId());

    if (trip.isEmpty()) {
      logger.error("Trip not found with id: {}", dp.getTrip().getId());
      throw new IllegalArgumentException("Trip not found with id: " + dp.getTrip().getId());
    }

    Trip updatedTrip = updateTripValues(trip.get());

    TripDto tripDto = new TripDto(dpDto, updatedTrip.getFishingSessions(),
        updatedTrip.getFuelConsumed(), updatedTrip.getTargetFuelConsumed(), updatedTrip.getTripDistance());

    logger.info("TripDto created: {}", tripDto);

    if (tripDtoHandler.isClientConnected(trip.get().getRegistrationMark())) {
      logger.info("client with regMark {} is connected", trip.get().getRegistrationMark());
      tripDtoHandler.sendTripDto(trip.get().getRegistrationMark(), tripDto);
    }
  }

  protected Trip updateTripValues(Trip trip) {
    logger.info("Updating trip values");
    float updatedTripDistance;
    float updatedFuelConsumed;
    float updatedTargetFuelConsumed;
    try {
      DataPoint lastDataPoint = trip.getDataPoints().getLast();
      DataPoint penultimateDataPoint = trip.getDataPoints().get(trip.getDataPoints().size() - 2);
      updatedTripDistance = trip.getTripDistance()
          + Distance.calculateDistance(lastDataPoint.getPos(), penultimateDataPoint.getPos());
      updatedFuelConsumed = trip.getFuelConsumed()
          + lastDataPoint.getVessel().getFuelConsumption().getTotal();
      updatedTargetFuelConsumed = trip.getTargetFuelConsumed()
          + lastDataPoint.getVessel().getTargetFuelConsumption();

      trip.setTripDistance(updatedTripDistance);
      trip.setFuelConsumed(updatedFuelConsumed);
      trip.setTargetFuelConsumed(updatedTargetFuelConsumed);

      tripRepository.save(trip);
      logger.info("Trip values updated");
    } catch (Exception e) {
      logger.error("Error updating trip values: {}", e.getMessage());
    }

    return trip;
  }
}
