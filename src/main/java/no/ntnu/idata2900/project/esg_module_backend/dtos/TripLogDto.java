package no.ntnu.idata2900.project.esg_module_backend.dtos;

import java.time.Instant;
import java.util.Set;
import no.ntnu.idata2900.project.esg_module_backend.models.FishingSession;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;

/**
 * The TripLogDto class represents a data transfer object (DTO) for transferring historic trip data.
 * It only contains the necessary information about a trip to display it as a log in the log
 * page in the frontend.
 *
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.06)
 */
public class TripLogDto {
  private final Long id;
  private Set<FishingSession> fishingSessions;
  private Instant startDate;
  private Instant endDate;
  private float fuelConsumed;
  private float totalDistance;
  private String area;
  private String comments;

  public Long getId() {
    return id;
  }

  public Set<FishingSession> getFishingSessions() {
    return fishingSessions;
  }

  public void setFishingSessions(
      Set<FishingSession> fishingSessions) {
    this.fishingSessions = fishingSessions;
  }

  public Instant getStartDate() {
    return startDate;
  }

  public void setStartDate(Instant startDate) {
    this.startDate = startDate;
  }

  public Instant getEndDate() {
    return endDate;
  }

  public void setEndDate(Instant endDate) {
    this.endDate = endDate;
  }

  public float getFuelConsumed() {
    return fuelConsumed;
  }

  public void setFuelConsumed(float fuelConsumed) {
    this.fuelConsumed = fuelConsumed;
  }

  public float getTotalDistance() {
    return totalDistance;
  }

  public void setTotalDistance(float totalDistance) {
    this.totalDistance = totalDistance;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public TripLogDto(Trip trip) {
    this.id = trip.getId();
    this.fishingSessions = trip.getFishingSessions();
    this.startDate = trip.getStartDate();
    this.endDate = trip.getEndDate();
    this.fuelConsumed = trip.getFuelConsumed();
    this.totalDistance = trip.getTripDistance();
    this.area = trip.getArea();
    this.comments = trip.getComments();
  }
}
