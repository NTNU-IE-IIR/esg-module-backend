package no.ntnu.idata2900.project.esg_module_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * <p>Represents a specific fishing operation within a fishing session.
 * A fishing operation is a discrete activity that occurs during a fishing session,
 * such as casting nets, trawling, or other fishing methods.</p>
 *
 * <p>Each fishing operation tracks details such as start and end times, fuel consumption,
 * the amount of fish caught, and the specific fishing method used.</p>
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Entity
@Table(name = "fishing_operation")
@Schema(description = "Fishing operation entity representing a specific fishing operation")
public class FishingOperation {
  @Id
  @Column(name = "fishing_operation_id")
  @Schema(description = "Unique ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "start_date")
  @Schema(description = "Start date of the fishing operation")
  private String startDate;
  @Column(name = "end_date")
  @Schema(description = "End date of the fishing operation")
  private String endDate;
  @Column(name = "fuel_consumed")
  @Schema(description = "Amount of fuel consumed during the fishing operation in liters")
  private Long fuelConsumed;
  @Column(name = "fish_amount")
  @Schema(description = "Amount of fish caught during the fishing operations in kilograms")
  private Long fishAmount;
  @Column(name = "fishing_method")
  @Schema(description = "The fishing method that was used to catch fish")
  private String fishingMethod;
  @JsonIgnore
  @ManyToOne
  @Schema(description = "Fishing session this operation belongs to")
  private FishingSession fishingSession;

  /**
   * Gets the fishing session associated with this operation.
   *
   * @return The fishing session this operation belongs to
   */
  public FishingSession getFishingSession() {
    return fishingSession;
  }

  /**
   * Sets the fishing session for this operation.
   *
   * @param fishingSession The fishing session to associate with this operation
   */
  public void setFishingSession(FishingSession fishingSession) {
    this.fishingSession = fishingSession;
  }

  /**
   * Gets the start date of the fishing operation.
   *
   * @return The start date as a string
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Gets the end date of the fishing operation.
   *
   * @return The end date as a string
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Gets the amount of fuel consumed during the fishing operation.
   *
   * @return The fuel consumed in liters
   */
  public Long getFuelConsumed() {
    return fuelConsumed;
  }

  /**
   * Gets the amount of fish caught during this operation.
   *
   * @return The amount of fish in kilograms
   */
  public Long getFishAmount() {
    return fishAmount;
  }

  /**
   * Gets the fishing method used during this operation.
   *
   * @return The fishing method as a string
   */
  public String getFishingMethod() {
    return fishingMethod;
  }

  /**
   * Gets the unique identifier of the fishing operation.
   *
   * @return The ID of the fishing operation
   */
  public Long getId() {
    return id;
  }
}
