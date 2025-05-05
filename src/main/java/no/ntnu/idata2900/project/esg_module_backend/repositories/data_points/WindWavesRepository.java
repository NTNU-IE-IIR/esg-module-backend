package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.WindWaves;

/**
 * The WindWavesRepository interface represents the repository for
 * {@link WindWaves wind waves data}.
 */
@Repository
public interface WindWavesRepository extends CrudRepository<WindWaves, Long> {
}
