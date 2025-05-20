package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The PositionRepository interface represents the repository for {@link Position position}.
 */
@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
}
