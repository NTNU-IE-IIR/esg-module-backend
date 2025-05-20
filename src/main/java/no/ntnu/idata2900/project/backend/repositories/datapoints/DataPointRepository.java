package no.ntnu.idata2900.project.backend.repositories.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The DataPointRepository interface represents the repository for {@link DataPoint data points}.
 */
@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, Long> {
}
