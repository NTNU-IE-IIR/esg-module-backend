package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.WavesRepository;

/**
 * The WavesService class represents the service for {@link Waves waves data}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class WavesService {

  private WavesRepository repo;

  /**
   * Constructor for the WavesService class.
   * 
   * @param repo The specified waves data repository
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
