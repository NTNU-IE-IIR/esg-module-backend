package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The WindWaves class represents various wind wave data. The class is part of the data packaged
 * into {@link MarineWeather marine weather data}.
 * 
 * <p>The data include the following parameters:</p>
 * 
 * <ul>
 *  <li><code>wwavesHeight</code> (<code>m</code>)</li>
 *  <li><code>wwavesDirection</code> (<code>deg</code>)*</li>
 *  <li><code>wwavesPeriod</code> (<code>s</code>)**</li>
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
 * @version v0.1.0 (2025.04.24)
 */
public class WindWaves {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private float wwavesHeight;
  private float wwavesDirection;
  private float wwavesPeriod;

  /**
   * Constructor for the WindWaves class.
   * 
   * @param wwavesHeight    The specified wind waves height
   * @param wwavesDirection The specified wind waves direction
   * @param wwavesPeriod    The specified wind waves period
   */
  public WindWaves(float wwavesHeight, float wwavesDirection, float wwavesPeriod) {
    this.wwavesHeight = wwavesHeight;
    this.wwavesDirection = wwavesDirection;
    this.wwavesPeriod = wwavesPeriod;
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
   * Getter for wind waves height.
   * 
   * @return Wind waves height
   */
  public float getWwavesHeight() {
    return this.wwavesHeight;
  }

  /**
   * Getter for wind waves direction.
   * 
   * @return Wind waves direction
   */
  public float getWwavesDirection() {
    return this.wwavesDirection;
  }

  /**
   * Getter for wind waves period.
   * 
   * @return Wind waves period
   */
  public float getWwavesPeriod() {
    return this.wwavesPeriod;
  }
}
