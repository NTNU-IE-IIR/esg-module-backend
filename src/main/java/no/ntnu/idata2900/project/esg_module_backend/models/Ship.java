package no.ntnu.idata2900.project.esg_module_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * The Ship class represents various ship data collected from different sources. The class is part
 * of the data packaged into a {@link DataPoint data point}.
 *
 * @author Group 14
 * @version v0.2.8 (2025.04.30)
 */
@Entity
@Table(name = "ship")
@Schema(description = "Ship entity representing ship data at a specific data point")
public class Ship {

  @Id
  @Column(name = "ship_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "registration_mark")
  @Schema(description = "Ship registration mark")
  private String registrationMark;

  @Column(name = "name")
  @Schema(description = "Ship name")
  private String name;

  @Column(name = "heading")
  @Schema(description = "Ship heading")
  private float heading;

  @Column(name = "speed")
  @Schema(description = "Ship speed")
  private float speed;

  @Column(name = "target_speed")
  @Schema(description = "Ship target speed")
  private float targetSpeed;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Fuel consumption data over the last time interval")
  private Fuel fuelConsumption;

  @JsonIgnore
  @MapsId
  @OneToOne(mappedBy = "ship")
  @JoinColumn(name = "ship_id")
  @Schema(description = "Data point containing this specific ship data")
  private DataPoint dp;

  /**
   * Default constructor for the Ship class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public Ship() {
    // Intentionally left blank
  }

  /**
   * Constructor for the Ship class.
   *
   * @param registrationMark The specified registration mark
   * @param name             The specified name
   * @param heading          The specified heading
   * @param speed            The specified speed
   */
  public Ship(String registrationMark, String name, float heading, float speed) {
    this.registrationMark = registrationMark;
    this.name = name;
    this.heading = heading;
    this.speed = speed;
    this.targetSpeed = 0.0f;
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
   * Getter for registration mark.
   * 
   * @return Registration mark
   */
  public String getRegistrationMark() {
    return this.registrationMark;
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
   * Getter for speed.
   *
   * @return Speed
   */
  public float getSpeed() {
    return this.speed;
  }

  /**
   * Getter for target speed.
   * 
   * @return Target speed
   */
  public float getTargetSpeed() {
    return this.targetSpeed;
  }

  /**
   * Setter for target speed.
   * 
   * @param targetSpeed The specified target speed
   */
  public void setTargetSpeed(float targetSpeed) {
    this.targetSpeed = targetSpeed;
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
   * Getter for data point.
   * 
   * @return Data point
   */
  public DataPoint getDp() {
    return this.dp;
  }

  /**
   * Setter for data point.
   * 
   * @param dp The specified data point
   */
  public void setDp(DataPoint dp) {
    this.dp = dp;
  }
}
