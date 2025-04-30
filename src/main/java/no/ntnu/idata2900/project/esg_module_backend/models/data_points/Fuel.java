package no.ntnu.idata2900.project.esg_module_backend.models.data_points;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Fuel class represents fuel consumption divided into different posts. The class is part of
 * the data packaged into vessel data.
 * 
 * <p>Each post represents an individual section of the vessel where fuel is consumed. The
 * different posts include:</p>
 * 
 * <ul>
 *   <li><b>Drift:</b> Fuel consumption caused by the motors for vessel movement</li>
 *   <li><b>Production:</b> Fuel consumption caused by generating electricity for fish catching
 *   mechanisms</li>
 *   <li><b>Hotel:</b> Fuel consumption caused by generating electricity for vessel housing
 *   operations (e.g. lighting and heating)</li>
 * </ul>
 * 
 * @author Group 14
 * @version v0.1.3 (2025.04.30)
 * @see Vessel
 */
@Entity
@Table(name = "fuel")
@Schema(
  description = "Fuel entity representing fuel consumption data over various posts at a specific "
              + "data point"
)
public class Fuel {

  @Id
  @Column(name = "fuel_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "drift")
  @Schema(description = "Drift post")
  private float drift;

  @Column(name = "production")
  @Schema(description = "Production post")
  private float production;

  @Column(name = "hotel")
  @Schema(description = "Hotel")
  private float hotel;

  @JsonIgnore
  @MapsId
  @OneToOne(mappedBy = "fuelConsumption")
  @JoinColumn(name = "fuel_id")
  @Schema(description = "Vessel data containing this specific fuel data")
  private Vessel vessel;

  /**
   * Default constructor for the Fuel class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public Fuel() {
    // Intentionally left blank
  }

  /**
   * Constructor for the Fuel class.
   * 
   * @param drift      The specified drift fuel consumption
   * @param production The specified production fuel consumption
   * @param hotel      The specified hotel fuel consumption
   */
  public Fuel(float drift, float production, float hotel) {
    this.drift = drift;
    this.production = production;
    this.hotel = hotel;
  }

  /**
   * Getter for fuel consumption caused by drift.
   * 
   * @return Drift fuel consumption
   */
  public float getDrift() {
    return this.drift;
  }

  /**
   * Getter for fuel consumption caused by production.
   * 
   * @return Production fuel consumption
   */
  public float getProduction() {
    return this.production;
  }

  /**
   * Getter for fuel consumption caused by hotel.
   * 
   * @return Hotel fuel consumption
   */
  public float getHotel() {
    return this.hotel;
  }

  /**
   * Getter for vessel data.
   * 
   * @return Vessel data
   */
  public Vessel getVessel() {
    return this.vessel;
  }

  /**
   * Setter for vessel data.
   * 
   * @param vessel The specific vessel data
   */
  public void setVessel(Vessel vessel) {
    this.vessel = vessel;
  }

  /**
   * Returns the total fuel consumption aggregated over all posts.
   * 
   * @return Total fuel consumption
   */
  public float getTotal() {
    return this.drift + this.production + this.hotel;
  }
}
