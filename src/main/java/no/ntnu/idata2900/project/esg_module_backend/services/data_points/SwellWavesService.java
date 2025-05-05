package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.SwellWaves;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.SwellWavesRepository;

/**
 * The SwellWavesService class represents the service for {@link SwellWaves swell waves data}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class SwellWavesService {

  private SwellWavesRepository repo;

  /**
   * Constructor for the SwellWavesService class.
   * 
   * @param repo The specified swell waves data repository
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
