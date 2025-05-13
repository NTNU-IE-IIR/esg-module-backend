package no.ntnu.idata2900.project.esg_module_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.generators.DataPointGenerator;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.services.ModelService;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.DataPointService;

/**
 * The DataSimulator class represents a simulator that starts on application start-up. The
 * simulator generated historical {@link DataPoint data points} that are used to train the model in
 * the machine learning model service.
 * 
 * @author Group 14
 * @version v0.2.0 (2025.05.13)
 */
@Component
public class DataSimulator implements ApplicationListener<ApplicationReadyEvent> {

  private DataPointService dpService;
  private ModelService modelService;

  private DataPointGenerator dpGenerator;

  private static final int TRIPS = 40;
  private static final int TRIP_LENGTH = 540;

  private final Logger logger = LoggerFactory.getLogger(DataSimulator.class);

  /**
   * Constructor for the ModelService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   * 
   * @param dpService    The specified data point service
   * @param modelService The specified model service
   * @param dpGenerator  The specified data point generator
   */
  @Autowired
  public DataSimulator(
    DataPointService dpService,
    ModelService modelService,
    DataPointGenerator dpGenerator
  ) {
    this.dpService = dpService;
    this.modelService = modelService;
    this.dpGenerator = dpGenerator;
  }

  public void onApplicationEvent(ApplicationReadyEvent event) {
    this.logger.info("[SIMULATOR] Starting simulation...");
    this.generateDataPoints();
    boolean isTrained = this.trainModel();
    if (isTrained) {
      this.logger.info("[SIMULATOR] Simulation up-and-running");
    } else {
      this.logger.error("[SIMULATOR] Simulation could not start, please review error logs");
    }
  }

  /**
   * Generates data points representing historical data. The amount of trips to generate for and
   * the length of each trip is specified by the <code>TRIPS</code> and <code>TRIP_LENGTH</code>
   * constants respectively.
   * 
   * <p><b>Note:</b> The length of a trip is measured in minutes, and since a data point is
   * generated each minute, it is equivalent to the amount of data points in each trip.</p>
   */
  private void generateDataPoints() {
    this.logger.info("[GENERATOR] Generating data points, please wait...");

    if (this.dpService.getAll().iterator().hasNext()) {
      this.logger.info("[GENERATOR] Data points already generated");
    } else {
      for (int i = 0; i < TRIPS; i++) {
        DataPoint dp = null;
        for (int j = 0; j < TRIP_LENGTH; j++) {
          dp = dpGenerator.generate(dp, null);
        }
      }
      this.logger.info("[GENERATOR] Finished generating data points");
    }
  }

  /**
   * Trains the machine learning model. This is done by sending a HTTP request to the machine
   * learning model service. The service returns a response after performing the training
   * operation, determining if the model was trained successfully or not.
   * 
   * @return True if the model was successfully trained or false otherwise
   */
  private boolean trainModel() {
    boolean isTrained = false;
    this.logger.info("[MODEL] Model training, please wait...");
    ResponseEntity<String> response = this.modelService.train();
    if (response.getStatusCode() == HttpStatus.OK) {
      isTrained = true;
      this.logger.info("[MODEL] Model finished training");
    } else {
      this.logger.error("[MODEL] Model could not be trained, please review debug output");
    }
    return isTrained;
  }
}
