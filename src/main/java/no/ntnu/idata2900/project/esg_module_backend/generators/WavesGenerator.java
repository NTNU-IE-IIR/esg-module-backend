package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.WavesService;

/**
 * WavesGenerator class
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class WavesGenerator {
  private WavesService wavesService;

  // Generator boundaries
  private static final float WAVES_HEIGHT = 3.0f;
  private static final float WAVES_DIRECTION = 1.0f;
  private static final float WAVES_PERIOD = 1.0f;

  private static final Random RAN = new Random();

  @Autowired
  public WavesGenerator(WavesService wavesService) {
    this.wavesService = wavesService;
  }

  public void generate(MarineWeather baseMarineWeather, MarineWeather marineWeather) {
    Waves waves = this.randomWaves(baseMarineWeather);
    waves.setMarineWeather(marineWeather);
    // Add waves data to storage
    this.wavesService.add(waves);
  }

  public Waves randomWaves(MarineWeather baseMarineWeather) {
    Waves waves = null;
    boolean valid = false;

    while (!valid) {
      float wavesHeight = baseMarineWeather.getWaves().getWavesHeight()
                        + RAN.nextFloat(WAVES_HEIGHT * 2) - WAVES_HEIGHT;
      float wavesDirection = baseMarineWeather.getWaves().getWavesDirection()
                           + RAN.nextFloat(WAVES_DIRECTION * 2) - WAVES_DIRECTION;
      float wavesPeriod = baseMarineWeather.getWaves().getWavesPeriod()
                        + RAN.nextFloat(WAVES_PERIOD * 2) - WAVES_PERIOD;
      
      // Correct `wavesDirection`
      if (wavesDirection > 360) {
        wavesDirection -= 360;
      } else if (wavesDirection < 0) {
        wavesDirection += 360;
      }

      waves = new Waves(wavesHeight, wavesDirection, wavesPeriod);

      if (waves.isGeneratedValid()) {
        valid = true;
      }
    }

    return waves;
  }
}
