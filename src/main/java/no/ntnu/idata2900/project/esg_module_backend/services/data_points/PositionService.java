package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.PositionRepository;

/**
 * The PositionService class represents the service for {@link Position position}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class PositionService {

  private PositionRepository repo;

  /**
   * Constructor for the PositionService class.
   * 
   * @param repo The specified position repository
   */
  @Autowired
  public PositionService(PositionRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified position if it is valid.
   * 
   * @param pos The specified position
   */
  public void add(Position pos) {
    boolean valid = pos.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Position is invalid");
    }
    this.repo.save(pos);
  }
}
