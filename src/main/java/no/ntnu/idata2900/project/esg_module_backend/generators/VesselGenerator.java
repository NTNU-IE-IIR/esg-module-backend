package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.VesselService;

/**
 * The VesselGenerator class represents the generator for {@link Vessel vessel data}. The class
 * contains a set of constants called max boundaries that defines how much each attribute can
 * maximum change at each {@link DataPoint data point}.
 */
public class VesselGenerator {
  private VesselService vesselService;

  // MAX BOUNDARIES
  private static final float HEADING = 15.0f; // Degreees
  private static final float SPEED = 0.5f; // Knots

  private static final Random RAN = new Random();

  /**
   * Constructor for the VesselGenerator class.
   * 
   * @param vesselService The specified vessel data service
   */
  @Autowired
  public VesselGenerator(VesselService vesselService) {
    this.vesselService = vesselService;
  }


  /**
   * Generates vessel data and adds it to storage. The generated vessel data is based on the data
   * provided in the specified base data point.
   * 
   * <p>When the vessel data is added to storage, it contains a reference to the specified data
   * point. This enables the vessel data to be accessed from the data point itself when fetched
   * from storage.</p>
   * 
   * @param baseDp The specified base data point
   * @param dp The specified data point
   * @see Vessel
   * @see DataPoint
   */
  public void generate(DataPoint baseDp, DataPoint dp) {
    Vessel vessel = this.randomVessel(baseDp);
    vessel.setDp(dp);
    // Add vessel to storage
    this.vesselService.add(vessel);
  }

  /**
   * Generates random vessel data. The generated vessel data is based on the data provided in the
   * specified base data point.
   * 
   * @param baseDp The specified base data point
   * @return Random vessel data
   */
  private Vessel randomVessel(DataPoint baseDp) {
    Vessel vessel = null;
    boolean valid = false;

    while (!valid) {
      float heading = baseDp.getVessel().getHeading() + RAN.nextFloat(HEADING * 2) - HEADING;
      float speed = baseDp.getVessel().getSpeed() + RAN.nextFloat(SPEED * 2) - SPEED;

      vessel = new Vessel(heading, speed);

      if (vessel.isValid()) {
        valid = true;
      }
    }

    return vessel;
  }
}
