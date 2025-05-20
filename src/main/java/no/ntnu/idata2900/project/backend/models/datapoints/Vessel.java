package no.ntnu.idata2900.project.backend.models.datapoints;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Vessel class represents various vessel data collected from different sources. The class is
 * part of the data packaged into a {@link DataPoint data point}.
 * 
 * <p><i>Data generation constans:</i></p>
 * 
 * <ul>
 *   <li><code>MAX_SPEED</code> (<code>knots</code>): Maximum speed vessel data can obtain during
 *   data generation</li>
 * </ul>
 * 
 * <p>The preceding constans are used to define boundaries for data generation.</p>
 *
 * @author Group 14
 * @version v0.5.0 (2025.05.13)
 */
@Entity
@Table(name = "vessel")
@Schema(description = "Vessel entity representing vessel data at a specific data point")
public class Vessel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vessel_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "heading")
  @Schema(description = "Vessel heading")
  private float heading;

  @Column(name = "speed")
  @Schema(description = "Vessel speed")
  private float speed;

  @Column(name = "target_speed")
  @Schema(description = "Vessel target speed")
  private float targetSpeed;

  @OneToOne(mappedBy = "vessel", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(description = "Fuel consumption over the last time interval")
  private Fuel fuelConsumption;

  @Column(name = "target_fuel")
  @Schema(description = "Target fuel consumption over the last time interval")
  private float targetFuelConsumption;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "data_point_id")
  @Schema(description = "Data point containing this specific vessel data")
  private DataPoint dp;

  private static final float MAX_SPEED = 15;

  /**
   * Default constructor for the Vessel class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public Vessel() {
    // Intentionally left blank
  }

  /**
   * Constructor for the Vessel class.
   *
   * @param heading The specified heading
   * @param speed   The specified speed
   */
  public Vessel(float heading, float speed) {
    this.heading = heading;
    this.speed = speed;
    this.targetSpeed = 0.0f;
    this.targetFuelConsumption = 0.0f;
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
   * Setter for fuel consumption.
   *
   * @param fuelConsumption The specified fuel consumption
   */
  public void setFuelConsumption(Fuel fuelConsumption) {
    this.fuelConsumption = fuelConsumption;
  }

  /**
   * Getter for target fuel consumption.
   *
   * @return Target fuel consumption
   */
  public float getTargetFuelConsumption() {
    return this.targetFuelConsumption;
  }

  /**
   * Setter for target fuel consumption.
   *
   * @param targetFuelConsumption The specified target fuel consumption
   */
  public void setTargetFuelConsumption(float targetFuelConsumption) {
    this.targetFuelConsumption = targetFuelConsumption;
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

  /**
   * Checks if vessel data is valid.
   *
   * @return True if vessel data is valid or false otherwise
   */
  public boolean isValid() {
    return this.heading >= 0 && this.heading <= 360 && this.speed >= 0 && this.speed <= 15;
  }

  /**
   * Checks if generated vessel data is valid.
   *
   * @return True if generated vessel data is valid or false otherwise
   */
  public boolean isGeneratedValid() {
    return this.isValid() && this.speed <= MAX_SPEED;
  }
}
