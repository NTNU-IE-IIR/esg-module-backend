package no.ntnu.idata2900.project.esg_module_backend.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Set;
import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;

// TODO Refactor class to contain a list of data points instead of ship DTOs

/**
 * The Trip class represents a fishing trip. The class contain a list of
 * {@link ShipDto ship data}.
 *
 * @author Group 14
 * @version v0.1.1 (2025.04.24)
 */
public class Trip {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant startDate;
  private Instant endDate;
  private float tripDistance;
  private float fuelConsumed;
  private float fishCaught;
  private Set<FishingSession> fishingSessions;
  private final List<ShipDto> shipData;

  /**
   * Constructor for the Trip class.
   */
  public Trip() {
    this.shipData = new ArrayList<>();
  }

  /**
   * Starts a fishing trip.
   */
  public void start() {
    this.startDate = Instant.now();
  }

  /**
   * Ends a fishing trip and intitializes a summarization.
   */
  public void end() {
    this.endDate = Instant.now();
    this.summarizeTrip();
  }

  /**
   * Summarizes a trip by calculating various key numbers like <code>tripDistance</code>.
   */
  private void summarizeTrip() {
    this.tripDistance = this.calculateTotalDistance();
    this.fishCaught = 0; //TODO: Temporary assign to 0. Fishing logic will be refactored.
    this.fuelConsumed += shipData.getFirst().getFuelLevel() - shipData.getLast().getFuelLevel();
  }

  /**
   * Returns the calculated total distance traveled during the trip. This is done by calculating
   * the distance between each data point and adding them together.
   *
   * @return Calculated total distance traveled
   */
  private float calculateTotalDistance() {
    float distance = 0;
    for (int i = 0; i < shipData.size() - 1; i++) {
      distance += calculateDistance(shipData.get(i), shipData.get(i + 1));
    }
    return distance;
  }

  /**
   * Returns the calculated geographical distance between two individual data points.
   *
   * @param dp1 The specified first data point
   * @param dp2 The specified second data point
   * @return Calculated geograpical distance between two points
   */
  private float calculateDistance(ShipDto dp1, ShipDto dp2) {
    float lat1 = dp1.getLat();
    float lon1 = dp1.getLng();
    float lat2 = dp2.getLat();
    float lon2 = dp2.getLng();

    float theta = lon1 - lon2;

    double dist =
        Math.sin(Math.toRadians(lat1)) *
            Math.sin(Math.toRadians(lat2)) +
            Math.cos(Math.toRadians(lat1)) *
                Math.cos(Math.toRadians(lat2)) *
                Math.cos(Math.toRadians(theta));
    dist = Math.acos(dist);
    dist = Math.toDegrees(dist);
    dist = dist * 60 * 1.1515;
    dist = dist * 0.8684;

    return (float) dist;
  }

  /**
   * Returns a trip log derived from the fishing trip.
   *
   * @param comments The specified comments
   * @param area     The specified area
   * @return A trip log of the fishing trip
   * @see TripLog
   */
  public TripLog toTripLog(String comments, String area) {
    return new TripLog(fishCaught, fuelConsumed, tripDistance, comments, area, startDate.toString(),
        endDate.toString());
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
   * Getter for list of ship data
   *
   * @return List of ship data
   */
  public List<ShipDto> getShipData() {
    return this.shipData;
  }

  /**
   * Adds the specified ship data to the list of ship data.
   *
   * @param ship The specified ship data
   */
  public void addShipData(ShipDto ship) {
    this.shipData.add(ship);
  }

  public Set<FishingSession> getFishingSessions() {
    return fishingSessions;
  }
}
