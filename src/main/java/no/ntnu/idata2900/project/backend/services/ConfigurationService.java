package no.ntnu.idata2900.project.backend.services;

import java.util.Optional;
import no.ntnu.idata2900.project.backend.dtos.ConfigurationDto;
import no.ntnu.idata2900.project.backend.models.Configuration;
import no.ntnu.idata2900.project.backend.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ConfigurationService class represents the service for {@link Configuration configurations}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.28)
 */
@Service
public class ConfigurationService {

  private final ConfigurationRepository repo;

  /**
   * Constructor for the ConfigurationService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified configuration service
   */
  @Autowired
  public ConfigurationService(ConfigurationRepository repo) {
    this.repo = repo;
  }

  /**
   * Gets the configuration with the specified registration mark. If the configuration exists, it
   * is stored inside a container object. If it does not exist, the value inside the container
   * object is <code>null</code> and the object is considered empty.
   *
   * @param registrationMark The specified registration mark
   * @return A container object containing the configuration if it exists
   */
  public Optional<Configuration> get(String registrationMark) {
    return this.repo.findById(registrationMark);
  }

  /**
   * Adds the specified configuration if it is valid.
   *
   * @param configuration The specified configuraiton
   * @throws IllegalArgumentException If the configuration is invalid
   */
  public void add(Configuration configuration) {
    boolean valid = configuration.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Configuration is invalid");
    }
    this.repo.save(configuration);
  }

  /**
   * Updates the configuration with the specified registration mark with the specified
   * configuration data. The configuration is only updated if it exists and the specified
   * configuration data is valid.
   *
   * @param registrationMark The specified registration mark
   * @param configurationDto The specified configuration data
   * @return True if the configuration exists and the configuration data is valid or false otherwise
   * @throws IllegalArgumentException If the configuration data is invalid
   */
  public boolean update(String registrationMark, ConfigurationDto configurationDto) {
    if (!configurationDto.isValid()) {
      throw new IllegalArgumentException("Configuration data is invalid");
    }
    Optional<Configuration> configuration =
        this.repo.findById(registrationMark);
    boolean exist = configuration.isPresent();
    if (exist) {
      Configuration existingConfiguration = configuration.get();
      existingConfiguration.setName(configurationDto.getName());
      existingConfiguration.setFuelType(configurationDto.getFuelType());
      existingConfiguration.setLevel1(configurationDto.isLevel1());
      this.repo.save(existingConfiguration);
    }
    return exist;
  }

  /**
   * Deletes the configuration with the specified registration mark if it exists.
   *
   * @param registrationMark The specified registration mark
   * @return True if the configuration exists and is deleted or false otherwise
   */
  public boolean delete(String registrationMark) {
    Optional<Configuration> configuration =
        this.repo.findById(registrationMark);
    boolean exist = configuration.isPresent();
    if (exist) {
      this.repo.deleteById(registrationMark);
    }
    return exist;
  }
}
