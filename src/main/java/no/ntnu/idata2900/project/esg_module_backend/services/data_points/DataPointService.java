package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.DataPointRepository;

/**
 * The DataPointService class represents the service for {@link DataPoint data points}.
 * 
 * @author Group 14
 * @version v0.2.1 (2025.05.01)
 */
@Service
public class DataPointService {

  private final DataPointRepository repo;

  /**
   * Constructor for the DataPointService class.
   * 
   * @param repo The specified data point repository
   */
  @Autowired
  public DataPointService(DataPointRepository repo) {
    this.repo = repo;
  }

  /**
   * Gets all data points.
   * 
   * @return All data points
   */
  public Iterable<DataPoint> getAll() {
    return this.repo.findAll();
  }

  /**
   * Gets the data point with the specified ID.
   * 
   * @param id The specified ID
   * @return The data point
   */
  public Optional<DataPoint> get(Long id) {
    return this.repo.findById(id);
  }

  /**
   * Adds the specified data point if it is valid.
   * 
   * @param dp The specified data point
   * @return The generated ID if the specified data point is valid
   */
  public Long add(DataPoint dp) {
    if (!dp.isValid()) {
      throw new IllegalArgumentException("The specified data point is invalid");
    }
    this.repo.save(dp);
    return dp.getId();
  }
}
