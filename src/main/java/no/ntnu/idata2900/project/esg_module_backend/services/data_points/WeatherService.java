package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.WeatherRepository;

/**
 * The WeatherService class represents the service for {@link Weather weather data}.
 * 
 * @author Group 14
 * @version v0.1.1 (2025.05.02)
 */
@Service
public class WeatherService {

  private WeatherRepository repo;

  /**
   * Constructor for the WeatherService class.
   * 
   * @param repo The specified weather data repository
   */
  @Autowired
  public WeatherService(WeatherRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified weather data if it is valid.
   * 
   * @param weather The specified weather data
   */
  public void add(Weather weather) {
    if (!weather.isValid()) {
      throw new IllegalArgumentException("The specified weather data is invalid");
    }
    this.repo.save(weather);
  }
}
