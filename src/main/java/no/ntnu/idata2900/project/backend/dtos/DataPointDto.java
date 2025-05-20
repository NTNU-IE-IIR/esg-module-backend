package no.ntnu.idata2900.project.backend.dtos;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.models.datapoints.Position;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;

/**
 * The DataPointDto class represents only the data from a DataPoint that is relevant to send to
 * the frontend. The class is part of the data packaged into a TripDto.
 *
 * @author Group 14
 * @version v0.2.8 (2025.05.01)
 */
public class DataPointDto {
  private Long id;
  private long ts;
  private Position pos;
  private Vessel vessel;

  /**
   * Constructor for the DataPoint class, from an existing DataPoint.
   *
   * @param dataPoint the specified DataPoint to copy from.
   */
  public DataPointDto(DataPoint dataPoint) {
    this.id = dataPoint.getId();
    this.ts = dataPoint.getTs();
    this.pos = dataPoint.getPos();
    this.vessel = dataPoint.getVessel();
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
   * Getter for vessel data.
   *
   * @return Vessel data
   */
  public Vessel getVessel() {
    return this.vessel;
  }
}
