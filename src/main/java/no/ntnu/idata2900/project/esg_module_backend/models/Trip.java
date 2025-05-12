package no.ntnu.idata2900.project.esg_module_backend.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;

/**
 * The Trip class represents a fishing trip. The class contain a list of
 * {@link DataPoint data points} and a list of {@link FishingSession fishing sessions}.
 *
 * @author Group 14
 * @version v0.2.0 (2025.05.01)
 */
@Entity
@Table(name = "trip")
@Schema(description = "Trip entity representing a fishing trip")
public class Trip {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "trip_id")
  @Schema(description = "Unique ID")
  private Long id;

  @Column(name = "name")
  @Schema(description = "Name of the ship")
  private String name;

  @Column(name = "registration_mark")
  @Schema(description = "Registration mark of the vessel")
  private String registrationMark;

  @Column(name = "start_date")
  @Schema(description = "Start date of the trip")
  private Instant startDate;

  @Column(name = "end_date")
  @Schema(description = "End date of the trip")
  private Instant endDate;

  @Column(name = "trip_distance")
  @Schema(description = "Total distance traveled during the trip")
  private float tripDistance;

  @Column(name = "fuel_consumed")
  @Schema(description = "Total fuel consumed during the trip")
  private float fuelConsumed;

  @Column(name = "fish_caught")
  @Schema(description = "Total amount of fish caught during the trip")
  private float fishCaught;

  @Column(name = "active")
  @Schema(description = "Whether the trip is currently active")
  private boolean active;

  @Column(name = "area")
  @Schema(description = "Fishing area of the trip")
  private String area;

  @Column(name = "comments")
  @Schema(description = "Comments about the trip")
  private String comments;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip")
  @Schema(description = "Set of fishing sessions during the trip")
  private Set<FishingSession> fishingSessions;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip")
  @Schema(description = "List of data points collected during the trip")
  private List<DataPoint> dataPoints;

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

  public void setFuelConsumed(float fuelConsumed) {
    this.fuelConsumed = fuelConsumed;
  }

  public void setTripDistance(float tripDistance) {
    this.tripDistance = tripDistance;
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

  public List<DataPoint> getDataPoints() {
    return dataPoints;
  }

  public Set<FishingSession> getFishingSessions() {
    return fishingSessions;
  }
}
