package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The WeatherData class represents various weather data collected from different weather APIs. The
 * class is part of the data packaged into a {@link DataPoint data point}.
 *
 * <p>
 * The data is based off of forecast data from <a href="https://api.windy.com/">Windy API</a>
 * distributed by <a href="https://www.windy.com/">Windy</a>.
 * </p>
 *
 * <p>
 * The <code>oceanCurrentVelocity</code> and <code>oceanCurrentDirection</code> parameteres are
 * based off of marine forecast data from
 * <a href="https://open-meteo.com/en/docs/marine-weather-api">Marine Weather API</a> distributed
 * by <a href="https://open-meteo.com/">Open-meteo</a>.
 * </p>
 *
 * <p>The data include the following parameters:</p>
 *
 * <ul>
 *   <li>
 *     <code>windU</code> (<code>m/s</code>): Wind speed and direction defined by a vector
 *     <code>u</code> (West towards East)
 *   </li>
 *   <li>
 *     <code>windV</code> (<code>m/s</code>): Wind speed and direction defined by a vector
 *     <code>v</code> (South towards North)
 *   </li>
 *   <li>
 *     <code>gust</code> (<code>m/s</code>): Speed of wind at gusts
 *   </li>
 *   <li>
 *     <code>wavesHeight</code> (<code>m</code>): Height of waves
 *   </li>
 *   <li>
 *     <code>wavesDirection</code> (<code>deg</code>): Direction* of waves
 *   </li>
 *   <li>
 *     <code>wavesPeriod</code> (<code>s</code>): Period** of waves
 *   </li>
 *   <li>
 *     <code>wwavesHeight</code> (<code>m</code>): Height of wind waves
 *   </li>
 *   <li>
 *     <code>wwavesDirection</code> (<code>deg</code>): Direction* of wind waves
 *   </li>
 *   <li>
 *     <code>wwavesPeriod</code> (<code>s</code>): Period** of wind waves
 *   </li>
 *   <li>
 *     <code>swell1Height</code> (<code>m</code>): Height of class 1 swell waves
 *   </li>
 *   <li>
 *     <code>swell1Direction</code> (<code>deg</code>): Direction* of class 1 swell waves
 *   </li>
 *   <li>
 *     <code>swell1Period</code> (<code>s</code>): Period** of class 1 swell waves
 *   </li>
 *   <li>
 *     <code>swell2Height</code> (<code>m</code>): Height of class 2 swell waves
 *   </li>
 *   <li>
 *     <code>swell2Direction</code> (<code>deg</code>): Direction* of class 2 swell waves
 *   </li>
 *   <li>
 *     <code>swell2Period</code> (<code>s</code>): Period** of class 2 swell waves
 *   </li>
 *   <li>
 *     <code>oceanCurrentVelocity</code> (<code>m/s</code>): Velocity of ocean current
 *   </li>
 *   <li>
 *     <code>oceanCurrentDirection</code> (<code>deg</code>): Direction* of ocean current
 *   </li>
 * </ul>
 *
 * <p>
 *   * Direction defines where the parameter comes from (0 = North, 90 = East, 180 = South,
 *   270 = West).
 * </p>
 *
 * <p>
 *   ** Period defines the time interval between arrival of consecutive crests at a stationary
 *   point.
 * </p>
 *
 * <p>
 *   For further documentation of parameters from <a href="https://api.windy.com/">Windy API</a>,
 *   see <a href="https://api.windy.com/point-forecast/docs">Windy API documentation</a>.
 * </p>
 *
 * @author Group 14
 * @version v0.2.0 (2025.04.08)
 */
public class Weather {
  // TODO Change to automatically generated ID
  private int id;

  // GFS
  private float windU;
  private float windV;
  private float gust;

  // GFS Wave
  private float wavesHeight;
  private float wavesDirection;
  private float wavesPeriod;

  private float wwavesHeight;
  private float wwavesDirection;
  private float wwavesPeriod;

  private float swell1Height;
  private float swell1Direction;
  private float swell1Period;

  private float swell2Height;
  private float swell2Direction;
  private float swell2Period;

  // Marine Weather API
  private float oceanCurrentVelocity;
  private float oceanCurrentDirection;

  /**
   * Constructor for WeatherData class.
   *
   * @param id                    The specified ID
   * @param windU                 The specified wind <code>u</code> vector
   * @param windV                 The specified wind <code>v</code> vector
   * @param gust                  The specified wind gusts speed
   * @param wavesHeight           The specified waves height
   * @param wavesDirection        The specified waves direction
   * @param wavesPeriod           The specified waves period
   * @param wwavesHeight          The specified wind waves height
   * @param wwavesDirection       The specified wind waves direction
   * @param wwavesPeriod          The specified wind waves period
   * @param swell1Height          The specified class 1 swell waves height
   * @param swell1Direction       The specified class 1 swell waves direction
   * @param swell1Period          The specified class 1 swell waves period
   * @param swell2Height          The specified class 2 swell waves height
   * @param swell2Direction       The speicifed class 2 swell waves direction
   * @param swell2Period          The specified class 2 swell waves period
   * @param oceanCurrentVelocity  The specified ocean current velocity
   * @param oceanCurrentDirection The specified ocean current direction
   */
  public Weather(
      int id,
      float windU,
      float windV,
      float gust,
      float wavesHeight,
      float wavesDirection,
      float wavesPeriod,
      float wwavesHeight,
      float wwavesDirection,
      float wwavesPeriod,
      float swell1Height,
      float swell1Direction,
      float swell1Period,
      float swell2Height,
      float swell2Direction,
      float swell2Period,
      float oceanCurrentVelocity,
      float oceanCurrentDirection
  ) {
    this.id = id;
    this.windU = windU;
    this.windV = windV;
    this.gust = gust;
    this.wavesHeight = wavesHeight;
    this.wavesDirection = wavesDirection;
    this.wavesPeriod = wavesPeriod;
    this.wwavesHeight = wwavesHeight;
    this.wwavesDirection = wwavesDirection;
    this.wwavesPeriod = wwavesPeriod;
    this.swell1Height = swell1Height;
    this.swell1Direction = swell1Direction;
    this.swell1Period = swell1Period;
    this.swell2Height = swell2Height;
    this.swell2Direction = swell2Direction;
    this.swell2Period = swell2Period;
    this.oceanCurrentVelocity = oceanCurrentVelocity;
    this.oceanCurrentDirection = oceanCurrentDirection;
  }

  /**
   * Getter for ID.
   *
   * @return ID
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter for wind <code>u</code> vector.
   *
   * @return Wind <code>u</code> vector
   */
  public float getWindU() {
    return this.windU;
  }

  /**
   * Getter for wind <code>v</code> vector.
   *
   * @return Wind <code>v</code> vector
   */
  public float getWindV() {
    return this.windV;
  }

  /**
   * Getter for wind gusts speed.
   *
   * @return Wind gusts speed
   */
  public float getGust() {
    return this.gust;
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
   * Getter for waves period.
   *
   * @return Waves period
   */
  public float getWavesPeriod() {
    return this.wavesPeriod;
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

  /**
   * Getter for ocean current velocity.
   *
   * @return Ocean current velocity
   */
  public float oceanCurrentVelocity() {
    return this.oceanCurrentVelocity;
  }

  /**
   * Getter for ocean current direction.
   *
   * @return Ocean current direction
   */
  public float oceanCurrentDirection() {
    return this.oceanCurrentDirection;
  }
}
