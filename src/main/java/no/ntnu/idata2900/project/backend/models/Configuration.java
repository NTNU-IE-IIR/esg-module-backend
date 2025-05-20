package no.ntnu.idata2900.project.backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Configuration class represents the configuration set for specific vessel. The vessels are
 * identified by the registration mark.
 *
 * @author Group 14
 * @version v0.1.1 (2025.04.30)
 */
@Entity
@Table(name = "configuration")
public class Configuration {

  @Id
  @Column(name = "registration_mark")
  @Schema(description = "Unique vessel ID")
  private String registrationMark;

  @Column(name = "name")
  @Schema(description = "Configuration name")
  private String name;

  @Column(name = "fuel_type")
  @Schema(description = "Vessel fuel type")
  private String fuelType;

  @Column(name = "level_1")
  @Schema(description = "If vessel is level 1")
  private boolean level1;

  /**
   * Constructor for the Configuration class.
   *
   * @param registrationMark The specified registration mark
   * @param name             The specified name
   * @param level1           The specified level 1
   */
  public Configuration(String registrationMark, String name, String fuelType, boolean level1) {
    this.registrationMark = registrationMark;
    this.name = name;
    this.fuelType = fuelType;
    this.level1 = level1;
  }

  /**
   * Default constructor for Configuration class.
   *
   * <p>The default constructor is required by JPA.</p>
   */
  public Configuration() {
    // Intentionally left blank
  }

  /**
   * Getter for vessel registration mark.
   *
   * @return Registration mark
   */
  public String getRegistrationMark() {
    return this.registrationMark;
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
   * Setter for vessel name.
   *
   * @param name The specified name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for fuel type.
   *
   * @return Fuel type
   */
  public String getFuelType() {
    return fuelType;
  }

  /**
   * Setter for fuel type.
   *
   * @param fuelType The specified fuel type
   */
  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
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
   * Setter for vessel level 1.
   *
   * @param level1 The specified level 1
   */
  public void setLevel1(boolean level1) {
    this.level1 = level1;
  }

  /**
   * Checks if the configuration is valid.
   *
   * @return True if the configuration is valid or false otherwise
   */
  public boolean isValid() {
    return this.registrationMark != null && !this.registrationMark.isBlank() && this.name != null
        && !this.name.isBlank();
  }
}
