package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The Ship class represents various ship data collected from different sources. The class is part
 * of the data packaged into a {@link DataPoint data point}.
 * 
 * @author Group 14
 * @version v0.2.0 (2025.04.08)
 */
public class Ship {
  // TODO Change to automatically generated ID
  private int id;
  private String name;
  private float heading;
  private float course;
  private float speed;
  private float fuelLevel;
  private float fishAmount;
  private float totalDistance;

  /**
   * Constructor for the Ship class.
   * 
   * @param id The specified ID
   * @param name The specified ship name
   * @param heading The specified heading
   * @param course The specified course
   * @param speed The specified speed
   * @param fuelLevel The specified fuel level
   * @param fishAmount The specified amount
   * @param totalDistance The specified total distance
   */
  public Ship(
    int id,
    String name,
    float heading,
    float course,
    float speed,
    float fuelLevel,
    float fishAmount,
    float totalDistance
  ) {
    this.id = id;
    this.name = name;
    this.heading = heading;
    this.course = course;
    this.speed = speed;
    this.fuelLevel = fuelLevel;
    this.fishAmount = fishAmount;
    this.totalDistance = totalDistance;
  }

  /**
   * Getter for ID.
   * 
   * @return ID
   */
  public int getId() {
    return id;
  }

  /**
   * Getter for ship name.
   * 
   * @return Ship name
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for heading.
   * 
   * @return Heading
   */
  public float getHeading() {
    return heading;
  }

  /**
   * Getter for course.
   * 
   * @return Course
   */
  public float getCourse() {
    return course;
  }

  /**
   * Getter for speed.
   * 
   * @return Speed
   */
  public float getSpeed() {
    return speed;
  }

  /**
   * Getter for fuel level.
   * 
   * @return Fuel level
   */
  public float getFuelLevel() {
    return fuelLevel;
  }

  /**
   * Getter for fish amount.
   * 
   * @return Fish amount
   */
  public float getFishAmount() {
    return fishAmount;
  }

  /**
   * Getter for total distance traveled.
   * 
   * @return Total distance traveled
   */
  public float getTotalDistance() {
    return totalDistance;
  }
}
