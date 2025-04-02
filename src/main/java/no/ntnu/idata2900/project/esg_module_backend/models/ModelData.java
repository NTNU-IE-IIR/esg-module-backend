package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The ModelData class represents data to be used as training and test data for the machine
 * learning model. The data include relevant attributes from the {@link BoatData} and
 * {@link WeatherData} classes.
 * 
 * <p>The data include the following attributes:</p>
 * 
 * <ul>
 *  <li>Average heading</li>
 *  <li>Average speed</li>
 *  <li>Fuel consumption</li>
 *  <li>Average fish amount</li>
 *  <li>Average wind gust (10 m)</li>
 *  <li>Average wind speed (10 m)</li>
 *  <li>Average wind direction (10 m)</li>
 *  <li>Average wave height</li>
 *  <li>Average wave direction</li>
 *  <li>Average wind wave height</li>
 *  <li>Average wind wave direction</li>
 *  <li>Average swell wave height</li>
 *  <li>Average swell wave direction</li>
 *  <li>Average ocean current velocity</li>
 *  <li>Average ocean current direction</li>
 * </ul>
 * 
 * Each intance of this class represents the average values between two data points. The only
 * exception is the fuel consumption attribute, which represents the fuel consumed between two
 * datapoints.
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.02)
 */
public class ModelData {
  // Boat data
  private double heading;
  private double speed;
  private double fuelConsumption;
  private double fishAmount;
  // Weather data
  private double windGust;
  private double windSpeed;
  private int windDirection;
  // Marine weather data
  private double waveHeight;
  private int waveDirection;
  private double windWaveHeight;
  private int windWaveDirection;
  private double swellWaveHeight;
  private int swellWaveDirection;
  private double oceanCurrentVelocity;
  private int oceanCurrentDirection;

  /**
   * Constructor for ModelData class.
   * 
   * @param heading The specified average heading
   * @param speed The specified average speed
   * @param fuelConsumption The specified fuel consumption
   * @param fishAmount The specified average fish amount
   * @param windGust The specified average wind gust
   * @param windSpeed The specified average wind speed
   * @param windDirection The specified average wind direction
   * @param waveHeight The specified average wave height
   * @param waveDirection The specified average wave direction
   * @param windWaveHeight The specified average wind wave height
   * @param windWaveDirection The specified average wind wave direction
   * @param swellWaveHeight The specified average swell wave height
   * @param swellWaveDirection The specified average swell wave direction
   * @param oceanCurrentVelocity The specified average ocean current velocity
   * @param oceanCurrentDirection The specified average ocean current direction
   */
  public ModelData(
    double heading,
    double speed,
    double fuelConsumption,
    double fishAmount,
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
    this.heading = heading;
    this.speed = speed;
    this.fuelConsumption = fuelConsumption;
    this.fishAmount = fishAmount;
    this.windGust = windGust;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.waveHeight = waveHeight;
    this.waveDirection = waveDirection;
    this.windWaveHeight = windWaveHeight;
    this.windWaveDirection = windWaveDirection;
    this.swellWaveHeight = swellWaveHeight;
    this.swellWaveDirection = swellWaveDirection;
    this.oceanCurrentVelocity = oceanCurrentVelocity;
    this.oceanCurrentDirection = oceanCurrentDirection;
  }

  /**
   * Getter for heading. Note that this returns average heading.
   * 
   * @return Average heading
   */
  public double getHeading() {
    return this.heading;
  }

  /**
   * Getter for speed. Note that this returns average speed.
   * 
   * @return Average speed
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Getter for fuel consumption.
   * 
   * @return Fuel consumption
   */
  public double getFuelConsumption() {
    return this.fuelConsumption;
  }

  /**
   * Getter for fish amount. Note that this returns average fish amount.
   * 
   * @return Average fish amount
   */
  public double getFishAmount() {
    return this.fishAmount;
  }

  /**
   * Getter for wind gust. Note that this returns average wind gust.
   * 
   * @return Average wind gust
   */
  public double getWindGust() {
    return this.windGust;
  }

  /**
   * Getter for wind speed. Note that this returns average wind speed.
   * 
   * @return Average wind speed
   */
  public double getWindSpeed() {
    return this.windSpeed;
  }

  /**
   * Getter for wind direction. Note that this returns average wind direction.
   * 
   * @return Average wind direction
   */
  public int getWindDirection() {
    return this.windDirection;
  }

  /**
   * Getter for wave height. Note that this returns average wave height.
   * 
   * @return Average wave height
   */
  public double getWaveHeight() {
    return this.waveHeight;
  }

  /**
   * Getter for wave direction. Note that this returns average wave direction.
   * 
   * @return Average wave direction
   */
  public int getWaveDirection() {
    return this.waveDirection;
  }

  /**
   * Getter for wind wave height. Note that this returns average wind wave height.
   * 
   * @return Average wind wave height
   */
  public double getWindWaveHeight() {
    return this.windWaveHeight;
  }

  /**
   * Getter for wind wave direction. Note that this returns average wind wave direction.
   * 
   * @return Average wind wave direction
   */
  public int getWindWaveDirection() {
    return this.windWaveDirection;
  }

  /**
   * Getter for swell wave height. Note that this returns average swell wave height.
   * 
   * @return Average swell wave height
   */
  public double getSwellWaveHeight() {
    return this.swellWaveHeight;
  }

  /**
   * Getter for swell wave direction. Note that this returns average swell wave direction.
   * 
   * @return Average swell wave direction
   */
  public int getSwellWaveDirection() {
    return this.swellWaveDirection;
  }

  /**
   * Getter for ocean current velocity. Note that this returns average ocean current velocity.
   * 
   * @return Average heading
   */
  public double getOceanCurrentVelocity() {
    return this.oceanCurrentVelocity;
  }

  /**
   * Getter for ocean current direction. Note that this returns average ocean current direction.
   * 
   * @return Average ocean current direction
   */
  public int getOceanCurrentDirection() {
    return this.oceanCurrentDirection;
  }
}
