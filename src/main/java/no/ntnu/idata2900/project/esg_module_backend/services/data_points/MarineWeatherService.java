package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.MarineWeatherRepository;

/**
 * The MarineWeatherService class represents the service for
 * {@link MarineWeather marine weather data}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class MarineWeatherService {

  private MarineWeatherRepository repo;

  /**
   * Constructor for the MarineWeatherService class.
   * 
   * @param repo The specified marine weather data repository
   */
  @Autowired
  public MarineWeatherService(MarineWeatherRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified marine weather data if it is valid.
   * 
   * @param marineWeather The specified marine weather data
   */
  public void add(MarineWeather marineWeather) {
    boolean valid = marineWeather.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Marine weather data is invalid");
    }
    this.repo.save(marineWeather);
  }
}
