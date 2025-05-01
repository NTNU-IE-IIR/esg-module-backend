package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.DataPointRepository;

/**
 * The DataPointService class represents the service for {@link DataPoint data points}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class DataPointService {

  private DataPointRepository repo;

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
   * Adds the specified data point if it is valid.
   * 
   * @param dp The specified data point
   */
  public void add(DataPoint dp) {
    boolean valid = dp.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Data point is invalid");
    }
    this.repo.save(dp);
  }
}
