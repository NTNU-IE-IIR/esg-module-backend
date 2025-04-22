package no.ntnu.idata2900.project.esg_module_backend.sources;

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
  void start();

  void stop();

  void setDataListener(DataListener listener);
}
