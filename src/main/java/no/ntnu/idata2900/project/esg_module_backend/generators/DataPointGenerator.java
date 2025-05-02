package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.DataPointService;

/**
 * The DataPointGenerator class represents a generator for {@link DataPoint data points}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class DataPointGenerator {
  private DataPointService dpService;

  private PositionGenerator posGenerator;
  private VesselGenerator vesselGenerator;
  private WeatherGenerator weatherGenerator;
  private MarineWeatherGenerator marineWeatherGenerator;

  private final Logger logger = LoggerFactory.getLogger(DataPointGenerator.class);

  /**
   * Constructor for the DataPointInitializer class.
   * 
   * @param dpService The specified data point service
   */
  @Autowired
  public DataPointGenerator(
    DataPointService dpService,
    PositionGenerator posGenerator,
    VesselGenerator vesselGenerator,
    WeatherGenerator weatherGenerator,
    MarineWeatherGenerator marineWeatherGenerator
  ) {
    this.dpService = dpService;
    this.posGenerator = posGenerator;
    this.vesselGenerator = vesselGenerator;
    this.weatherGenerator = weatherGenerator;
    this.marineWeatherGenerator = marineWeatherGenerator;
  }

  public DataPoint generate(DataPoint baseDp) {
    this.logger.info("Generarting data point...");

    // Increment timestamp by 1 min
    long ts = baseDp.getTs() + 60;

    DataPoint dp = new DataPoint(ts);
    Long id = this.dpService.add(dp);

    this.posGenerator.generate(baseDp, dp);
    this.weatherGenerator.generate(baseDp, dp);
    this.marineWeatherGenerator.generate(baseDp, dp);
    // Update data point so far
    dp = this.fetch(id);
    this.vesselGenerator.generate(baseDp, dp);

    this.logger.info("Finished generating data point");

    return dp;
  }

  private DataPoint fetch(Long id) {
    DataPoint dp = null;
    Optional<DataPoint> fDp = this.dpService.get(id);
    if (fDp.isPresent()) {
      // Update data point with relationships in storage
      dp = fDp.get();
    }
    return dp;
  }
}
