package no.ntnu.idata2900.project.esg_module_backend.utils;

public class Fuel {

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
