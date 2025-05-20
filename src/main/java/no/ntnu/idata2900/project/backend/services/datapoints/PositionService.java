package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Position;
import no.ntnu.idata2900.project.backend.repositories.datapoints.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The PositionService class represents the service for {@link Position position}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class PositionService {

  private final PositionRepository repo;

  /**
   * Constructor for the PositionService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
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
