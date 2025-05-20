package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Fuel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The FuelRepository interface represents the repository for {@link Fuel fuel consumption}.
 */
@Repository
public interface FuelRepository extends CrudRepository<Fuel, Long> {
}
