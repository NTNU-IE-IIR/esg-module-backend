package no.ntnu.idata2900.project.esg_module_backend.sources;

import no.ntnu.idata2900.project.esg_module_backend.models.Trip;

/**
 * Represents a general interface for a data source, allowing the generation
 * and delivery of data to a registered listener. A class implementing
 * this interface is responsible for managing the initialization,
 * execution, and termination of the data flow.
 * Implementations of this interface should provide mechanisms to produce data.
 *
 * @author Group 14
 * @version v0.0.1 (2025.04.22)
 */
public interface DataSource {
  /**
   * Starts the data source. If the implementation is a fake data source, then this method should
   * start the data generation process. If the implementation is a real data source, then this method
   * should connect to the data source and start receiving data.
   */
  void start(Trip trip);

  /**
   * Stops the data source. If the implementation is a fake data source, then this method
   * should stop the data generation process. If the implementation is a real data source,
   * then this method should disconnect from the data source and terminate the receipt
   * of data.
   */
  void stop();

  /**
   * Registers a DataListener to receive data events. The specified listener will be
   * notified whenever new data is available or an event occurs in the data source.
   * Only one DataListener instance can be set at a time.
   *
   * @param listener the DataListener instance that will handle incoming data events.
   */
  void setDataListener(DataListener listener);
}
