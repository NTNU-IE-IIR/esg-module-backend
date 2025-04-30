package no.ntnu.idata2900.project.esg_module_backend.dtos;

/**
 * The ShipDto class represents a data transfer object (DTO) for transfering ship data on a
 * suitable format for the frontend.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.08)
 */
public class ShipDto {
  private int id;
  private String name;
  private float heading;
  private float course;
  private float speed;
  private float fuelLevel;
  private float totalDistance;

  private float lat;
  private float lng;
  private String timestamp;

  /**
   * Constructor for BoatDataDto class.
   *
   * @param id            The specified ID
   * @param name          The specified name
   * @param heading       The specified heading
   * @param course        The specified course
   * @param speed         The specified speed
   * @param totalDistance The specified total distance
   * @param lat           The specified latitude
   * @param lng           The specified longitude
   * @param timestamp     The specified timestamp
   */
  public ShipDto(
      int id,
      String name,
      float heading,
      float course,
      float speed,
      float fuelLevel,
      float totalDistance,
      float lat,
      float lng,
      String timestamp
  ) {
    this.id = id;
    this.name = name;
    this.heading = heading;
    this.course = course;
    this.speed = speed;
    this.fuelLevel = fuelLevel;
    this.totalDistance = totalDistance;
    this.lat = lat;
    this.lng = lng;
    this.timestamp = timestamp;
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
   * Getter for total distance.
   *
   * @return Total distance
   */
  public float getTotalDistance() {
    return totalDistance;
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
}
