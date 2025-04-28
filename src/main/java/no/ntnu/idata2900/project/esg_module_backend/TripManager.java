package no.ntnu.idata2900.project.esg_module_backend;

import java.util.List;
import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.springframework.stereotype.Component;

/**
 * Manager class for managing fishing trips. This class is responsible for starting, stopping, and
 * containing a single fishing trip at a time. The class can then be used at multiple places
 * throughout the application.
 */
@Component
public class TripManager {
  private Trip currentTrip;
  private boolean tripActive;

  public TripManager() {
    tripActive = false;
  }

  public void startTrip() {
    currentTrip = new Trip();
    tripActive = true;
    currentTrip.start();
  }

  public void endTrip() {
    currentTrip.end();
    tripActive = false;
  }

  public boolean tripIsActive() {
    return tripActive;
  }

  public Trip getCurrentTrip() {
    return currentTrip;
  }

  public List<ShipDto> getCurrentTripData() {
    return currentTrip.getShipData();
  }

  public void setCurrentTrip(Trip trip) {
    currentTrip = trip;
  }

}
