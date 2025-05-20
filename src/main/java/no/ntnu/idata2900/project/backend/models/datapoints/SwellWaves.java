package no.ntnu.idata2900.project.backend.models.datapoints;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The SwellWaves class represents various swell waves data. The class is part of the data packaged
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
 * @version v0.2.0 (2025.05.02)
 */
@Entity
@Table(name = "swell_waves")
@Schema(description = "Swell waves entity representing swell waves data at a specific data point")
public class SwellWaves {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "swell_waves_id")
  @Schema(description = "Unique ID")
  public Long id;

  @Column(name = "swell1_height")
  @Schema(description = "Class 1 swell waves height")
  private float swell1Height;

  @Column(name = "swell1_dir")
  @Schema(description = "Class 1 swell waves direction")
  private float swell1Direction;

  @Column(name = "swell1_period")
  @Schema(
      description = "Class 1 swell waves period represented as time interval between arrival of "
                  + "consecutive class 1 swell waves"
  )
  private float swell1Period;

  @Column(name = "swell2_height")
  @Schema(description = "Class 2 swell waves height")
  private float swell2Height;

  @Column(name = "swell2_dir")
  @Schema(description = "Class 2 swell waves direction")
  private float swell2Direction;

  @Column(name = "swell2_period")
  @Schema(
      description = "Class 2 swell waves period represented as time interval between arrival of "
                  + "consecutive class 2 swell waves"
  )
  private float swell2Period;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "marine_weather_id")
  @Schema(description = "Marine weather data containing this specific swell waves data")
  private MarineWeather marineWeather;

  private static final float MAX_SWELL1_HEIGHT = 1;
  private static final float MAX_SWELL1_PERIOD = 14;
  private static final float MAX_SWELL2_HEIGHT = 0.6f;
  private static final float MAX_SWELL2_PERIOD = 15;

  /**
   * Default constructor for the SwellWaves class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public SwellWaves() {
    // Intentionally left blank
  }

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

  /**
   * Checks if the swell waves data is valid.
   *
   * @return True if the swell waves data is valid or false otherwise
   */
  public boolean isValid() {
    return this.swell1Height >= 0 && this.swell1Direction >= 0 && this.swell1Direction <= 360
        && this.swell1Period >= 0 && this.swell2Height >= 0 && this.swell2Direction >= 0
        && this.swell2Direction <= 360 && this.swell2Period >= 0;
  }

  /**
   * Checks if the generated swell waves data is valid.
   *
   * @return True if the generated swell waves data is valid or false otherwise
   */
  public boolean isGeneratedValid() {
    return this.isValid() && this.swell1Height <= MAX_SWELL1_HEIGHT
        && this.swell1Period <= MAX_SWELL1_PERIOD && this.swell2Height <= MAX_SWELL2_HEIGHT
        && this.swell2Period <= MAX_SWELL2_PERIOD;
  }
}
