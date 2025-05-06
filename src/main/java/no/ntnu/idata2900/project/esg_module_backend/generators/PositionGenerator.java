package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.PositionService;

/**
 * The PositionGenerator class represents the generator for {@link Position positions}.
 * 
 * <p><i>Deviation constants:</i></p>
 * 
 * <ul>
 *   <li><code>COR</code> (<code>degrees</code>): Maximum coordinate deivation</li>
 * </ul>
 * 
 * <p>The preceding constants define how much each attribute can maximum change at each
 * {@link DataPoint data point}.</p>
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Component
public class PositionGenerator {
  private PositionService posService;
  //private Logger logger = LoggerFactory.getLogger(PositionGenerator.class);

  private static final float COR = 0.005f;

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
  public Position generate(DataPoint baseDp, DataPoint dp) {
    Position pos;
    if (baseDp == null) {
      pos = new Position(62.27082f, 5.52099f);
    } else {
      pos = this.randomPos(baseDp);
    }
    pos.setDp(dp);
    // Add position to storage
    this.posService.add(pos);
    //logger.info("Position after saved {}", pos);
    return pos;
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
