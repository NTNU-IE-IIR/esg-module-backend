package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.SwellWaves;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.SwellWavesService;

/**
 * SwellWavesGenerator class
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class SwellWavesGenerator {
  private SwellWavesService swellWavesService;

  // Generator boundaries
  private static final float SWELL1_HEIGHT = 0.25f;
  private static final float SWELL1_DIRECTION = 1.0f;
  private static final float SWELL1_PERIOD = 2.0f;
  private static final float SWELL2_HEIGHT = 0.1f;
  private static final float SWELL2_DIRECTION = 1.0f;
  private static final float SWELL2_PERIOD = 1.0f;

  private static final Random RAN = new Random();

  @Autowired
  public SwellWavesGenerator(SwellWavesService swellWavesService) {
    this.swellWavesService = swellWavesService;
  }

  public void generate(MarineWeather baseMarineWeather, MarineWeather marineWeather) {
    SwellWaves swellWaves;
    if (baseMarineWeather == null) {
      swellWaves = new SwellWaves(0.5f, 60.0f, 7.0f, 0.3f, 55.0f, 9.0f);
    } else {
      swellWaves = this.randomWaves(baseMarineWeather);
    }
    swellWaves.setMarineWeather(marineWeather);
    // Add swell waves data to storage
    this.swellWavesService.add(swellWaves);
  }

  public SwellWaves randomWaves(MarineWeather baseMarineWeather) {
    SwellWaves swellWaves = null;
    boolean valid = false;

    while (!valid) {
      float swell1Height = baseMarineWeather.getSwellWaves().getSwell1Height()
                        + RAN.nextFloat(SWELL1_HEIGHT * 2) - SWELL1_HEIGHT;
      float swell1Direction = baseMarineWeather.getSwellWaves().getSwell1Direction()
                           + RAN.nextFloat(SWELL1_DIRECTION * 2) - SWELL1_DIRECTION;
      float swell1Period = baseMarineWeather.getSwellWaves().getSwell1Period()
                        + RAN.nextFloat(SWELL1_PERIOD * 2) - SWELL1_PERIOD;
      float swell2Height = baseMarineWeather.getSwellWaves().getSwell2Height()
                        + RAN.nextFloat(SWELL2_HEIGHT * 2) - SWELL2_HEIGHT;
      float swell2Direction = baseMarineWeather.getSwellWaves().getSwell2Direction()
                           + RAN.nextFloat(SWELL2_DIRECTION * 2) - SWELL2_DIRECTION;
      float swell2Period = baseMarineWeather.getSwellWaves().getSwell2Period()
                        + RAN.nextFloat(SWELL2_PERIOD * 2) - SWELL2_PERIOD;
      
      // Correct `swell1Direction`
      if (swell1Direction > 360) {
        swell1Direction -= 360;
      } else if (swell1Direction < 0) {
        swell1Direction += 360;
      }
      // Correct `swell2Direction`
      if (swell2Direction > 360) {
        swell2Direction -= 360;
      } else if (swell2Direction < 0) {
        swell2Direction += 360;
      }

      swellWaves = new SwellWaves(
        swell1Height,
        swell1Direction,
        swell1Period,
        swell2Height,
        swell2Direction,
        swell2Period
      );

      if (swellWaves.isGeneratedValid()) {
        valid = true;
      }
    }

    return swellWaves;
  }
}
