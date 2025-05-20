package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.SwellWaves;
import no.ntnu.idata2900.project.backend.repositories.datapoints.SwellWavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The SwellWavesService class represents the service for {@link SwellWaves swell waves data}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class SwellWavesService {

  private final SwellWavesRepository repo;

  /**
   * Constructor for the SwellWavesService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified swell waves repository
   */
  @Autowired
  public SwellWavesService(SwellWavesRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified swell waves data if it is valid.
   *
   * @param swellWaves The specified swell waves data
   */
  public void add(SwellWaves swellWaves) {
    boolean valid = swellWaves.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Swell waves data is invalid");
    }
    this.repo.save(swellWaves);
  }
}
