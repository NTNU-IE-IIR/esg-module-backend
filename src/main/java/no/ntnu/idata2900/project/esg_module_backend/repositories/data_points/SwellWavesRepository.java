package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.SwellWaves;

/**
 * The SwellWavesRepository interface represents the repository for
 * {@link SwellWaves swell waves data}.
 */
@Repository
public interface SwellWavesRepository extends CrudRepository<SwellWaves, Long> {
}
