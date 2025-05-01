package no.ntnu.idata2900.project.esg_module_backend.models.data_points;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * The DataPoint class represents a single data point containing {@link Vessel vessel} and
 * {@link Weather weather} data, as well as {@link Position position} data.
 *
 * @author Group 14
 * @version v0.2.7 (2025.04.30)
 */
@Entity
@Table(name = "data_point")
@Schema(description = "Data point entity representing a specific data point")
public class DataPoint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "data_point_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "timestamp")
  @Schema(description = "Timestamp")
  private long ts;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Position data represented as coordinates")
  private Position pos;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Vessel data")
  private Vessel vessel;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Weather data (wind)")
  private Weather weather;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Marine weather data (waves and ocean current)")
  private MarineWeather marineWeather;

  @ManyToOne(cascade = CascadeType.ALL)
  @Schema(description = "Trip data")
  @JsonIgnore
  private Trip trip;

  /**
   * Default constructor for the DataPoint class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public DataPoint() {
    // Intentionally left blank
  }

  /**
   * Constructor for the DataPoint class.
   *
   * @param ts The specified timestamp
   */
  public DataPoint(long ts) {
    this.ts = ts;
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
   * Getter for timestamp.
   *
   * @return Timestamp
   */
  public long getTs() {
    return this.ts;
  }

  /**
   * Getter for position data.
   *
   * @return Position data
   */
  public Position getPos() {
    return this.pos;
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
   * Getter for weather data.
   *
   * @return Weather data
   */
  public Weather getWeather() {
    return this.weather;
  }

  /**
   * Getter for marine weather data.
   * 
   * @return Marine weather data
   */
  public MarineWeather getMarineWeather() {
    return this.marineWeather;
  }


  /**
   * Getter for trip data.
   *
   * @return The associated trip data.
   */
  public Trip getTrip() {
    return trip;
  }

  /**
   * Sets the trip associated with this data point.
   *
   * @param trip the trip data to associate with this data point
   */
  public void setTrip(Trip trip) {
    this.trip = trip;
  }
}
