package no.ntnu.idata2900.project.esg_module_backend.dtos;

/**
 * The BoatDataDto class represents a data transfer object (DTO) for transfering data on a suitable
 * format to the frontend.
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.08)
 */
public class BoatDataDto {
  private int id;
  private String name;
  private double heading;
  private double course;
  private double speed;

  private float lat;
  private float lng;
  private String timestamp;

  private double fishAmount;

  private double fuelLevel;
  private double totalDistance;

  /**
   * Constructor for BoatDataDto class.
   * 
   * @param id The specified ID
   * @param name The specified name
   * @param heading The specified heading
   * @param course The specified course
   * @param speed The specified speed
   * @param lat The specified latitude
   * @param lng The specified longitude
   * @param ts The specifed timestamp
   * @param fishAmount The specified fish amount
   * @param totalDistance The specified total distance
   */
  public BoatDataDto(
    int id,
    String name,
    double heading,
    double course,
    double speed,
    float lat,
    float lng,
    String timestamp,
    double fishAmount,
    double totalDistance
  ) {
    this.id = id;
    this.name = name;
    this.heading = heading;
    this.course = course;
    this.speed = speed;
    this.lat = lat;
    this.lng = lng;
    this.timestamp = timestamp;
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
   * Getter for name.
   * 
   * @return Name
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for heading.
   * 
   * @return Heading
   */
  public double getHeading() {
    return heading;
  }

  /**
   * Getter for course.
   * 
   * @return Course
   */
  public double getCourse() {
    return course;
  }

  /**
   * Getter for speed.
   * 
   * @return Speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Getter for latitude.
   * 
   * @return Latitude
   */
  public float getLat() {
    return lat;
  }

  /**
   * Getter for longitude.
   * 
   * @return Longitude
   */
  public float getLng() {
    return lng;
  }

  /**
   * Getter for timestamp.
   * 
   * @return Timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Getter for fish amount.
   * 
   * @return Fish amount
   */
  public double getFishAmount() {
    return fishAmount;
  }

  /**
   * Getter for fuel level.
   * 
   * @return Fuel level
   */
  public double getFuelLevel() {
    return fuelLevel;
  }

  /**
   * Getter for total distance.
   * 
   * @return Total distance
   */
  public double getTotalDistance() {
    return totalDistance;
  }
}
