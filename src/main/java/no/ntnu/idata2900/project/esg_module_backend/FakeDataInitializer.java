package no.ntnu.idata2900.project.esg_module_backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.ntnu.idata2900.project.esg_module_backend.models.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.Ship;
import no.ntnu.idata2900.project.esg_module_backend.models.Weather;

/**
 * The FakeDataInitializer class represents a data initializer for simulated data. The simulated
 * data is randomly generated, but in an attempt to ensure that the relationships within the data
 * is somewhat realistic, the randomness may be slightly manipulated. See method documentation for
 * further information.
 * 
 * @author Group 14
 * @version v0.0.1 (2025.04.09)
 */
public class FakeDataInitializer {
  private static final List<DataPoint> DATA = new ArrayList<>();
  // Number of instances to initialize of each data type
  private static int num = 10000;

  private static Random ran = new Random();

  // Initial values
  private static float lat = 61.60314f;
  private static float lng = 5.044566f;

  private static float windU = 5.0f;
  private static float windV = 5.0f;
  private static float gust = 10.0f;
  private static float wavesHeight = 0.5f;
  private static float wavesDirection = 30.0f;
  private static float wavesPeriod = 8.0f;
  private static float wwavesHeight = 0.3f;
  private static float wwavesDirection = 15.0f;
  private static float wwavesPeriod = 4.0f;
  private static float swell1Height = 0.5f;
  private static float swell1Direction = 60.0f;
  private static float swell1Period = 7.0f;
  private static float swell2Height = 0.3f;
  private static float swell2Direction = 55.0f;
  private static float swell2Period = 9.0f;
  private static float oceanCurrentVelocity = 0.2f;
  private static float oceanCurrentDirection = 230.0f;

  private static float heading = 315.0f;
  private static float course = 310.0f;
  private static float speed = 12.5f;

  /**
   * Returns a list of fake data points containing random data.
   * 
   * @return A list of fake data points
   */
  public static List<DataPoint> init() {
    // UNIX timestamp in seconds
    int ts = 1739438130;

    for (int i = 1; i <= 10000; i++) {
      DataPoint dp = new DataPoint(initPositionData(), ts);

      dp.setWeather(initWeatherData(i));
      dp.setShip(initShipData(i));

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
    float tmpLat = lat;
    float tmpLng = lng;

    boolean valid = false;

    while (!valid) {
      lat += ran.nextFloat(0.01f) - 0.005f;
      if (lat < -90 && lat > 90) {
        lat = tmpLat;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    while (!valid) {
      lng += ran.nextFloat(0.01f) - 0.005f;
      if (lng < -180 || lng > 180) {
        lng = tmpLng;
      } else {
        valid = true;
      }
    }

    return new Position(lat, lng);
  }

  /**
   * Returns weather data containing randomly generated parameters. The values are based off of the
   * previously set parameters. If any of the values exceed their respective boundaries, they are
   * either corrected or reset until become valid.
   * 
   * @param id The specified ID
   * @return Randomly generated weather data
   */
  private static Weather initWeatherData(int id) {
    boolean valid = false;

    // Random wind U vector
    float tmpWindU = windU;
    while (!valid) {
      windU += ran.nextFloat() - 0.5f;
      if (windU > 15.0f || windU < 0.0f) {
        windU = tmpWindU;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random wind V vector
    float tmpWindV = windV;
    while (!valid) {
      windV += ran.nextFloat() - 0.5f;
      if (windV > 15.0f || windV < 0.0f) {
        windV = tmpWindV;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random wind gust
    float tmpGust = gust;
    while (!valid) {
      gust += ran.nextFloat(2) - 1.0f;
      if (gust > 20.0f || gust < 0.0f) {
        gust = tmpGust;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random waves height
    float tmpWavesHeight = wavesHeight;
    while (!valid) {
      wavesHeight += ran.nextFloat(6) - 3.0f;
      if (wavesHeight > 6.0f || wavesHeight < 0.0f) {
        wavesHeight = tmpWavesHeight;
      } else {
        valid = true;
      }
    }

    // Random waves direction
    wavesDirection += ran.nextFloat(2) - 1.0f;
    if (wavesDirection > 360.0f) {
      wavesDirection -= 360.0f;
    } else if (wavesDirection < 0.0f) {
      wavesDirection += 360.0f;
    }

    // Reset guard condition
    valid = false;

    // Random waves period
    float tmpWavesPeriod = wavesPeriod;
    while (!valid) {
      wavesPeriod += ran.nextFloat(2) - 1.0f;
      if (wavesPeriod > 15.0f || wavesPeriod < 0.0f) {
        wavesPeriod = tmpWavesPeriod;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random wind waves height
    float tmpWwavesHeight = wwavesHeight;
    while (!valid) {
      wwavesHeight += ran.nextFloat(0.5f) - 0.25f;
      if (wwavesHeight > 2.0f || wwavesHeight < 0.0f) {
        wwavesHeight = tmpWwavesHeight;
      } else {
        valid = true;
      }
    }

    // Random wind waves direction
    wwavesDirection += ran.nextFloat(2) - 1.0f;
    if (wwavesDirection > 360.0f) {
      wwavesDirection -= 360.0f;
    } else if (wavesDirection < 0.0f) {
      wwavesDirection += 360.0f;
    }

    // Reset guard condition
    valid = false;

    // Random wind waves period
    float tmpWwavesPeriod = wwavesPeriod;
    while (!valid) {
      wwavesPeriod += ran.nextFloat() - 0.5f;
      if (wwavesPeriod > 10.0f || wwavesPeriod < 0.0f) {
        wwavesPeriod = tmpWwavesPeriod;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random class 1 swell waves height
    float tmpSwell1Height = swell1Height;
    while (!valid) {
      swell1Height += ran.nextFloat(0.5f) - 0.25f;
      if (swell1Height > 1.0f || swell1Height < 0.0f) {
        swell1Height = tmpSwell1Height;
      } else {
        valid = true;
      }
    }

    // Random class 1 swell waves direction
    swell1Direction += ran.nextFloat(2) - 1.0f;
    if (swell1Direction > 360.0f) {
      swell1Direction -= 360.0f;
    } else if (swell1Direction < 0.0f) {
      swell1Direction += 360.0f;
    }

    // Reset guard condition
    valid = false;

    // Random class 1 swell waves period
    float tmpSwell1Period = swell1Period;
    while (!valid) {
      swell1Period += ran.nextFloat(4) - 2.0f;
      if (swell1Period > 14.0f || swell1Period < 0.0f) {
        swell1Period = tmpSwell1Period;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random class 2 swell waves height
    float tmpSwell2Height = swell2Height;
    while (!valid) {
      swell2Height += ran.nextFloat(0.2f) - 0.1f;
      if (swell2Height > 0.6f || swell2Height < 0.0f) {
        swell2Height = tmpSwell2Height;
      } else {
        valid = true;
      }
    }

    // Random class 2 swell waves direction
    swell2Direction += ran.nextFloat(2) - 1.0f;
    if (swell2Direction > 360.0f) {
      swell2Direction -= 360.0f;
    } else if (swell2Direction < 0.0f) {
      swell2Direction += 360.0f;
    }

    // Reset guard condition
    valid = false;

    // Random class 2 swell waves period
    float tmpSwell2Period = swell2Period;
    while (!valid) {
      swell2Period += ran.nextFloat(2) - 1.0f;
      if (swell2Period > 15.0f || swell2Period < 0.0f) {
        swell2Period = tmpSwell2Period;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random ocean current velocity
    float tmpOceanCurrentVelocity = oceanCurrentVelocity;
    while (!valid) {
      oceanCurrentVelocity += ran.nextFloat(0.002f) - 0.001f;
      if (oceanCurrentVelocity > 0.25f || oceanCurrentVelocity < 0.0f) {
        oceanCurrentVelocity = tmpOceanCurrentVelocity;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    // Random ocean current direction
    oceanCurrentDirection += ran.nextFloat(0.005f) - 0.0025f;
    if (oceanCurrentDirection > 360.0f) {
      oceanCurrentDirection -= 360.0f;
    } else if (oceanCurrentDirection < 0.0f) {
      oceanCurrentDirection += 360.0f;
    }

    return new Weather(
      id,
      windU,
      windV,
      gust,
      wavesHeight,
      wavesDirection,
      wavesPeriod,
      wwavesHeight,
      wwavesDirection,
      wwavesPeriod,
      swell1Height,
      swell2Direction,
      swell1Period,
      swell2Height,
      swell2Direction,
      swell2Period,
      oceanCurrentVelocity,
      oceanCurrentDirection
    );
  }

  private static Ship initShipData(int id) {
    boolean valid = false;

    // Random heading
    heading += ran.nextFloat();
    if (heading > 360) {
      heading -= 360;
    } else if (heading < 0) {
      heading += 360;
    }

    // Random course
    if (ran.nextBoolean()) {
      course = heading + 5;
    } else {
      course = heading - 5;
    }
    if (course > 360) {
      course -= 360;
    } else if (course < 0) {
      course += 360;
    }

    // Random speed
    float tmpSpeed = speed;
    while (!valid) {
      speed += ran.nextFloat();
      if (speed < 0) {
        speed = tmpSpeed;
      } else {
        valid = true;
      }
    }

    // Ship ship = new Ship
    //   id,
    //   "Ship " + id,
    //   heading,
    //   course,
    //   speed,
    //   // fuelLevel
    // );
  }
}
