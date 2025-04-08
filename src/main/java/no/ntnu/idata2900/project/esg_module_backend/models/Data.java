package no.ntnu.idata2900.project.esg_module_backend.models;

/**
 * The Data class represents general data applicable for all data classes.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.04.08)
 */
public class Data {
  private Position pos;
  private long ts;

  /**
   * Constructor for the Data class.
   * 
   * @param pos The specified position
   * @param ts The specified timestamp
   */
  public Data(Position pos, long ts) {
    this.pos = pos;
    this.ts = ts;
  }

  /**
   * Getter for position.
   * 
   * @return Position
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
}
