package no.ntnu.idata2900.project.backend.dtos;

/**
 * The ConfigurationDto class represents the data transfer object (DTO) for
 * {@link Configuration configurations}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.28)
 */
public class ConfigurationDto {
  private String name;
  private boolean level1;
  private String fuelType;

  /**
   * Constructor for the ConfigurationDto class.
   *
   * @param name   The specified name
   * @param level1 The specified level 1
   */
  public ConfigurationDto(String name, String fuelType, boolean level1) {
    this.name = name;
    this.fuelType = fuelType;
    this.level1 = level1;
  }

  /**
   * Getter for vessel name.
   *
   * @return Name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Checks if the vessel is level 1.
   *
   * @return True if level 1 or false otherwise
   */
  public boolean isLevel1() {
    return this.level1;
  }

  /**
   * Checks if the configuration DTO is valid.
   *
   * @return True if the configuration DTO is valid or false otherwise
   */
  public boolean isValid() {
    return this.name != null && !this.name.isBlank();
  }

  public String getFuelType() {
    return fuelType;
  }
}
