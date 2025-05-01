package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.WindWaves;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.WindWavesRepository;

/**
 * The WindWavesService class represents the service for {@link WindWaves wind waves data}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class WindWavesService {

  private WindWavesRepository repo;

  /**
   * Constructor for the WindWavesService class.
   * 
   * @param repo The specified wind waves data repository
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
