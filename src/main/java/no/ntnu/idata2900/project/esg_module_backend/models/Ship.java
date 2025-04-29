package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Ship class represents various ship data collected from different sources. The class is part
 * of the data packaged into a {@link DataPoint data point}.
 *
 * @author Group 14
 * @version v0.2.3 (2025.04.27)
 */
public class Ship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private final String name;
  private final float heading;
  private final float course;
  private final float speed;
  private final Fuel fuelConsumption;

  /**
   * Constructor for the Ship class.
   *
   * @param name            The specified ship name
   * @param heading         The specified heading
   * @param course          The specified course
   * @param speed           The specified speed
   * @param fuelConsumption The specified fuel consumption
   */
  public Ship(
      String name,
      float heading,
      float course,
      float speed,
      Fuel fuelConsumption
  ) {
    this.name = name;
    this.heading = heading;
    this.course = course;
    this.speed = speed;
    this.fuelConsumption = fuelConsumption;
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
}
