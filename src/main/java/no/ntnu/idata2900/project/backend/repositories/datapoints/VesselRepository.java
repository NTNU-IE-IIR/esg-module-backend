package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The VesselRepository interface represents the repository for {@link Vessel vessel data}.
 */
@Repository
public interface VesselRepository extends CrudRepository<Vessel, Long> {
}
