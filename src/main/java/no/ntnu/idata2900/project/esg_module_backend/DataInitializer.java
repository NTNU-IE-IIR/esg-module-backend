package no.ntnu.idata2900.project.esg_module_backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
import no.ntnu.idata2900.project.esg_module_backend.models.Data;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;

public class DataInitializer {
  private static final Set<Data> DATA = new HashSet<>();
  private static final List<Position> POSITIONS = new ArrayList<>();
  // Number of instances to initialize of each data type
  private static int num = 10000;

  private static Random ran = new Random();

  public static Set<Data> init() {
    initPositions();

    initBoatData();
    initWeatherData();

    return DATA;
  }

  private static void initBoatData() {
    for (int i = 1; i <= num; i++) {
      BoatData boatData = new BoatData(
        i,
        "Boat " + i,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0, 
        0
      );
    }
  }

  private static void initWeatherData() {
  }

  private static void initPositions() {
    float lat = 61.6031484f;
    float lng = 5.0445668f;

    // Initial position
    POSITIONS.add(new Position(lat, lng));

    for (int i = 1; i < num; i++) {
      lat += ran.nextFloat(0.01f) - 0.005f;
      lng += ran.nextFloat(0.01f) - 0.005f;

      Position pos = new Position(lat, lng);

      POSITIONS.add(pos);
    }
  }
}
