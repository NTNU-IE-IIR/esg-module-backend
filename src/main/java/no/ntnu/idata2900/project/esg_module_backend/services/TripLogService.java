package no.ntnu.idata2900.project.esg_module_backend.services;


import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripLogRepository;
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
  private final TripLogRepository tripLogRepository;

  @Autowired
  public TripLogService(TripLogRepository tripLogRepository) {
    this.tripLogRepository = tripLogRepository;
  }


  public List<TripLog> getAllTripLogs() {
    return tripLogRepository.findAll();
  }


  public boolean editComments(String comments, Long id) {
    boolean success = false;
    Optional<TripLog> tripLog = tripLogRepository.findById(id);
    if (tripLog.isPresent()) {
      TripLog log = tripLog.get();
      log.setComments(comments);
      tripLogRepository.save(log);
      logger.info("Updated comments for trip log with ID: {}", id);
      success = true;
    }
    return success;
  }


  public boolean deleteTripLog(Long id) {
    boolean success = false;
    Optional<TripLog> tripLog = tripLogRepository.findById(id);
    if (tripLog.isPresent()) {
      tripLogRepository.deleteById(id);
      logger.info("Deleted trip log with ID: {}", id);
      success = true;
    }
    return success;
  }


  public boolean createTripLog(TripLog tripLog) {
    boolean success = false;
    tripLogRepository.save(tripLog);

    if (tripLogRepository.findById(tripLog.getId()).isPresent()) {
      logger.info("Saved trip log with ID: {}", tripLog.getId());
      success = true;
    }

    return success;
  }
}
