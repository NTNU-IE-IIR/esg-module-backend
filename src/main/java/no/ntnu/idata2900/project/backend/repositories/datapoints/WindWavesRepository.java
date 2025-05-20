package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.WindWaves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The WindWavesRepository interface represents the repository for
 * {@link WindWaves wind waves data}.
 */
@Repository
public interface WindWavesRepository extends CrudRepository<WindWaves, Long> {
}
