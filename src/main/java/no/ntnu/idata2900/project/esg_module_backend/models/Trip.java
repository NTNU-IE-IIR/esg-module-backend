package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * The Trip class represents a fishing trip. The class contain a list of
 * {@link DataPoint data points} and a list of {@link FishingSession fishing sessions}.
 *
 * @author Group 14
 * @version v0.2.0 (2025.05.01)
 */
@Entity
public class Trip {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String registrationMark;
  private Instant startDate;
  private Instant endDate;
  private float tripDistance;
  private float fuelConsumed;
  private float fishCaught;
  private boolean active;
  private String area;
  private String comments;
  @OneToMany(mappedBy = "trip")
  private Set<FishingSession> fishingSessions;
  @OneToMany(mappedBy = "trip")
  private List<DataPoint> shipData;

  /**
   * Constructor for the Trip class.
   */
  public Trip() {
  }

  /**
   * Constructor for creating a new Trip with a name and registration mark.
   *
   * @param name The name of the trip
   * @param registrationMark The registration mark of the vessel
   */
  public Trip(String name, String registrationMark) {
    this.name = name;
    this.registrationMark = registrationMark;
    this.startDate = Instant.now();
    this.endDate = null;
    this.tripDistance = 0;
    this.fuelConsumed = 0;
    this.fishCaught = 0;
    this.active = true;
    this.area = null;
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
   * Getter for start date.
   *
   * @return Start date
   */
  public Instant getStartDate() {
    return this.startDate;
  }

  /**
   * Getter for end date.
   *
   * @return End date
   */
  public Instant getEndDate() {
    return this.endDate;
  }

  /**
   * Getter for total trip distance.
   *
   * @return Total trip distance
   */
  public float getTripDistance() {
    return this.tripDistance;
  }

  /**
   * Getter for total fuel consumed.
   *
   * @return Total fuel consumed
   */
  public float getFuelConsumed() {
    return this.fuelConsumed;
  }

  /**
   * Getter for total fish caught.
   *
   * @return Total fish caught
   */
  public float getFishCaught() {
    return this.fishCaught;
  }

  /**
   * Getter for the fishing area.
   *
   * @return The fishing area
   */
  public String getArea() {
    return area;
  }

  /**
   * Setter for the fishing area.
   *
   * @param area The fishing area to set
   */
  public void setArea(String area) {
    this.area = area;
  }

  /**
   * Getter for trip comments.
   *
   * @return The comments for this trip
   */
  public String getComments() {
    return comments;
  }

  /**
   * Setter for trip comments.
   *
   * @param comments The comments to set for this trip
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * Checks if the trip is currently active.
   *
   * @return true if the trip is active, false otherwise
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Getter for trip name.
   *
   * @return The name of the trip
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for vessel registration mark.
   *
   * @return The registration mark of the vessel
   */
  public String getRegistrationMark() {
    return registrationMark;
  }

  /**
   * Ends the current trip by setting the end date to the current time
   * and marking the trip as inactive.
   */
  public void end() {
    this.endDate = Instant.now();
    this.active = false;
    //maybe calculate total fish and fuel here?
  }
}
