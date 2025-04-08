package no.ntnu.idata2900.project.esg_module_backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.ntnu.idata2900.project.esg_module_backend.models.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.Ship;
import no.ntnu.idata2900.project.esg_module_backend.models.Weather;

public class FakeDataInitializer {
  private static final List<DataPoint> DATA = new ArrayList<>();
  // Number of instances to initialize of each data type
  private static int num = 10000;

  private static Random ran = new Random();

  private static float lat = 61.60314f;
  private static float lng = 5.044566f;

  /**
   * Returns a list of fake data points containing random data.
   * 
   * @return A list of fake data points
   */
  public static List<DataPoint> init() {
    // UNIX timestamp in seconds
    int ts = 1739438130;

    for (int i = 0; i < 10000; i++) {
      DataPoint dp = new DataPoint(initPositionData(), ts);

      dp.setShip(initShipData());
      dp.setWeather(initWeatherData());

      DATA.add(dp);

      // Increment time by 1 minute
      ts += 60;
    }

    return DATA;
  }

  /**
   * Returns a position with a random latitude and longitude. The values are based off of the
   * previously set latitude and longitude. If either of the values exceed their respective
   * boundaries, they are reset and produced over until they become valid.
   * 
   * @return A position with a random latitude and longitude
   */
  private static Position initPositionData() {
    float tempLat = lat;
    float tempLng = lng;

    boolean valid = false;

    while (!valid) {
      lat += ran.nextFloat(0.01f) - 0.005f;
      if (lat < -90 && lat > 90) {
        lat = tempLat;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    while (!valid) {
      lng += ran.nextFloat(0.01f) - 0.005f;
      if (lng < -180 || lng > 180) {
        lng = tempLng;
      } else {
        valid = true;
      }
    }

    return new Position(lat, lng);
  }

  private static Ship initShipData() {
  }

  private static Weather initWeatherData() {
  }
}
