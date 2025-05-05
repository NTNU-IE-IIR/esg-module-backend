package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;

/**
 * The VesselRepository interface represents the repository for {@link Vessel vessel data}.
 */
@Repository
public interface VesselRepository extends CrudRepository<Vessel, Long> {
}
