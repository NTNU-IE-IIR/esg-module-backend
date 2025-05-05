package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;

/**
 * The PositionRepository interface represents the repository for {@link Position position}.
 */
@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
}
