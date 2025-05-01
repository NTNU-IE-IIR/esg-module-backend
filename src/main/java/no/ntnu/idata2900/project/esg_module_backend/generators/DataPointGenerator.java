package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.DataPointService;

/**
 * The DataPointGenerator class represents a generator for {@link DataPoint data points}.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.01)
 */
@Component
public class DataPointGenerator {
  private DataPointService dpService;

  private PositionGenerator posGenerator;
  private VesselGenerator vesselGenerator;

  /**
   * Constructor for the DataPointInitializer class.
   * 
   * @param dpService The specified data point service
   */
  @Autowired
  public DataPointGenerator(
    DataPointService dpService,
    PositionGenerator posGenerator,
    VesselGenerator vesselGenerator
  ) {
    this.dpService = dpService;
    this.posGenerator = posGenerator;
    this.vesselGenerator = vesselGenerator;
  }

  public DataPoint generate(DataPoint baseDp) {
    // Increment timestamp by 1 min
    long ts = baseDp.getTs() + 60;

    DataPoint dp = new DataPoint(ts);
    Long id = this.dpService.add(dp);

    this.posGenerator.generate(baseDp, dp);
    this.vesselGenerator.generate(baseDp, dp);

    // Data point fetched from storage
    Optional<DataPoint> fDp = this.dpService.get(id);
    if (fDp.isPresent()) {
      // Update data point with relationships in storage
      dp = fDp.get();
    }

    return dp;
  }
}
