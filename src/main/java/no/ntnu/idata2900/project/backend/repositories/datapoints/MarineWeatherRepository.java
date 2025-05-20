package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The MarineWeatherRepository interface represents the repository for
 * {@link MarineWeather marine weather data}.
 */
@Repository
public interface MarineWeatherRepository extends CrudRepository<MarineWeather, Long> {
}
