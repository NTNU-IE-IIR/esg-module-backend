package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Weather class represents various weather data. The class is part of the data packaged into a
 * {@link DataPoint data point}.
 *
 * <p>The data include the following parameters:</p>
 *
 * <ul>
 *   <li><code>windU</code> (<code>m/s</code>)</li>
 *   <li><code>windV</code> (<code>m/s</code>)</li>
 *   <li><code>gust</code> (<code>m/s</code>)</li>
 * </ul>
 * 
 * <p>Notice how wind is divided into two vectors: <code>u</code> and <code>v</code>. The
 * <code>u</code> vector represents wind speed and direction from West towards East. The
 * <code>v</code> vector represents wind speed and direction from South towards North.</p>
 *
 * <p>The data are based off of forecast data from <a href="https://api.windy.com/">Windy API</a>
 * distributed by <a href="https://www.windy.com/">Windy</a>. For further documentation of
 * parameters, see
 * <a href="https://api.windy.com/point-forecast/docs">Windy API documentation</a>.</p>
 *
 * @author Group 14
 * @version v0.3.1 (2025.04.29)
 */
@Entity(name = "weather")
public class Weather {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private float windU;
  private float windV;
  private float gust;

  /**
   * Constructor for the Weather class.
   *
   * @param windU The specified wind <code>u</code> vector
   * @param windV The specified wind <code>v</code> vector
   * @param gust  The specified wind gusts speed
   */
  public Weather(
      float windU,
      float windV,
      float gust
  ) {
    this.windU = windU;
    this.windV = windV;
    this.gust = gust;
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
}
