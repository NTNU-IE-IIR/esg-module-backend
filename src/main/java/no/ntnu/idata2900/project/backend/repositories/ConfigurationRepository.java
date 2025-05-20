package no.ntnu.idata2900.project.backend.repositories;

import no.ntnu.idata2900.project.backend.models.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The ConfigurationRepository interface represents the repository for
 * {@link Configuration configurations}.
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
