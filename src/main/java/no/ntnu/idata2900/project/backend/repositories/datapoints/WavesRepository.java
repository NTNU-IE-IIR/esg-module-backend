package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Waves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The WavesRepository interface represents the repository for {@link Waves waves data}.
 */
@Repository
public interface WavesRepository extends CrudRepository<Waves, Long> {
}
