package no.ntnu.idata2900.project.esg_module_backend.dtos;

import java.util.Set;
import no.ntnu.idata2900.project.esg_module_backend.models.FishingSession;

/**
 * The TripDto class represents a data transfer object (DTO) for transferring vessel data as
 * well as other information like fishes caught and distance traveled. The reason for using a DTO
 * is that we don't want to expose the entire vessel model to the frontend. The DTO is used for
 * sending only relevant data point data as well as some fishing data that are not connected to
 * data points.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.08)
 */
public class TripDto {
  private final Long id;
  private DataPointDto dataPointDto;
  private Set<FishingSession> fishingSessions;
  private float fuelConsumed;
  private float totalDistance;

  public TripDto(DataPointDto dataPointDto, Set<FishingSession> fishingSessions,
                 float fuelConsumed, float totalDistance) {
    this.id = dataPointDto.getId();
    this.dataPointDto = dataPointDto;
    this.fishingSessions = fishingSessions;
    this.fuelConsumed = fuelConsumed;
    this.totalDistance = totalDistance;
  }

  /**
   * Getter for ID.
   *
   * @return ID
   */
  public Long getId() {
    return id;
  }

  /**
   * Getter for total distance.
   *
   * @return Total distance
   */
  public float getTotalDistance() {
    return totalDistance;
  }

  /**
   * Sets the total distance traveled during the trip.
   *
   * @param totalDistance The total distance to be set, measured in kilometers.
   */
  public void setTotalDistance(float totalDistance) {
    this.totalDistance = totalDistance;
  }

  /**
   * Retrieves the data point associated with this trip.
   *
   * @return The associated {@link DataPointDto} containing relevant data for the trip
   */
  public DataPointDto getDataPointDto() {
    return dataPointDto;
  }

  /**
   * Sets the data point associated with a trip.
   *
   * @param dataPointDto The {@link DataPointDto} object containing relevant data to be associated with the trip.
   */
  public void setDataPointDto(
      DataPointDto dataPointDto) {
    this.dataPointDto = dataPointDto;
  }

  /**
   * Retrieves the set of fishing sessions associated with this trip.
   *
   * @return A set of {@link FishingSession} objects representing the fishing sessions in the trip
   */
  public Set<FishingSession> getFishingSessions() {
    return fishingSessions;
  }

  /**
   * Updates the set of fishing sessions associated with the trip.
   *
   * @param fishingSessions The set of {@link FishingSession} objects to associate with the trip
   */
  public void setFishingSessions(
      Set<FishingSession> fishingSessions) {
    this.fishingSessions = fishingSessions;
  }

  /**
   * Retrieves the total fuel consumed during the trip.
   *
   * @return The total amount of fuel consumed, measured in liters
   */
  public float getFuelConsumed() {
    return fuelConsumed;
  }

  /**
   * Sets the total fuel consumed during the trip.
   *
   * @param fuelConsumed The amount of fuel to be set, measured in liters.
   */
  public void setFuelConsumed(float fuelConsumed) {
    this.fuelConsumed = fuelConsumed;
  }

  @Override
  public String toString() {
    return "TripDto{" +
        "id=" + id +
        ", dataPoint=" + dataPointDto +
        ", fishingSessions=" + fishingSessions +
        ", fuelConsumed=" + fuelConsumed +
        ", totalDistance=" + totalDistance +
        '}';
  }
}
