package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.VesselRepository;

/**
 * The VesselService class represents the service for {@link Vessel vessel data}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class VesselService {

  private VesselRepository repo;

  /**
   * Constructor for the VesselService class.
   * 
   * @param repo The specified vessel data repository
   */
  @Autowired
  public VesselService(VesselRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified vessel data if it is valid.
   * 
   * @param vessel The specified vessel data
   */
  public void add(Vessel vessel) {
    boolean valid = vessel.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Vessel data is invalid");
    }
    this.repo.save(vessel);
  }
}
