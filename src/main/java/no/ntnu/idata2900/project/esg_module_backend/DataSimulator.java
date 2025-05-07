package no.ntnu.idata2900.project.esg_module_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.generators.DataPointGenerator;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.DataPointService;

/**
 * DataSimulator
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class DataSimulator implements ApplicationListener<ApplicationReadyEvent> {

  private DataPointService dpService;

  private DataPointGenerator dpGenerator;

  private final Logger logger = LoggerFactory.getLogger(DataSimulator.class);

  @Autowired
  public DataSimulator(DataPointService dpService, DataPointGenerator dpGenerator) {
    this.dpService = dpService;
    this.dpGenerator = dpGenerator;
  }

  public void onApplicationEvent(ApplicationReadyEvent event) {
    // Uncomment to start simulator
    //this.logger.info("Starting simulator...");
    //this.simulateDataPoints();
    //this.logger.info("Simulator finished");
  }

  private void simulateDataPoints() {
    int num = 540;

    this.logger.info("Simulating data points...");

    if (this.dpService.getAll().iterator().hasNext()) {
      this.logger.info("Data points already simulated");
    } else {
      for (int i = 0; i < 4; i++) {
        DataPoint dp = null;
        for (int j = 0; j < num; j++) {
          dp = dpGenerator.generate(dp, null);
        }
      }
    }
  }
}
