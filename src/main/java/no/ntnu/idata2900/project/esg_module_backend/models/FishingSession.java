package no.ntnu.idata2900.project.esg_module_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;

/**
 * <p>Represents a fishing session in the context of a fishing trip.
 * A fishing session encompasses a time period with a defined start and end date
 * during which specific fishing operations take place.</p>
 *
 * <p>A fishing session can consist of multiple fishing operations, and it is
 * associated with a single trip.</p>
 *
 * <p>The class is used to encapsulate details such as the session's duration,
 * fuel consumption, and the operations performed during the session.</p>
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Entity
public class FishingSession {
  @Id
  @Column(name = "fishing_session_id")
  @Schema(description = "Unique ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "start_date")
  @Schema(description = "Start date of the fishing session")
  private String startDate;
  @Column(name = "end_date")
  @Schema(description = "End date of the fishing session")
  private String endDate;
  @Column(name = "fuel_consumed")
  @Schema(description = "Amount of fuel that was consumed during the fishing session")
  private Long fuelConsumed;
  @OneToMany(mappedBy = "fishingSession")
  @Schema(description = "Set of fishing operations performed during the fishing session")
  private Set<FishingOperation> operations;
  @ManyToOne
  @Schema(description = "Trip this session belongs to")
  @JsonIgnore
  private Trip trip;

  /**
   * Gets the start date of the fishing session.
   *
   * @return The start date as a string
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Gets the end date of the fishing session.
   *
   * @return The end date as a string
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Gets the amount of fuel consumed during the fishing session.
   *
   * @return The fuel consumed in liters
   */
  public Long getFuelConsumed() {
    return fuelConsumed;
  }

  /**
   * Gets the set of fishing operations associated with this session.
   *
   * @return A set of fishing operations
   */
  public Set<FishingOperation> getOperations() {
    return operations;
  }

  /**
   * Gets the unique identifier of the fishing session.
   *
   * @return The ID of the fishing session
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the trip associated with this fishing session.
   *
   * @param trip The trip to associate with this fishing session
   */
  public void setTrip(Trip trip) {
    this.trip = trip;
  }
}
