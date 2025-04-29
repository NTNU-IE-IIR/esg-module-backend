package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
 * <p>The data include further parameters, which are packaged into {@link Waves wave},
 * {@link WindWaves wind wave} and {@link SwellWaves swell wave} data. For documentation of these
 * parameters, see their respective class documentations.</p>
 * 
 * @author Group 14
 * @version v0.1.2 (2025.04.29)
 */
@Entity(name = "marine_weather")
@Schema(
  description = "Marine weather entity representing marine weather data (waves and ocean current) "
              + "at a specific data point"
)
public class MarineWeather {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique ID")
  private Long id;

  @Schema(description = "Wave data")
  private Waves waves;

  @Schema(description = "Wind wave data")
  private WindWaves wwaves;

  @Schema(description = "Swell wave data")
  private SwellWaves swellWaves;

  @Schema(description = "Ocean current speed")
  private float oceanCurrentVelocity;

  @Schema(description = "Ocean current direction")
  private float oceanCurrentDirection;

  /**
   * Constructor for the MarineWeather class.
   * 
   * @param waves                 The specified wave data
   * @param wwaves                The specified wind wave data
   * @param swellWaves            The specified swell wave data
   * @param oceanCurrentVelocity  The specified ocean current velocity
   * @param oceanCurrentDirection The specified ocean current direction
   */
  public MarineWeather(
    Waves waves,
    WindWaves wwaves,
    SwellWaves swellWaves,
    float oceanCurrentVelocity,
    float oceanCurrentDirection
  ) {
    this.waves = waves;
    this.wwaves = wwaves;
    this.swellWaves = swellWaves;
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
   * Getter for wave data.
   * 
   * @return Wave data
   */
  public Waves getWaves() {
    return this.waves;
  }

  /**
   * Getter for wind wave data.
   * 
   * @return Wind wave data
   */
  public WindWaves getWwaves() {
    return this.wwaves;
  }

  /**
   * Getter for swell wave data.
   * 
   * @return Swell wave data
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
}
