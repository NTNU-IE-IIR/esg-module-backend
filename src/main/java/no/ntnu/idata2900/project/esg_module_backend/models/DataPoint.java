package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The DataPoint class represents a single data point containing {@link Ship ship} and
 * {@link Weather weather} data, as well as {@link Position position} data.
 *
 * @author Group 14
 * @version v0.2.3 (2025.04.29)
 */
@Entity(name = "data_point")
public class DataPoint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private long ts;
  private Position pos;
  private Ship ship;
  private Weather weather;
  private MarineWeather marineWeather;

  /**
   * Constructor for the DataPoint class.
   *
   * @param ts  The specified timestamp
   * @param pos The specified position data
   */
  public DataPoint(long ts, Position pos) {
    this.pos = pos;
    this.ts = ts;
    this.ship = null;
    this.weather = null;
    this.marineWeather = null;
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

  /**
   * Getter for marine weather data.
   * 
   * @return Marine weather data
   */
  public MarineWeather getMarineWeather() {
    return this.marineWeather;
  }

  /**
   * Setter for marine weather.
   * 
   * @param marineWeather The specified marine weather data
   */
  public void setMarineWeather(MarineWeather marineWeather) {
    this.marineWeather = marineWeather;
  }
}
