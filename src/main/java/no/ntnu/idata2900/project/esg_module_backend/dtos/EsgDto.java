package no.ntnu.idata2900.project.esg_module_backend.dtos;

/**
 * Data Transfer Object for Environmental, Social, and Governance (ESG) data.
 * This class encapsulates information about fish weight, CO2 emissions, and the ratio of CO2 per kg of fish.
 * It is used to transfer ESG-related data between different layers of the application.
 */
public class EsgDto {
  /**
   * The weight of fish in kilograms.
   */
  private float kgFishWeigth;

  /**
   * The amount of CO2 emissions in tons.
   */
  private float tonsCo2;

  /**
   * The ratio of CO2 emissions (in tons) per kilogram of fish weight.
   * This is a calculated field based on tonsCo2 and kgFishWeigth.
   */
  private float co2PerKgFishWeight;

  /**
   * Constructs a new EsgDto with the specified fish weight and CO2 emissions.
   * The CO2 per kg fish weight ratio is automatically calculated.
   *
   * @param kgFishWeigth The weight of fish in kilograms
   * @param tonsCo2 The amount of CO2 emissions in tons
   */
  public EsgDto(float kgFishWeigth, float tonsCo2) {
    this.kgFishWeigth = kgFishWeigth;
    this.tonsCo2 = tonsCo2;
    this.co2PerKgFishWeight = tonsCo2 / kgFishWeigth;
  }

  /**
   * Gets the weight of fish in kilograms.
   *
   * @return The weight of fish in kilograms
   */
  public float getKgFishWeigth() {
    return kgFishWeigth;
  }

  /**
   * Sets the weight of fish in kilograms.
   *
   * @param kgFishWeigth The weight of fish in kilograms
   */
  public void setKgFishWeigth(float kgFishWeigth) {
    this.kgFishWeigth = kgFishWeigth;
  }

  /**
   * Gets the amount of CO2 emissions in tons.
   *
   * @return The amount of CO2 emissions in tons
   */
  public float getTonsCo2() {
    return tonsCo2;
  }

  /**
   * Sets the amount of CO2 emissions in tons.
   *
   * @param tonsCo2 The amount of CO2 emissions in tons
   */
  public void setTonsCo2(float tonsCo2) {
    this.tonsCo2 = tonsCo2;
  }

  /**
   * Gets the ratio of CO2 emissions (in tons) per kilogram of fish weight.
   *
   * @return The ratio of CO2 emissions per kilogram of fish weight
   */
  public float getCo2PerKgFishWeight() {
    return co2PerKgFishWeight;
  }

  /**
   * Sets the ratio of CO2 emissions (in tons) per kilogram of fish weight.
   *
   * @param co2PerKgFishWeight The ratio of CO2 emissions per kilogram of fish weight
   */
  public void setCo2PerKgFishWeight(float co2PerKgFishWeight) {
    this.co2PerKgFishWeight = co2PerKgFishWeight;
  }
}
