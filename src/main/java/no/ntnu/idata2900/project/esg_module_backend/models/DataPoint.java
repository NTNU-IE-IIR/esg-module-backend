package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The DataPoint class represents a single data point containing {@link Ship ship} and
 * {@link Weather weather} data, as well as {@link Position position} data.
 *
 * @author Group 14
 * @version v0.2.0 (2025.04.08)
 */
public class DataPoint {
  private Position pos;
  private long ts;
  private Ship ship;
  private Weather weather;

  /**
   * Constructor for the DataPoint class.
   *
   * @param pos The specified position data
   * @param ts  The specified timestamp
   */
  public DataPoint(Position pos, long ts) {
    this.pos = pos;
    this.ts = ts;
    this.ship = null;
    this.weather = null;
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
   * Getter for timestamp.
   *
   * @return Timestamp
   */
  public long getTs() {
    return this.ts;
  }

  /**
   * Getter for ship data.
   *
   * @return Ship data
   */
  public Ship getShip() {
    return this.ship;
  }

  /**
   * Setter for ship data.
   *
   * @param ship The specified ship data
   */
  public void setShip(Ship ship) {
    this.ship = ship;
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
}
