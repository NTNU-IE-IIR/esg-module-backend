package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.PositionService;

/**
 * The PositionGenerator class represents the generator for {@link Position positions}. The class
 * contains a set of constants called max boundaries that defines how much each attribute can
 * maximum change at each {@link DataPoint data point}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Component
public class PositionGenerator {
  private PositionService posService;

  // MAX BONDARIES
  private static final float COR = 0.005f; // Degrees

  private static final Random RAN = new Random();

  /**
   * Constructor for the PositionGenerator class.
   * 
   * @param posService The specified position service
   */
  @Autowired
  public PositionGenerator(PositionService posService) {
    this.posService = posService;
  }

  /**
   * Generates a position and adds it to storage. The generated position is based on the data
   * provided in the specified base data point.
   * 
   * <p>When the position is added to storage, it contains a reference to the specified data point.
   * This enables the position to be accessed from the data point itself when fetched from
   * storage.</p>
   * 
   * @param baseDp The specified base data point
   * @param dp The specified data point
   * @see Position
   * @see DataPoint
   */
  public void generate(DataPoint baseDp, DataPoint dp) {
    Position pos = this.randomPos(baseDp);
    pos.setDp(dp);
    // Add position to storage
    this.posService.add(pos);
  }

  /**
   * Generates a random position. The generated position is based on the data provided in the
   * specified base data point.
   * 
   * @param baseDp The specified base data point
   * @return A random position
   */
  private Position randomPos(DataPoint baseDp) {
    Position pos = null;
    boolean valid = false;
    
    while (!valid) {
      float lat = baseDp.getPos().getLat() + RAN.nextFloat(COR * 2) - COR;
      float lng = baseDp.getPos().getLng() + RAN.nextFloat(COR * 2) - COR;

      pos = new Position(lat, lng);
  
      if (pos.isValid()) {
        valid = true;
      }
    }

    return pos;
  }
}
