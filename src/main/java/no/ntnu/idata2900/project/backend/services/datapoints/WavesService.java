package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Waves;
import no.ntnu.idata2900.project.backend.repositories.datapoints.WavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The WavesService class represents the service for {@link Waves waves data}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class WavesService {

  private final WavesRepository repo;

  /**
   * Constructor for the WavesService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified waves repository
   */
  @Autowired
  public WavesService(WavesRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified waves data if it is valid.
   *
   * @param waves The specified waves data
   */
  public void add(Waves waves) {
    boolean valid = waves.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Waves data is invalid");
    }
    this.repo.save(waves);
  }
}
