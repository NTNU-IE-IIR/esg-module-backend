package no.ntnu.idata2900.project.esg_module_backend.services;

import java.util.List;
import java.util.Optional;

import no.ntnu.idata2900.project.esg_module_backend.BoatDataHandler;
import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripLogRepository;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataListener;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataSource;
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
 * @version v0.1.0 (2025.04.22)
 */
@Service
public class TripService implements DataListener {
  private final Logger logger = LoggerFactory.getLogger(TripService.class);
  private final DataSource dataSource;
  private final BoatDataHandler boatDataHandler;
  private Trip currentTrip;
  private boolean tripActive;

  /**
   * Constructs a new TripService with the required dependencies.
   *
   * @param dataSource      The data source that provides ship data during trips
   * @param boatDataHandler The handler for sending boat data to WebSocket clients
   */
  @Autowired
  TripService(TripLogRepository tripLogRepository, DataSource dataSource, BoatDataHandler boatDataHandler) {
    this.dataSource = dataSource;
    this.boatDataHandler = boatDataHandler;
  }




  public boolean isTripActive() {
    return tripActive;
  }

  public List<ShipDto> getTripDataPoints() {
    if (tripActive) {
      return currentTrip.getShipData();
    } else {
      throw new IllegalStateException("Could not find any data points for current trip");
    }
  }


  /**
   * Stops the current fishing trip. This method stops the data source
   * to end data collection and finalizes the current trip.
   */
  public void stopTrip() {
    dataSource.stop();
    currentTrip.end();
    tripActive = false;
    // Stop trip
  }

  /**
   * Gets the current active trip.
   *
   * @return The current Trip object, or null if no trip is active
   */
  public Trip getCurrentTrip() {
    return currentTrip;
  }

  /**
   * Starts a new fishing trip. This method creates a new Trip object,
   * initializes it, sets up the data listener, and starts the data source
   * to begin collecting ship data.
   */
  public void startTrip() {
    currentTrip = new Trip();
    tripActive = true;
    currentTrip.start();

    dataSource.setDataListener(this);
    dataSource.start();
    // Start trip
    System.out.println("Trip started");
  }

  /**
   * Handles ship data received from the data source. This method is called
   * whenever new data is available from the data source.
   * If a trip is active, the data is added to the trip. If a WebSocket
   * connection is established, the data is also sent to the client.
   *
   * @param data The ship data received from the data source
   */
  @Override
  public void onDataReceived(ShipDto data) {
    System.out.println("Data received: " + data);
    if (tripActive) {
      currentTrip.addShipData(data);
    }
      if (boatDataHandler.isConnected()) {
          boatDataHandler.sendBoatData(data);
      }
  }
}
