package no.ntnu.idata2900.project.backend.generators;

import java.util.Random;

import no.ntnu.idata2900.project.backend.models.datapoints.SwellWaves;
import no.ntnu.idata2900.project.backend.models.datapoints.Waves;
import no.ntnu.idata2900.project.backend.models.datapoints.WindWaves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import no.ntnu.idata2900.project.backend.services.datapoints.MarineWeatherService;

/**
 * The MarineWeatherGenerator class represents the generator for
 * {@link MarineWeather marine weather data}.
 * 
 * <p><i>Deviation constants:</i></p>
 * 
 * <ul>
 *   <li><code>OCEAN_CURRENT_VELOCITY</code> (<code>m/s</code>): Maximum ocean current velocity
 *   deivation</li>
 *   <li><code>OCEAN_CURRENT_DIRECTION</code> (<code>degrees</code>): Maximum ocean current
 *   direction deviation</li>
 * </ul>
 * 
 * <p>The preceding constants define how much each attribute can maximum change at each
 * {@link DataPoint data point}.</p>
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class MarineWeatherGenerator {
  private MarineWeatherService marineWeatherService;

  private WavesGenerator wavesGenerator;
  private WindWavesGenerator wwavesGenerator;
  private SwellWavesGenerator swellWavesGenerator;

  private static final float OCEAN_CURRENT_VELOCITY = 0.001f;
  private static final float OCEAN_CURRENT_DIRECTION = 0.0025f;

  private static final Random RAN = new Random();

  /**
   * Constructor for the MarineWeatherGenerator class.
   * 
   * @param marineWeatherService The specified marine weather service
   */
  @Autowired
  public MarineWeatherGenerator(
    MarineWeatherService marineWeatherService,
    WavesGenerator wavesGenerator,
    WindWavesGenerator wwavesGenerator,
    SwellWavesGenerator swellWavesGenerator
  ) {
    this.marineWeatherService = marineWeatherService;
    this.wavesGenerator = wavesGenerator;
    this.wwavesGenerator = wwavesGenerator;
    this.swellWavesGenerator = swellWavesGenerator;
  }

  /**
   * Generates marine weather data and adds it to storage. The generated marine weather data is
   * based on the data provided in the specified base data point.
   * 
   * <p>When the marine weather data is added to storage, it contains a reference to the specified
   * data point. This enables the marine waether data to be accessed from the data point itself
   * when fetched from storage.</p>
   * 
   * @param baseDp The specified base data point
   * @param dp The specified data point
   * @see Vessel
   * @see DataPoint
   */
  public MarineWeather generate(DataPoint baseDp, DataPoint dp) {
    MarineWeather marineWeather;
    if (baseDp == null) {
      marineWeather = new MarineWeather(0.2f, 230.0f);
    } else {
      marineWeather = this.randomMarineWeather(baseDp);
    }
    marineWeather.setDp(dp);
    // Add marine weather data to storage
    this.marineWeatherService.add(marineWeather);

    Waves waves;
    WindWaves wwaves;
    SwellWaves swellWaves;

    if (baseDp == null) {
      waves = wavesGenerator.generate(null, marineWeather);
      wwaves = wwavesGenerator.generate(null, marineWeather);
      swellWaves = swellWavesGenerator.generate(null, marineWeather);
    } else {
      waves = wavesGenerator.generate(baseDp.getMarineWeather(), marineWeather);
      wwaves = wwavesGenerator.generate(baseDp.getMarineWeather(), marineWeather);
      swellWaves = swellWavesGenerator.generate(baseDp.getMarineWeather(), marineWeather);
    }
    marineWeather.setWaves(waves);
    marineWeather.setWwaves(wwaves);
    marineWeather.setSwellWaves(swellWaves);
    return marineWeather;
  }

  /**
   * Generates random marine weather data. The generated marine weather data is based on the data
   * provided in the specified base data point.
   * 
   * @param baseDp The specified base data point
   * @return Random marine weather data
   */
  private MarineWeather randomMarineWeather(DataPoint baseDp) {
    MarineWeather marineWeather = null;
    boolean valid = false;

    while (!valid) {
      float oceanCurrentVelocity = baseDp.getMarineWeather().getOceanCurrentVelocity()
                                 + RAN.nextFloat(OCEAN_CURRENT_VELOCITY * 2)
                                 - OCEAN_CURRENT_VELOCITY;
      float oceanCurrentDirection = baseDp.getMarineWeather().getOceanCurrentDirection()
                                  + RAN.nextFloat(OCEAN_CURRENT_DIRECTION * 2)
                                  - OCEAN_CURRENT_DIRECTION;

      // Correct `oceanCurrentDirection`
      if (oceanCurrentDirection > 360) {
        oceanCurrentDirection -= 360;
      } else if (oceanCurrentDirection < 0) {
        oceanCurrentDirection += 360;
      }
    
      marineWeather = new MarineWeather(oceanCurrentVelocity, oceanCurrentDirection);

      if (marineWeather.isGeneratedValid()) {
        valid = true;
      }
    }

    return marineWeather;
  }
}
