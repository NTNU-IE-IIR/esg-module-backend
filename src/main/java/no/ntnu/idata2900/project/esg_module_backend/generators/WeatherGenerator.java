package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.WeatherService;

/**
 * The WeatherGenerator class represents the generator {@link Weather weather data}.
 * 
 * <p><i>Deviation constants:</i></p>
 * 
 * <ul>
 *   <li><code>WIND</code> (<code>degrees</code>): Maximum wind speed deivation</li>
 *   <li><code>GUST</code> (<code>knots</code>): Maximum wind gust speed deviation</li>
 * </ul>
 * 
 * <p>The preceding constants define how much each attribute can maximum change at each
 * {@link DataPoint data point}.</p>
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class WeatherGenerator {
  private WeatherService weatherService;

  private static final float WIND = 0.5f;
  private static final float GUST = 1.0f;

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

  /**
   * Generates weather data and adds it to storage. The generated weather data is based on the data
   * provided in the specified base data point.
   * 
   * <p>When the weather data is added to storage, it contains a reference to the specified data
   * point. This enables the weather data to be accessed from the data point itself when fetched
   * from storage.</p>
   * 
   * @param baseDp The specified base data point
   * @param dp The specified data point
   * @see Weather
   * @see DataPoint
   */
  public void generate(DataPoint baseDp, DataPoint dp) {
    Weather weather;
    if (baseDp == null) {
      weather = new Weather(5.0f, 5.0f, 10.0f);
    } else {
      weather = this.randomWeather(baseDp);
    }
    weather.setDp(dp);
    // Add weather data to storage
    this.weatherService.add(weather);
  }

  /**
   * Generates random weather data. The generated weather data is based on the data provided in the
   * specified base data point.
   * 
   * @param baseDp The specified base data point
   * @return Random weather data
   */
  private Weather randomWeather(DataPoint baseDp) {
    Weather weather = null;
    boolean valid = false;

    while (!valid) {
      float windU = baseDp.getWeather().getWindU() + RAN.nextFloat(WIND * 2) - WIND;
      float windV = baseDp.getWeather().getWindV() + RAN.nextFloat(WIND * 2) - WIND;
      float gust = baseDp.getWeather().getGust() + RAN.nextFloat(GUST * 2) - GUST;

      weather = new Weather(windU, windV, gust);

      if (weather.isGeneratedValid()) {
        valid = true;
      }
    }

    return weather;
  }
}
