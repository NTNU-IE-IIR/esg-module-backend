package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.repositories.datapoints.MarineWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The MarineWeatherService class represents the service for
 * {@link MarineWeather marine weather data}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class MarineWeatherService {

  private final MarineWeatherRepository repo;

  /**
   * Constructor for the MarineWeatherService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified marine weather repository
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
