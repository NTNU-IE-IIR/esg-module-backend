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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import no.ntnu.idata2900.project.backend.models.Trip;

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

  @OneToOne(mappedBy = "dp", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(description = "Position represented as coordinates")
  private Position pos;

  @OneToOne(mappedBy = "dp", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(description = "Vessel data")
  private Vessel vessel;

  @OneToOne(mappedBy = "dp", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(description = "Weather data (wind)")
  private Weather weather;

  @OneToOne(mappedBy = "dp", cascade = CascadeType.ALL, orphanRemoval = true)
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

  /**
   * Setter for position data.
   *
   * @param pos The specified position data
   */
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

  /**
   * Setter for vessel data.
   *
   * @param vessel The specified vessel data
   */
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

  /**
   * Setter for weather data.
   *
   * @param weather The specified weather data
   */
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

  /**
   * Setter for marine weather data.
   *
   * @param marineWeather The specified marine weather data
   */
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
    return "DataPoint{"
         + "id=" + id
         + ", ts=" + ts
         + ", pos=" + pos
         + ", vessel=" + vessel
         + ", weather=" + weather
         + ", marineWeather=" + marineWeather
         + ", trip=" + trip
         + "}";
  }
}
