package no.ntnu.idata2900.project.backend.sources;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;

/**
 * The DataListener interface defines a contract for objects that need to
 * respond to data events. It is primarily used to handle incoming data
 * represented by the ShipDto object.
 * Implementations of this interface must define the behavior for handling
 * received data by implementing the onDataReceived method.
 *
 * @author Group 14
 * @version v0.0.1 (2025.04.22)
 */
public interface DataListener {
  /**
   * Handles the event when data is received. This method is invoked with a
   * ShipDto object containing information about a specific ship.
   * Implementations of this method define how the received data is processed.
   *
   * @param dataPoint a DataPoint object representing the received ship data
   */
  void onDataReceived(DataPoint dataPoint);
}
