package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The Data class represents general data applicable for all data classes.
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.08)
 */
public class Data {
  private float lat;
  private float lng;
  private long ts;

  /**
   * Constructor for Data class.
   * 
   * @param lat The specified latitude
   * @param lng The specified longitude
   * @param ts The specified timestamp
   */
  public Data(float lat, float lng, long ts) {
    this.lat = lat;
    this.lng = lng;
    this.ts = ts;
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
   * Getter for timestamp.
   * 
   * @return Timestamp
   */
  public long getTs() {
    return this.ts;
  }
}
