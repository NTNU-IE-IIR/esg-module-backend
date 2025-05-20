package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import no.ntnu.idata2900.project.backend.repositories.datapoints.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The VesselService class represents the service for {@link Vessel vessel data}.
 *
 * @author Group 14
 * @version v0.1.1 (2025.05.02)
 */
@Service
public class VesselService {

  private final VesselRepository repo;

  /**
   * Constructor for the VesselService class.
   *
   * @param repo The specified vessel repository
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
    if (!vessel.isValid()) {
      throw new IllegalArgumentException("The specified vessel data is invalid");
    }
    this.repo.save(vessel);
  }
}
