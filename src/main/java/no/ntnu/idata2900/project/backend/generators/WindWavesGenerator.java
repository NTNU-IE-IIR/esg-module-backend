package no.ntnu.idata2900.project.backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.models.datapoints.WindWaves;
import no.ntnu.idata2900.project.backend.services.datapoints.WindWavesService;

/**
 * WindWavesGenerator class
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class WindWavesGenerator {
  private WindWavesService wwavesService;

  // Generator boundaries
  private static final float WWAVES_HEIGHT = 0.25f;
  private static final float WWAVES_DIRECTION = 1.0f;
  private static final float WWAVES_PERIOD = 0.5f;

  private static final Random RAN = new Random();

  @Autowired
  public WindWavesGenerator(WindWavesService wwavesService) {
    this.wwavesService = wwavesService;
  }

  public WindWaves generate(MarineWeather baseMarineWeather, MarineWeather marineWeather) {
    WindWaves wwaves;
    if (baseMarineWeather == null) {
      wwaves = new WindWaves(0.3f, 15.0f, 4.0f);
    } else {
      wwaves = this.randomWwaves(baseMarineWeather);
    }
    wwaves.setMarineWeather(marineWeather);
    // Add wind waves data to storage
    this.wwavesService.add(wwaves);
    return wwaves;
  }

  public WindWaves randomWwaves(MarineWeather baseMarineWeather) {
    WindWaves wwaves = null;
    boolean valid = false;

    while (!valid) {
      float wwavesHeight = baseMarineWeather.getWwaves().getWwavesHeight()
                        + RAN.nextFloat(WWAVES_HEIGHT * 2) - WWAVES_HEIGHT;
      float wwavesDirection = baseMarineWeather.getWwaves().getWwavesDirection()
                           + RAN.nextFloat(WWAVES_DIRECTION * 2) - WWAVES_DIRECTION;
      float wwavesPeriod = baseMarineWeather.getWwaves().getWwavesPeriod()
                        + RAN.nextFloat(WWAVES_PERIOD * 2) - WWAVES_PERIOD;
      
      // Correct `wwavesDirection`
      if (wwavesDirection > 360) {
        wwavesDirection -= 360;
      } else if (wwavesDirection < 0) {
        wwavesDirection += 360;
      }

      wwaves = new WindWaves(wwavesHeight, wwavesDirection, wwavesPeriod);

      if (wwaves.isGeneratedValid()) {
        valid = true;
      }
    }

    return wwaves;
  }
}
