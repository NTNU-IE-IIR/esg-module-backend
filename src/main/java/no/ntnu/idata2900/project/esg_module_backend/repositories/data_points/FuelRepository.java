package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Fuel;

/**
 * The FuelRepository interface represents the repository for {@link Fuel fuel consumption}.
 */
@Repository
public interface FuelRepository extends CrudRepository<Fuel, Long> {
}
