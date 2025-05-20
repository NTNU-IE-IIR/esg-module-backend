package no.ntnu.idata2900.project.backend.services;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.backend.TripDtoHandler;
import no.ntnu.idata2900.project.backend.dtos.DataPointDto;
import no.ntnu.idata2900.project.backend.dtos.TripDto;
import no.ntnu.idata2900.project.backend.models.Trip;
import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.repositories.TripRepository;
import no.ntnu.idata2900.project.backend.sources.DataListener;
import no.ntnu.idata2900.project.backend.sources.DataSource;
import no.ntnu.idata2900.project.backend.utils.Distance;
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

  private final TripRepository tripRepository;
  private final TripDtoHandler tripDtoHandler;
  private final DataSource dataSource;

  private final Logger logger = LoggerFactory.getLogger(TripService.class);

  /**
   * Constructs a new TripService with the required dependencies.
   *
   * @param tripRepository The trip repository for database communication
   * @param tripDtoHandler The handler for sending boat data to WebSocket clients
   * @param dataSource     The data source that provides ship data during trips
   */
  @Autowired
  TripService(
      DataSource dataSource,
      TripDtoHandler tripDtoHandler,
      TripRepository tripRepository
  ) {
    this.tripRepository = tripRepository;
    this.tripDtoHandler = tripDtoHandler;
    // Data source initialization
    this.dataSource = dataSource;
    this.dataSource.setDataListener(this);
    this.restoreActiveTrips();
    this.dataSource.start();
  }


  /**
   * Finds a trip if it is active. Returns a trip belonging to the registration mark if there is
   * an active one. If there is no active trip, this method returns null.
   *
   * @param registrationMark The registration mark of the ship
   * @return A tripId of type Long if there is an active trip. Null if there is no active trip.
   */
  public Long findTripIdIfActive(String registrationMark) {
    Optional<Trip> trip =
        this.tripRepository.findTripByRegistrationMarkAndActiveTrue(registrationMark);
    return trip.map(Trip::getId).orElse(null);
  }

  /**
   * Get all data for the trip with the specified ID.
   *
   * @param tripId The specified ID
   * @return Collection containing all trip data
   */
  public List<TripDto> getActiveTripData(Long tripId) {
    Optional<Trip> trip = this.tripRepository.findById(tripId);

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
    Trip trip = this.tripRepository.findById(tripId)
        .orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + tripId));
    this.dataSource.removeClient(trip);
    trip.end();
    trip.setComments(comments);
    trip.setArea(area);
    this.tripRepository.save(trip);
  }

  /**
   * Starts a new fishing trip. This method creates a new Trip object,
   * initializes it, sets up the data listener, and starts the data source
   * to begin collecting ship data.
   */
  public Long startTrip(String registrationMark, String name) {
    Trip trip = new Trip(name, registrationMark);
    this.tripRepository.save(trip);
    this.dataSource.addClient(trip);
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
    this.tripRepository.deactivateActiveTripsByRegistrationMark(registrationMark);
  }

  /**
   * Restores all active trips on startup.
   */
  private void restoreActiveTrips() {
    List<Trip> activeTrips = this.tripRepository.findByActiveTrue();
    List<DataPoint> clients =
        activeTrips.stream().map(trip -> trip.getDataPoints().getLast()).toList();
    this.dataSource.restoreClients(clients);
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
    this.logger.info("Received data point: {}", dp);

    DataPointDto dpDto = new DataPointDto(dp);

    Optional<Trip> trip = this.tripRepository.findById(dp.getTrip().getId());

    if (trip.isEmpty()) {
      this.logger.error("Trip not found with id: {}", dp.getTrip().getId());
      throw new IllegalArgumentException("Trip not found with id: " + dp.getTrip().getId());
    }

    Trip updatedTrip = updateTripValues(trip.get());

    TripDto tripDto = new TripDto(
        dpDto, updatedTrip.getFishingSessions(),
        updatedTrip.getFuelConsumed(),
        updatedTrip.getTargetFuelConsumed(),
        updatedTrip.getTripDistance()
    );

    this.logger.info("TripDto created: {}", tripDto);

    if (this.tripDtoHandler.isClientConnected(trip.get().getRegistrationMark())) {
      this.logger.info("client with regMark {} is connected", trip.get().getRegistrationMark());
      this.tripDtoHandler.sendTripDto(trip.get().getRegistrationMark(), tripDto);
    }
  }

  protected Trip updateTripValues(Trip trip) {
    this.logger.info("Updating trip values");
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

      this.tripRepository.save(trip);
      this.logger.info("Trip values updated");
    } catch (Exception e) {
      this.logger.error("Error updating trip values: {}", e.getMessage());
    }

    return trip;
  }
}
