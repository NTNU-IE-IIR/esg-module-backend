package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Weather;
import no.ntnu.idata2900.project.backend.repositories.datapoints.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The WeatherService class represents the service for {@link Weather weather data}.
 *
 * @author Group 14
 * @version v0.1.1 (2025.05.02)
 */
@Service
public class WeatherService {

  private final WeatherRepository repo;

  /**
   * Constructor for the WeatherService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified weather repository
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
