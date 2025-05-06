package no.ntnu.idata2900.project.esg_module_backend.services;


import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.dtos.TripLogDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripRepository;
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
  private final Logger logger = LoggerFactory.getLogger(TripLogService.class);
  private final TripRepository tripRepository;

  @Autowired
  public TripLogService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }


  public List<TripLogDto> getAllTripLogs(String registrationMark) {
    List<Trip> trips = tripRepository.findByRegistrationMarkAndActiveFalse(registrationMark);

    return trips.stream().map(TripLogDto::new).toList();
  }


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
