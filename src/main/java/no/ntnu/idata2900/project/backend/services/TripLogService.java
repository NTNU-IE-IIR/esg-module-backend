package no.ntnu.idata2900.project.backend.services;

import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.backend.dtos.TripLogDto;
import no.ntnu.idata2900.project.backend.models.Trip;
import no.ntnu.idata2900.project.backend.repositories.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling CRUD operations on Trip Logs.
 *
 * @author Group 14
 * @version 0.1.0 (2025.04.29)
 */
@Service
public class TripLogService {
  
  private final TripRepository tripRepository;

  private final Logger logger = LoggerFactory.getLogger(TripLogService.class);

  /**
   * Constructor for the TripLogService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param tripRepository The specified trip log repository
   */
  @Autowired
  public TripLogService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  /**
   * Get all trip logs with the specified registration mark.
   *
   * @param registrationMark The specified registration mark
   * @return Collection containing all trip logs
   * @see TripLogDto
   */
  public List<TripLogDto> getAllTripLogs(String registrationMark) {
    List<Trip> trips = tripRepository.findByRegistrationMarkAndActiveFalse(registrationMark);
    return trips.stream().map(TripLogDto::new).toList();
  }

  /**
   * Edit the comments in the {@link TripLogDto trip log} with specified ID. The comments is edited
   * with the specified comments.
   *
   * @param comments The specified comments
   * @param id The specified ID
   * @return True if the comments are successfully edited or false otherwise
   */
  public boolean editComments(String comments, Long id) {
    boolean success = false;
    Optional<Trip> trip = tripRepository.findById(id);
    if (trip.isPresent()) {
      Trip updatedTrip = trip.get();
      updatedTrip.setComments(comments);
      tripRepository.save(updatedTrip);
      logger.info("Updated comments for trip log with ID: {}", id);
      success = true;
    }
    return success;
  }

  /**
   * Delete the trip log with the specified ID.
   *
   * @param id The specified ID
   * @return True if the trip log is successfully deleted or false otherwise
   * @see TripLogDto
   */
  public boolean deleteTripLog(Long id) {
    boolean success = false;
    Optional<Trip> tripLog = tripRepository.findById(id);
    if (tripLog.isPresent()) {
      tripRepository.deleteById(id);
      logger.info("Deleted trip log with ID: {}", id);
      success = true;
    }
    return success;
  }
}
