package no.ntnu.idata2900.project.esg_module_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import no.ntnu.idata2900.project.esg_module_backend.models.Configuration;

/**
 * The ConfigurationRepository interface represents the repository for
 * {@link Configuration configurations}.
 */
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
