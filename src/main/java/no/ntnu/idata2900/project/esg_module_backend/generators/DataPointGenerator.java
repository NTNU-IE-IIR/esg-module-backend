package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Optional;

import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
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
  private final DataPointService dpService;
  private final Logger logger = LoggerFactory.getLogger(DataPointGenerator.class);

  private final PositionGenerator posGenerator;
  private final VesselGenerator vesselGenerator;
  private final WeatherGenerator weatherGenerator;
  private final MarineWeatherGenerator marineWeatherGenerator;
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

  public DataPoint generate(DataPoint baseDp, Trip trip) {
    long ts = 0;
    if (baseDp == null) {
      ts = 1747713600;
    } else {
      // Increment timestamp by 1 min
      ts = baseDp.getTs() + 60;
    }

    DataPoint dp = new DataPoint(ts);
    if (trip != null) {
      dp.setTrip(trip);
    }
    Long id = this.dpService.add(dp);


    dp.setPos(posGenerator.generate(baseDp, dp));
    dp.setWeather(weatherGenerator.generate(baseDp, dp));
    dp.setMarineWeather(marineWeatherGenerator.generate(baseDp, dp));
    logger.info("Datapoint after setting everything: {}", dp);
    dp.setVessel(vesselGenerator.generate(baseDp, dp));


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
