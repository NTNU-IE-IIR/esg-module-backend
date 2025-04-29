package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Position class represents a position in the form of a coordinate, containing a latitude and
 * longitude.
 *
 * @author Group 14
 * @version v0.1.4 (2025.04.29)
 */
@Entity(name = "position")
@Schema(
  description = "Position entity representing position as coordinates at a specific data point"
)
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique ID")
  private Long id;

  @Schema(description = "Latitude")
  private float lat;

  @Schema(description = "Longitude")
  private float lng;

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
}
