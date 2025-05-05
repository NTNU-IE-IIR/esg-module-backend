package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;

/**
 * The WavesRepository interface represents the repository for {@link Waves waves data}.
 */
@Repository
public interface WavesRepository extends CrudRepository<Waves, Long> {
}
