package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Ship class represents various ship data collected from different sources. The class is part
 * of the data packaged into a {@link DataPoint data point}.
 *
 * @author Group 14
 * @version v0.2.5 (2025.04.29)
 */
@Entity(name = "ship")
@Schema(description = "Ship entity representing ship data at a specific data point")
public class Ship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique ID")
  private Long id;

  @Schema(description = "Ship name")
  private String name;

  @Schema(description = "Ship heading")
  private float heading;

  @Schema(description = "Ship course")
  private float course;

  @Schema(description = "Ship speed")
  private float speed;

  @Schema(description = "Fuel consumption data over the last time interval")
  private Fuel fuelConsumption;

  @Schema(description = "Amount of fish caught")
  private float fishAmount;

  /**
   * Constructor for the Ship class.
   *
   * @param name            The specified ship name
   * @param heading         The specified heading
   * @param course          The specified course
   * @param speed           The specified speed
   * @param fuelConsumption The specified fuel consumption
   * @param fishAmount      The specified amount
   */
  public Ship(
      String name,
      float heading,
      float course,
      float speed,
      Fuel fuelConsumption,
      float fishAmount
  ) {
    this.name = name;
    this.heading = heading;
    this.course = course;
    this.speed = speed;
    this.fuelConsumption = fuelConsumption;
    this.fishAmount = fishAmount;
  }

  /**
   * Getter for ID.
   * 
   * @return ID
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Getter for ship name.
   *
   * @return Ship name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter for heading.
   *
   * @return Heading
   */
  public float getHeading() {
    return this.heading;
  }

  /**
   * Getter for course.
   *
   * @return Course
   */
  public float getCourse() {
    return this.course;
  }

  /**
   * Getter for speed.
   *
   * @return Speed
   */
  public float getSpeed() {
    return this.speed;
  }

  /**
   * Getter for fuel consumption.
   *
   * @return Fuel consumption
   */
  public Fuel getFuelConsumption() {
    return this.fuelConsumption;
  }

  /**
   * Getter for fish amount.
   *
   * @return Fish amount
   */
  public float getFishAmount() {
    return this.fishAmount;
  }
}
