package no.ntnu.idata2900.project.backend.generators;

import java.util.Random;

import no.ntnu.idata2900.project.backend.models.datapoints.Fuel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import no.ntnu.idata2900.project.backend.services.datapoints.VesselService;

/**
 * The VesselGenerator class represents the generator for {@link Vessel vessel data}.
 * 
 * <p><i>Deviation constants:</i></p>
 * 
 * <ul>
 *   <li><code>HEADING</code> (<code>degrees</code>): Maximum heading deivation</li>
 *   <li><code>SPEED</code> (<code>knots</code>): Maximum speed deviation</li>
 * </ul>
 * 
 * <p>The preceding constants define how much each attribute can maximum change at each
 * {@link DataPoint data point}.</p>
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
@Component
public class VesselGenerator {
  private VesselService vesselService;

  private FuelGenerator fuelGenerator;

  private static final float HEADING = 15.0f;
  private static final float SPEED = 0.5f;

  private static final Random RAN = new Random();

  /**
   * Constructor for the VesselGenerator class.
   * 
   * @param vesselService The specified vessel data service
   */
  @Autowired
  public VesselGenerator(VesselService vesselService, FuelGenerator fuelGenerator) {
    this.vesselService = vesselService;
    this.fuelGenerator = fuelGenerator;
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
  public Vessel generate(DataPoint baseDp, DataPoint dp) {
    Vessel vessel;
    if (baseDp == null) {
      vessel = new Vessel(315.0f, 7.0f);
    } else {
      vessel = this.randomVessel(baseDp);
    }
    vessel.setDp(dp);
    // Add vessel data to storage
    this.vesselService.add(vessel);

    Fuel fuel = fuelGenerator.generate(dp.getWeather(), dp.getMarineWeather(), vessel);

    vessel.setFuelConsumption(fuel);

    return vessel;
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

      // Correct `heading`
      if (heading > 360) {
        heading -= 360;
      } else if (heading < 0) {
        heading += 360;
      }

      vessel = new Vessel(heading, speed);

      if (vessel.isGeneratedValid()) {
        valid = true;
      }
    }

    return vessel;
  }
}
