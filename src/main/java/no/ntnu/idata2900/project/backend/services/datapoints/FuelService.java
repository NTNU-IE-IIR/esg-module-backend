package no.ntnu.idata2900.project.backend.services.datapoints;

import no.ntnu.idata2900.project.backend.models.datapoints.Fuel;
import no.ntnu.idata2900.project.backend.repositories.datapoints.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The FuelService class represents the service for {@link Fuel fuel consumption}.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Service
public class FuelService {

  private final FuelRepository repo;

  /**
   * Constructor for the FuelService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   *
   * @param repo The specified fuel repository
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
