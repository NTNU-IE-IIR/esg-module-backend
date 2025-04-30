package no.ntnu.idata2900.project.esg_module_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
 * @version v0.1.2 (2025.04.30)
 */
@Entity
@Table(name = "wind_waves")
@Schema(description = "Wind waves entity representing wind waves data at a specific data point")
public class WindWaves {

  @Id
  @Column(name = "wind_waves_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "wind_waves_height")
  @Schema(description = "Wind waves height")
  private float wwavesHeight;

  @Column(name = "wind_waves_dir")
  @Schema(description = "Wind waves direction")
  private float wwavesDirection;

  @Column(name = "wind_waves_period")
  @Schema(
    description = "Wind waves period represented as time interval between arrival of consecutive "
                + "wind waves"
  )
  private float wwavesPeriod;

  @JsonIgnore
  @MapsId
  @OneToOne(mappedBy = "wwaves")
  @JoinColumn(name = "wind_waves_id")
  @Schema(description = "Marine weather data containing this specific wind waves data")
  private MarineWeather marineWeather;

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

  /**
   * Getter for marine weather data.
   * 
   * @return Marine weather data
   */
  public MarineWeather getMarineWeather() {
    return this.marineWeather;
  }

  /**
   * Setter for marine weather data.
   * 
   * @param marineWeather The specified marine weather data
   */
  public void setMarineWeather(MarineWeather marineWeather) {
    this.marineWeather = marineWeather;
  }
}
