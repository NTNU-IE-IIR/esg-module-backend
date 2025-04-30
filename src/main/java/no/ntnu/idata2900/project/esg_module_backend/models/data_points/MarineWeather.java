package no.ntnu.idata2900.project.esg_module_backend.models.data_points;

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
 * The MarineWeather class represents various marine weather data. The class is part of the data
 * packaged into a {@link DataPoint data point}.
 * 
 * <p>The data include the following parameters:</p>
 * 
 * <ul>
 *  <li><code>oceanCurrentVelocity</code> (<code>m/s</code>)</li>
 *  <li><code>oceanCurrentDirection</code> (<code>deg</code>)*</li>
 * </ul>
 * 
 * <p>* <code>Direction</code> defines where the parameter comes from (0 = North, 90 = East,
 * 180 = South, 270 = West).</p>
 * 
 * <p>The data are based off of marine forecast data from
 * <a href="https://open-meteo.com/en/docs/marine-weather-api">Marine Weather API</a> distributed
 * by <a href="https://open-meteo.com/">Open-meteo</a>.</p>
 * 
 * <p>The data include further parameters, which are packaged into {@link Waves waves},
 * {@link WindWaves wind waves} and {@link SwellWaves swell waves} data. For documentation of these
 * parameters, see their respective class documentations.</p>
 * 
 * @author Group 14
 * @version v0.1.5 (2025.04.30)
 */
@Entity
@Table(name = "marine_weather")
@Schema(
  description = "Marine weather entity representing marine weather data (waves and ocean current) "
              + "at a specific data point"
)
public class MarineWeather {

  @Id
  @Column(name = "marine_weather_id")
  @Schema(description = "Unique ID")
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Waves data")
  private Waves waves;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Wind waves data")
  private WindWaves wwaves;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @Schema(description = "Swell waves data")
  private SwellWaves swellWaves;

  @Column(name = "ocean_current_vel")
  @Schema(description = "Ocean current speed")
  private float oceanCurrentVelocity;

  @Column(name = "ocean_current_dir")
  @Schema(description = "Ocean current direction")
  private float oceanCurrentDirection;

  @JsonIgnore
  @MapsId
  @OneToOne(mappedBy = "marineWeather")
  @JoinColumn(name = "marine_weather_id")
  @Schema(description = "Data point containing this specific marine weather data")
  private DataPoint dp;

  /**
   * Default constructor for the MarineWeather class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public MarineWeather() {
    // Intentionally left blank
  }

  /**
   * Constructor for the MarineWeather class.
   * 
   * @param oceanCurrentVelocity  The specified ocean current velocity
   * @param oceanCurrentDirection The specified ocean current direction
   */
  public MarineWeather(float oceanCurrentVelocity, float oceanCurrentDirection) {
    this.oceanCurrentVelocity = oceanCurrentVelocity;
    this.oceanCurrentDirection = oceanCurrentDirection;
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
   * Getter for waves data.
   * 
   * @return Waves data
   */
  public Waves getWaves() {
    return this.waves;
  }

  /**
   * Getter for wind waves data.
   * 
   * @return Wind waves data
   */
  public WindWaves getWwaves() {
    return this.wwaves;
  }

  /**
   * Getter for swell waves data.
   * 
   * @return Swell waves data
   */
  public SwellWaves getSwellWaves() {
    return this.swellWaves;
  }

  /**
   * Getter for ocean current velocity.
   * 
   * @return Ocean current velocity
   */
  public float getOceanCurrentVelocity() {
    return this.oceanCurrentVelocity;
  }

  /**
   * Getter for ocean current direction.
   * 
   * @return Ocean current direction
   */
  public float getOceanCurrentDirection() {
    return this.oceanCurrentDirection;
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
