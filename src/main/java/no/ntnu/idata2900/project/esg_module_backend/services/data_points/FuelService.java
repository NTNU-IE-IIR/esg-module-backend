package no.ntnu.idata2900.project.esg_module_backend.services.data_points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Fuel;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.FuelRepository;

/**
 * The FuelService class represents the service for {@link Fuel fuel consumption}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class FuelService {

  private FuelRepository repo;

  /**
   * Constructor for the FuelService class.
   * 
   * @param repo The specified fuel consumption repository
   */
  @Autowired
  public FuelService(FuelRepository repo) {
    this.repo = repo;
  }

  /**
   * Adds the specified fuel consumption if it is valid.
   * 
   * @param fuelConsumption The specified fuel consumption
   */
  public void add(Fuel fuelConsumption) {
    boolean valid = fuelConsumption.isValid();
    if (!valid) {
      throw new IllegalArgumentException("Fuel consumption is invalid");
    }
    this.repo.save(fuelConsumption);
  }
}
