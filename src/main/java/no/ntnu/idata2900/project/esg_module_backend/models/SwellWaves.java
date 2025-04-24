package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The SwellWaves class represents various swell wave data. The class is part of the data packaged
 * into {@link MarineWeather marine weather data}.
 * 
 * <p>The data include the following parameters:</p>
 * 
 * <ul>
 *  <li><code>swell1Height</code> (<code>m</code>)</li>
 *  <li><code>swell1Direction</code> (<code>deg</code>)*</li>
 *  <li><code>swell1Period</code> (<code>s</code>)**</li>
 *  <li><code>swell2Height</code> (<code>m</code>)</li>
 *  <li><code>swell2Direction</code> (<code>deg</code>)*</li>
 *  <li><code>swell2Period</code> (<code>s</code>)**</li>
 * </ul>
 * 
 * <p>* <code>Direction</code> defines where the parameter comes from (0 = North, 90 = East,
 * 180 = South, 270 = West).</p>
 * 
 * <p>** <code>Period</code> defines the time interval between arrival of consecutive waves at the
 * target {@link Position position}.</p>
 * 
 * <p>Notice how the parameteres are divided into two classes: class 1 and class 2. Their
 * difference stems from their direction of origin.</p>
 * 
 * <p>The data are based off of forecast data from <a href="https://api.windy.com/">Windy API</a>
 * distributed by <a href="https://www.windy.com/">Windy</a>. For further documentation of
 * parameters, see
 * <a href="https://api.windy.com/point-forecast/docs">Windy API documentation</a>.</p>
 * 
 * @author Group 14
 * @version v0.1.0 (2025.04.24)
 */
public class SwellWaves {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  private float swell1Height;
  private float swell1Direction;
  private float swell1Period;
  private float swell2Height;
  private float swell2Direction;
  private float swell2Period;

  /**
   * Constructor for the SwellWaves class.
   * 
   * @param swell1Height    The specified class 1 swell waves height
   * @param swell1Direction The specified class 1 swell waves direction
   * @param swell1Period    The specified class 1 swell waves period
   * @param swell2Height    The specified class 2 swell waves height
   * @param swell2Direction The specified class 2 swell waves direction
   * @param swell2Period    The specified class 2 swell waves period
   */
  public SwellWaves(
    float swell1Height,
    float swell1Direction,
    float swell1Period,
    float swell2Height,
    float swell2Direction,
    float swell2Period
  ) {
    this.swell1Height = swell1Height;
    this.swell1Direction = swell1Direction;
    this.swell1Period = swell1Period;
    this.swell2Height = swell2Height;
    this.swell2Direction = swell2Direction;
    this.swell2Period = swell2Period;
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
   * Getter for class 1 swell waves height.
   * 
   * @return Class 1 swell waves height
   */
  public float getSwell1Height() {
    return this.swell1Height;
  }

  /**
   * Getter for class 1 swell waves direction.
   * 
   * @return Class 1 swell waves direction
   */
  public float getSwell1Direction() {
    return this.swell1Direction;
  }

  /**
   * Getter for class 1 swell waves period.
   * 
   * @return Class 1 swell waves period
   */
  public float getSwell1Period() {
    return this.swell1Period;
  }

  /**
   * Getter for class 2 swell waves height.
   * 
   * @return Class 2 swell waves height
   */
  public float getSwell2Height() {
    return this.swell2Height;
  }

  /**
   * Getter for class 2 swell waves direction.
   * 
   * @return Class 2 swell waves direction
   */
  public float getSwell2Direction() {
    return this.swell2Direction;
  }

  /**
   * Getter for class 2 swell waves period.
   * 
   * @return Class 2 swell waves period
   */
  public float getSwell2Period() {
    return this.swell2Period;
  }
}
