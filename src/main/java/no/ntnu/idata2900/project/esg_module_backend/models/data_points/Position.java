package no.ntnu.idata2900.project.esg_module_backend.models.data_points;

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
 * The Position class represents a position in the form of a coordinate, containing a latitude and
 * longitude.
 *
 * @author Group 14
 * @version v0.1.5 (2025.04.30)
 */
@Entity
@Table(name = "position")
@Schema(
  description = "Position entity representing position as coordinates at a specific data point"
)
public class Position {

  @Id
  @Column(name = "position_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "latitude")
  @Schema(description = "Latitude")
  private float lat;

  @Column(name = "longitude")
  @Schema(description = "Longitude")
  private float lng;

  @JsonIgnore
  @MapsId
  @OneToOne(mappedBy = "pos")
  @JoinColumn(name = "position_id")
  @Schema(description = "Data point containig this specific position data")
  private DataPoint dp;

  /**
   * Default constructor for the Position class.
   * 
   * <p>The default constructor is required by JPA.</p>
   */
  public Position() {
    // Intentionally left blank
  }

  /**
   * Constructor for the Position class.
   *
   * @param lat The specified latitude
   * @param lng The specified longitude
   */
  public Position(float lat, float lng) {
    this.lat = lat;
    this.lng = lng;
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
   * Getter for latitude.
   *
   * @return Latitude
   */
  public float getLat() {
    return this.lat;
  }

  /**
   * Getter for longitude.
   *
   * @return Longitude
   */
  public float getLng() {
    return this.lng;
  }

  /**
   * Getter for data point.
   * 
   * @return Data point
   */
  public DataPoint getDp() {
    return this.dp;
  }

  /**
   * Setter for data point.
   * 
   * @param dp The specified data point
   */
  public void setDp(DataPoint dp) {
    this.dp = dp;
  }
}
