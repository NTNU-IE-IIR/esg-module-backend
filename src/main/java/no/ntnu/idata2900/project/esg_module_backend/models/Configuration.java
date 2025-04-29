package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * The Configuration class represents the configuration set for specific vessel. The vessels are
 * identified by the registration mark.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.04.28)
 */
@Entity(name = "configuration")
public class Configuration {
  @Id
  private String registrationMark;
  private String name;
  private boolean level1;

  /**
   * Constructor for the Configuration class.
   * 
   * @param registrationMark  The specified registration mark
   * @param name              The specified name
   * @param level1            The specified level 1
   */
  public Configuration(String registrationMark, String name, boolean level1) {
    this.registrationMark = registrationMark;
    this.name = name;
    this.level1 = level1;
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
