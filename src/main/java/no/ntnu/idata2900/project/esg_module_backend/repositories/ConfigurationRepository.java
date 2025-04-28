package no.ntnu.idata2900.project.esg_module_backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.ntnu.idata2900.project.esg_module_backend.models.Configuration;

/**
 * The ConfigurationRepository interface represents the repository for
 * {@link Configuration configurations}.
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
