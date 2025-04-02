package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The WeatherData class represents various weather data useful for marine operations. The data is
 * based off of weather and marine weather data fetched from
 * <a href="https://open-meteo.com/en/docs">Weather Forecast API</a> and
 * <a href="https://open-meteo.com/en/docs/marine-weather-api">Marine Weather API</a> respectively,
 * distributed by <a href="https://open-meteo.com/">Open-meteo</a>.
 * 
 * <p>The data include the following attributes:</p>
 * 
 * <ul>
 *  <li>Timestamp</li>
 *  <li>Wind gust (10 m)</li>
 *  <li>Wind speed (10 m)</li>
 *  <li>Wind direction (10 m)</li>
 *  <li>Wave height</li>
 *  <li>Wave direction</li>
 *  <li>Wind wave height</li>
 *  <li>Wind wave direction</li>
 *  <li>Swell wave height</li>
 *  <li>Swell wave direction</li>
 *  <li>Ocean current velocity</li>
 *  <li>Ocean current direction</li>
 * </ul>
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.02)
 */
public class WeatherData {
  // General data
  private String timestamp;
  // Weather data
  private double windGust;
  private double windSpeed;
  private int windDirection;
  private double waveHeight;
  private int waveDirection;
  private double windWaveHeight;
  private int windWaveDirection;
  private double swellWaveHeight;
  private int swellWaveDirection;
  private double oceanCurrentVelocity;
  private int oceanCurrentDirection;

  /**
   * Constructor for WeatherData class.
   * 
   * @param timestamp The specified timestamp
   * @param windGust The specified wind gust
   * @param windSpeed The specified wind speed
   * @param windDirection The specified wind direction
   * @param waveHeight The specified wave height
   * @param waveDirection The specified wave direction
   * @param windWaveHeight The specified wind wave height
   * @param windWaveDirection The specified wind wave direction
   * @param swellWaveHeight The specified swell wave height
   * @param swellWaveDirection The specified swell wave direction
   * @param oceanCurrentVelocity The specified ocean current velocity
   * @param oceanCurrentDirection The specified ocean current direction
   */
  public WeatherData(
    String timestamp,
    double windGust,
    double windSpeed,
    int windDirection,
    double waveHeight,
    int waveDirection,
    double windWaveHeight,
    int windWaveDirection,
    double swellWaveHeight,
    int swellWaveDirection,
    double oceanCurrentVelocity,
    int oceanCurrentDirection
  ) {
    this.timestamp = timestamp;
    this.windGust = windGust;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.waveHeight = waveHeight;
    this.waveDirection = waveDirection;
    this.windWaveHeight = windWaveHeight;
    this.swellWaveHeight = swellWaveHeight;
    this.swellWaveDirection = swellWaveDirection;
    this.oceanCurrentVelocity = oceanCurrentVelocity;
    this.oceanCurrentDirection = oceanCurrentDirection;
  }

  /**
   * Getter for timestamp.
   * 
   * @return Timestamp
   */
  public String getTimestamp() {
    return this.timestamp;
  }

  /**
   * Getter for wind gust.
   * 
   * @return Wind gust
   */
  public double getWindGust() {
    return this.windGust;
  }

  /**
   * Getter for wind speed.
   * 
   * @return Wind speed
   */
  public double getWindSpeed() {
    return this.windSpeed;
  }

  /**
   * Getter for wind direction.
   * 
   * @return Wind direction
   */
  public int getWindDirection() {
    return this.windDirection;
  }

  /**
   * Getter for wave height.
   * 
   * @return Wave height
   */
  public double getWaveHeight() {
    return this.waveHeight;
  }

  /**
   * Getter for wave direction.
   * 
   * @return Wave direction
   */
  public int getWaveDirection() {
    return this.waveDirection;
  }

  /**
   * Getter for wind wave height.
   * 
   * @return Wind wave height
   */
  public double getWindWaveHeight() {
    return this.windWaveHeight;
  }

  /**
   * Getter for wind wave direction.
   * 
   * @return Wind wave direction
   */
  public int getWindWaveDirection() {
    return this.windWaveDirection;
  }

  /**
   * Getter for swell wave height.
   * 
   * @return Swell wave height
   */
  public double getSwellWaveHeight() {
    return this.swellWaveHeight;
  }

  /**
   * Getter for swell wave direction.
   * 
   * @return Swell wave direction
   */
  public int getSwellWaveDirection() {
    return this.swellWaveDirection;
  }

  /**
   * Getter for ocean current velocity.
   * 
   * @return Ocean current velocity
   */
  public double getOceanCurrentVelocity() {
    return this.oceanCurrentVelocity;
  }

  /**
   * Getter for ocean current direction.
   * 
   * @return Ocean current direction
   */
  public int getOceanCurrentDirection() {
    return this.oceanCurrentDirection;
  }
}
