package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The WeatherRepository interface represents the repository for {@link Weather weather data}.
 */
@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
}
