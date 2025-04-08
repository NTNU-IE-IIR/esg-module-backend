package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The Position class represents a position in the form of a coordinate, containing a latitude and
 * longitude.
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.08)
 */
public class Position {
  private float lat;
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
