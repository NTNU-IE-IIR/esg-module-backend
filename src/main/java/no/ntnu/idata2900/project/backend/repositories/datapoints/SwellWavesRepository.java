package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.SwellWaves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The SwellWavesRepository interface represents the repository for
 * {@link SwellWaves swell waves data}.
 */
@Repository
public interface SwellWavesRepository extends CrudRepository<SwellWaves, Long> {
}
