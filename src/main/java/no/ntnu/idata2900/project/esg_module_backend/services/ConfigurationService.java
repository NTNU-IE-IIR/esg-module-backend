package no.ntnu.idata2900.project.esg_module_backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.dtos.ConfigurationDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Configuration;
import no.ntnu.idata2900.project.esg_module_backend.repositories.ConfigurationRepository;

/**
 * The ConfigurationService class represents the service for {@link Configuration configurations}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.04.28)
 */
@Service
public class ConfigurationService {
  private ConfigurationRepository configurationRepository;

  /**
   * Constructor for the ConfigurationService class.
   * 
   * @param configurationRepository The specified configuration repository
   */
  @Autowired
  public ConfigurationService(ConfigurationRepository configurationRepository) {
    this.configurationRepository = configurationRepository;
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
    return this.configurationRepository.findById(registrationMark);
  }

  /**
   * Adds the specified configuration if it is valid.
   * 
   * @param configuration The specified configuraiton
   * @throws IllegalArgumentException If the configuration is invalid
   */
  public void add(Configuration configuration) {
    boolean valid = configuration.isValid();
    if(!valid) {
      throw new IllegalArgumentException("Configuration is invalid");
    }
    this.configurationRepository.save(configuration);
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
        this.configurationRepository.findById(registrationMark);
    boolean exist = configuration.isPresent();
    if (exist) {
      Configuration existingConfiguration = configuration.get();
      existingConfiguration.setName(configurationDto.getName());
      existingConfiguration.setLevel1(configurationDto.isLevel1());
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
        this.configurationRepository.findById(registrationMark);
    boolean exist = configuration.isPresent();
    if (exist) {
      this.configurationRepository.deleteById(registrationMark);
    }
    return exist;
  }
}
