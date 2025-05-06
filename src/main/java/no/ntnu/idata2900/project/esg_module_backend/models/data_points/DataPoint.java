package no.ntnu.idata2900.project.esg_module_backend.models.data_points;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;

/**
 * The DataPoint class represents a single data point containing {@link Vessel vessel} and
 * {@link Weather weather} data, as well as {@link Position position} data.
 *
 * @author Group 14
 * @version v0.2.8 (2025.05.01)
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

  @OneToOne(mappedBy = "dp")
  @Schema(description = "Position represented as coordinates")
  private Position pos;

  @OneToOne(mappedBy = "dp")
  @Schema(description = "Vessel data")
  private Vessel vessel;

  @OneToOne(mappedBy = "dp")
  @Schema(description = "Weather data (wind)")
  private Weather weather;

  @OneToOne(mappedBy = "dp")
  @Schema(description = "Marine weather data (waves and ocean current)")
  private MarineWeather marineWeather;

  @JsonIgnore
  @JoinColumn(name = "trip_id")
  @ManyToOne
  @Schema(description = "Trip containing this specific data point")
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

  public void setPos(Position pos) {
    this.pos = pos;
  }

  /**
   * Getter for vessel data.
   *
   * @return Vessel data
   */
  public Vessel getVessel() {
    return this.vessel;
  }

  public void setVessel(Vessel vessel) {
    this.vessel = vessel;
  }

  /**
   * Getter for weather data.
   *
   * @return Weather data
   */
  public Weather getWeather() {
    return this.weather;
  }

  public void setWeather(Weather weather) {
    this.weather = weather;
  }

  /**
   * Getter for marine weather data.
   * 
   * @return Marine weather data
   */
  public MarineWeather getMarineWeather() {
    return this.marineWeather;
  }

  public void setMarineWeather(MarineWeather marineWeather) {
    this.marineWeather = marineWeather;
  }

  /**
   * Getter for trip.
   *
   * @return Trip.
   */
  public Trip getTrip() {
    return trip;
  }

  /**
   * Setter for trip.
   *
   * @param trip The specified trip
   */
  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  /**
   * Checks if data point is valid.
   * 
   * @return True if data point is valid or false otherwise
   */
  public boolean isValid() {
    return this.ts >= 0;
  }

  @Override
  public String toString() {
    return "DataPoint{" +
        "id=" + id +
        ", ts=" + ts +
        ", pos=" + pos +
        ", vessel=" + vessel +
        ", weather=" + weather +
        ", marineWeather=" + marineWeather +
        ", trip=" + trip +
        '}';
  }
}
