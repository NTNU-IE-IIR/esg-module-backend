package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;

/**
 * The WeatherRepository interface represents the repository for {@link Weather weather data}.
 */
@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
}
