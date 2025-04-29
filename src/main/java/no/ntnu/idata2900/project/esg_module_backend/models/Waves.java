package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Waves class represents various wave data. The class is part of the data packaged into
 * {@link MarineWeather marine weather data}.
 * 
 * <p>The data include the following parameters:</p>
 * 
 * <ul>
 *  <li><code>wavesHeight</code> (<code>m</code>)</li>
 *  <li><code>wavesDirection</code> (<code>deg</code>)*</li>
 *  <li><code>wavesPeriod</code> (<code>s</code>)**</li>
 * </ul>
 * 
 * <p>* <code>Direction</code> defines where the parameter comes from (0 = North, 90 = East,
 * 180 = South, 270 = West).</p>
 * 
 * <p>** <code>Period</code> defines the time interval between arrival of consecutive waves at the
 * target {@link Position position}.</p>
 * 
 * <p>The data are based off of forecast data from <a href="https://api.windy.com/">Windy API</a>
 * distributed by <a href="https://www.windy.com/">Windy</a>. For further documentation of
 * parameters, see
 * <a href="https://api.windy.com/point-forecast/docs">Windy API documentation</a>.</p>
 * 
 * @author Group 14
 * @version v0.1.2 (2025.04.29)
 */
@Entity(name = "waves")
@Schema(description = "Waves entity representing waves data at a specific data point")
public class Waves {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique ID")
  public Long id;

  @Schema(description = "Waves height")
  private float wavesHeight;

  @Schema(description = "Waves direction")
  private float wavesDirection;

  @Schema(
    description = "Waves period represented as time interval between arrival of consecutive waves"
  )
  private float wavesPeriod;

  /**
   * Constructor for the Waves class.
   * 
   * @param wavesHeight     The specified waves height
   * @param wavesDirection  The specified waves direction
   * @param wavesPeriod     The specified waves period
   */
  public Waves(float wavesHeight, float wavesDirection, float wavesPeriod) {
    this.wavesHeight = wavesHeight;
    this.wavesDirection = wavesDirection;
    this.wavesPeriod = wavesPeriod;
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
   * Getter for waves height.
   * 
   * @return Waves height
   */
  public float getWavesHeight() {
    return this.wavesHeight;
  }

  /**
   * Getter for waves direction.
   * 
   * @return Waves direction
   */
  public float getWavesDirection() {
    return this.wavesDirection;
  }

  /**
   * Getter for waves period
   * 
   * @return Waves period
   */
  public float wavesPeriod() {
    return this.wavesPeriod;
  }
}
