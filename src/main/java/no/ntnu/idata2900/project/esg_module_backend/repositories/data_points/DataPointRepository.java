package no.ntnu.idata2900.project.esg_module_backend.repositories.data_points;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;

/**
 * The DataPointRepository interface represents the repository for {@link DataPoint data points}.
 */
@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, Long> {
}
