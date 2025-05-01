package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.WeatherService;

/**
 * The WeatherGenerator class represents the generator {@link Weather weather data}. The class
 * contains a set of constants called max boundaries that defines how much each attribute can
 * maximum change at each {@link DataPoint data point}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
public class WeatherGenerator {
  private WeatherService weatherService;

  // MAX BOUNDARIES
  private static final float WIND = 0.5f; // m/s
  private static final float GUST = 1.0f; // m/s

  private static final Random RAN = new Random();

  /**
   * Constructor for the WeatherGenerator class.
   * 
   * @param weatherService The specified weather data service
   */
  @Autowired
  public WeatherGenerator(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  public void generate(DataPoint baseDp, DataPoint dp) {
  }

  private Weather randomWeather() {
    Weather weather = null;
    boolean valid = false;

    while (!valid) {
      float windU = 
    }
  }
}
