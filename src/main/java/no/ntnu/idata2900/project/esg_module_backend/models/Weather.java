package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * @version v0.3.2 (2025.04.29)
 */
@Entity(name = "weather")
@Schema(description = "Weather entity representing weather data (wind) at a specific data point")
public class Weather {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique ID")
  private Long id;

  @Schema(description = "Wind u vector")
  private float windU;

  @Schema(description = "Wind v vector")
  private float windV;

  @Schema(description = "Wind gusts speed")
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
