package no.ntnu.idata2900.project.backend.utils;

/**
 * The Fuel class represents utilities for fuel calculations.
 */
public class Fuel {
  /**
   * Constructor for the Fuel class. This constructor should never be called and is thus marked
   * with the <code>private</code> keyword.
   */
  private Fuel() {
    // Intentionally left blank
  }

  /**
   * Calculates the CO2 emissions based on the specified fuel consumption and the specified fuel
   * type.
   *
   * @param fuel     The specified fuel consumption
   * @param fuelType The specified fuel type
   * @return CO2 emissions based on fuel metrics
   */
  public static float co2FromFuel(float fuel, String fuelType) {
    return switch (fuelType) {
      case "hfo" -> 3.114f * fuel;
      case "mdo" -> 3.206f * fuel;
      case "mgo" -> 3.206f * fuel;
      case "vlsfo" -> 3.150f * fuel;
      case "lns" -> 1.350f * fuel;
      default -> 0f;
    };
  }
}
