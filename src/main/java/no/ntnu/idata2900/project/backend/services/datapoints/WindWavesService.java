package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.WindWaves;
import no.ntnu.idata2900.project.backend.repositories.datapoints.WindWavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The WindWavesService class represents the service for {@link WindWaves wind waves data}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class WindWavesService {

  @Autowired
  private final WindWavesRepository repo;

  /**
   * Constructor for the WindWavesService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified wind waves repository
   */
  @Autowired
  public WindWavesService(WindWavesRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified wind waves data if it is valid.
   *
   * @param windWaves The specified wind waves data
   */
  public void add(WindWaves windWaves) {
    boolean valid = windWaves.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Wind waves data is invalid");
    }
    this.repo.save(windWaves);
  }
}
