package no.ntnu.idata2900.project.esg_module_backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import no.ntnu.idata2900.project.esg_module_backend.models.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.Fuel;
import no.ntnu.idata2900.project.esg_module_backend.models.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.Ship;
import no.ntnu.idata2900.project.esg_module_backend.models.SwellWaves;
import no.ntnu.idata2900.project.esg_module_backend.models.Waves;
import no.ntnu.idata2900.project.esg_module_backend.models.Weather;
import no.ntnu.idata2900.project.esg_module_backend.models.WindWaves;

// TODO Refactor class to use trips

/**
 * The FakeDataInitializer class represents a data initializer for simulated data. The simulated
 * data is randomly generated, but in an attempt to ensure that the relationships within the data
 * is somewhat realistic, the randomness may be slightly manipulated. See method documentation for
 * further information.
 *
 * @author Group 14
 * @version v0.1.2 (2025.04.24)
 */
public class FakeDataInitializer {
  private final List<DataPoint> data;
  private final int num;

  private static final Random RAN = new Random();

  // Initial values
  private float lat;
  private float lng;

  private float windU;
  private float windV;
  private float gust;
  private float wavesHeight;
  private float wavesDirection;
  private float wavesPeriod;
  private float wwavesHeight;
  private float wwavesDirection;
  private float wwavesPeriod;
  private float swell1Height;
  private float swell1Direction;
  private float swell1Period;
  private float swell2Height;
  private float swell2Direction;
  private float swell2Period;
  private float oceanCurrentVelocity;
  private float oceanCurrentDirection;

  private float heading;
  private float course;
  private float speed;

  public FakeDataInitializer(int num) {
    this.data = new ArrayList<>();
    this.num = num;

    this.lat = 61.60314f;
    this.lng = 5.044566f;

    this.windU = 5.0f;
    this.windV = 5.0f;
    this.gust = 10.0f;
    this.wavesHeight = 0.5f;
    this.wavesDirection = 30.0f;
    this.wavesPeriod = 8.0f;
    this.wwavesHeight = 0.3f;
    this.wwavesDirection = 15.0f;
    this.wwavesPeriod = 4.0f;
    this.swell1Height = 0.5f;
    this.swell1Direction = 60.0f;
    this.swell1Period = 7.0f;
    this.swell2Height = 0.3f;
    this.swell2Direction = 55.0f;
    this.swell2Period = 9.0f;
    this.oceanCurrentVelocity = 0.2f;
    this.oceanCurrentDirection = 230.0f;

    this.heading = 315.0f;
    this.course = 310.0f;
    this.speed = 7.0f;
  }

  /**
   * Returns a list of fake data points containing random data.
   *
   * @return A list of fake data points
   */
  public List<DataPoint> init() {
    // UNIX timestamp in seconds
    int ts = 1739438130;

    for (int i = 1; i <= num; i++) {
      DataPoint dp = new DataPoint(ts, initPositionData());

      Weather weather = initWeatherData();
      MarineWeather marineWeather = initMarineWeatherData();
      Ship ship = initShipData();

      weather.setDp(dp);
      marineWeather.setDp(dp);
      ship.setDp(dp);

      // TODO This does not work
      data.add(dp);

      // Increment time by 1 minute
      ts += 60;
    }

    return data;
  }

  /**
   * Returns a position with a random latitude and longitude. The values are based off of the
   * previously set latitude and longitude. If either of the values exceed their respective
   * boundaries, they are reset and produced over until they become valid.
   *
   * @return A position with a random latitude and longitude
   * @see Position
   */
  private Position initPositionData() {
    float tmpLat = lat;
    float tmpLng = lng;

    boolean valid = false;

    while (!valid) {
      lat += RAN.nextFloat(0.01f) - 0.005f;
      if (lat < -90 && lat > 90) {
        lat = tmpLat;
      } else {
        valid = true;
      }
    }

    // Reset guard condition
    valid = false;

    while (!valid) {
      lng += RAN.nextFloat(0.01f) - 0.005f;
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
   * @see Weather
   */
  private Weather initWeatherData() {
    boolean valid = false;

    // Random wind U vector
    float tmpWindU = windU;
    while (!valid) {
      windU += RAN.nextFloat() - 0.5f;
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
      windV += RAN.nextFloat() - 0.5f;
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
      gust += RAN.nextFloat(2) - 1.0f;
      if (gust > 20.0f || gust < 0.0f) {
        gust = tmpGust;
      } else {
        valid = true;
      }
    }

    return new Weather(windU, windV, gust);
  }

  private MarineWeather initMarineWeatherData() {
    boolean valid = false;

    // Random waves height
    float tmpWavesHeight = wavesHeight;
    while (!valid) {
      wavesHeight += RAN.nextFloat(6) - 3.0f;
      if (wavesHeight > 6.0f || wavesHeight < 0.0f) {
        wavesHeight = tmpWavesHeight;
      } else {
        valid = true;
      }
    }

    // Random waves direction
    wavesDirection += RAN.nextFloat(2) - 1.0f;
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
      wavesPeriod += RAN.nextFloat(2) - 1.0f;
      if (wavesPeriod > 15.0f || wavesPeriod < 0.0f) {
        wavesPeriod = tmpWavesPeriod;
      } else {
        valid = true;
      }
    }

    Waves waves = new Waves(wavesHeight, wavesHeight, wavesPeriod);

    // Reset guard condition
    valid = false;

    // Random wind waves height
    float tmpWwavesHeight = wwavesHeight;
    while (!valid) {
      wwavesHeight += RAN.nextFloat(0.5f) - 0.25f;
      if (wwavesHeight > 2.0f || wwavesHeight < 0.0f) {
        wwavesHeight = tmpWwavesHeight;
      } else {
        valid = true;
      }
    }

    // Random wind waves direction
    wwavesDirection += RAN.nextFloat(2) - 1.0f;
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
      wwavesPeriod += RAN.nextFloat() - 0.5f;
      if (wwavesPeriod > 10.0f || wwavesPeriod < 0.0f) {
        wwavesPeriod = tmpWwavesPeriod;
      } else {
        valid = true;
      }
    }

    WindWaves wwaves = new WindWaves(wwavesHeight, wwavesHeight, wwavesPeriod);

    // Reset guard condition
    valid = false;

    // Random class 1 swell waves height
    float tmpSwell1Height = swell1Height;
    while (!valid) {
      swell1Height += RAN.nextFloat(0.5f) - 0.25f;
      if (swell1Height > 1.0f || swell1Height < 0.0f) {
        swell1Height = tmpSwell1Height;
      } else {
        valid = true;
      }
    }

    // Random class 1 swell waves direction
    swell1Direction += RAN.nextFloat(2) - 1.0f;
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
      swell1Period += RAN.nextFloat(4) - 2.0f;
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
      swell2Height += RAN.nextFloat(0.2f) - 0.1f;
      if (swell2Height > 0.6f || swell2Height < 0.0f) {
        swell2Height = tmpSwell2Height;
      } else {
        valid = true;
      }
    }

    // Random class 2 swell waves direction
    swell2Direction += RAN.nextFloat(2) - 1.0f;
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
      swell2Period += RAN.nextFloat(2) - 1.0f;
      if (swell2Period > 15.0f || swell2Period < 0.0f) {
        swell2Period = tmpSwell2Period;
      } else {
        valid = true;
      }
    }

    SwellWaves swellWaves = new SwellWaves(
      swell1Height,
      swell1Height,
      swell1Period,
      swell2Height,
      swell2Height,
      swell2Period
    );

    // Reset guard condition
    valid = false;

    // Random ocean current velocity
    float tmpOceanCurrentVelocity = oceanCurrentVelocity;
    while (!valid) {
      oceanCurrentVelocity += RAN.nextFloat(0.002f) - 0.001f;
      if (oceanCurrentVelocity > 0.25f || oceanCurrentVelocity < 0.0f) {
        oceanCurrentVelocity = tmpOceanCurrentVelocity;
      } else {
        valid = true;
      }
    }

    // Random ocean current direction
    oceanCurrentDirection += RAN.nextFloat(0.005f) - 0.0025f;
    if (oceanCurrentDirection > 360.0f) {
      oceanCurrentDirection -= 360.0f;
    } else if (oceanCurrentDirection < 0.0f) {
      oceanCurrentDirection += 360.0f;
    }

    return new MarineWeather(
      waves,
      wwaves,
      swellWaves,
      oceanCurrentVelocity,
      oceanCurrentDirection
    );
  }

  /**
   * Returns ship data containing randomly generated parameters. The values are based off of the
   * previously set parameters. If any of the values exceed their respective boundaries, they are
   * either corrected or reset until become valid.
   *
   * @param id The specified ID
   * @return Randomly generated ship data
   * @see Ship
   */
  private Ship initShipData() {
    boolean valid = false;

    // Random heading
    heading += RAN.nextFloat();
    if (heading > 360.0f) {
      heading -= 360.0f;
    } else if (heading < 0.0f) {
      heading += 360.0f;
    }

    // Random course
    if (RAN.nextBoolean()) {
      course = heading + 5.0f;
    } else {
      course = heading - 5.0f;
    }
    if (course > 360.0f) {
      course -= 360.0f;
    } else if (course < 0.0f) {
      course += 360.0f;
    }

    // Random speed
    float tmpSpeed = speed;
    while (!valid) {
      speed += RAN.nextFloat(2) - 1.0f;
      if (speed > 15.0f || speed < 0.0f) {
        speed = tmpSpeed;
      } else {
        valid = true;
      }
    }

    return new Ship(
        "Ship",
        heading,
        course,
        speed,
        calculateFuelConsumption()
    );
  }

  /**
   * Returns the calculated fuel level based on the direction of various parameters included in the
   * {@link Weather weather data}.
   *
   * <p><b>How it works:</b></p>
   *
   * <p>First, an interval angle is defined to define the area of which parameters can attack a
   * ship from the front and the rear. Second, this interval is used to define the upper and lower
   * bounds for positive and negative resistance.</p>
   *
   * <p>Positive resistance represents the front attack area, and all parameters who falls within
   * this area applies a resistance on the ship. Negative resistance on the other hand represents
   * the rear attack area, and all parameters who boosts the ship forward, and thus applies a
   * negative resistance, goes under here.</p>
   *
   * <p>The amount of parameters who go under either of the resistance types makes up a resistance
   * factor. The factor is used to define the interval of which random fuel consumption can be
   * generated (e.g. bigger resistance factor implies greater fuel consumption). This consumption
   * is in the end used to decrease the total fuel level on the ship.</p>
   *
   * @return Calculated fuel level based on directional weather parameters
   */
  private Fuel calculateFuelConsumption() {
    float windDirection = calculateWindDirection();

    // Collect all resistance parameters in a single variable
    List<Float> resistParams = new ArrayList<>();
    resistParams.add(windDirection);
    resistParams.add(wavesDirection);
    resistParams.add(wwavesDirection);
    resistParams.add(swell1Direction);
    resistParams.add(swell2Direction);
    resistParams.add(oceanCurrentDirection);

    float interval = 90.0f;

    // Upper bound for positive resistance interval
    float upperPositive = heading += (interval / 2) + 180.0f;
    if (upperPositive > 360.0f) {
      upperPositive -= 360.0f;
    }

    // Lower bound for positive resistance interval
    float lowerPositive = heading -= (interval / 2) + 180.0f;
    if (lowerPositive < 0.0f) {
      lowerPositive += 360.0f;
    }

    // Upper bound for negative resistance interval
    float upperNegative = heading += interval / 2;
    if (upperNegative > 360.0f) {
      upperNegative -= 360.0f;
    }

    // Lower bound for negative resistance interval
    float lowerNegative = heading -= interval / 2;
    if (lowerNegative < 0.0f) {
      lowerNegative += 360.0f;
    }

    int resist = 0;

    // Adjust resistance factor
    for (float param : resistParams) {
      if (param <= upperPositive || param >= lowerPositive) {
        resist++;
      } else if (param <= upperNegative || param >= lowerNegative) {
        resist--;
      }
    }

    float consumption = 0.0f;

    // Random fuel consumption based on resistance factor
    switch (resist) {
      case -6:
        consumption += RAN.nextFloat(0.06f, 0.12f);
        break;
      case -5:
        consumption += RAN.nextFloat(0.12f, 0.18f);
        break;
      case -4:
        consumption += RAN.nextFloat(0.18f, 0.24f);
        break;
      case -3:
        consumption += RAN.nextFloat(0.24f, 0.30f);
        break;
      case -2:
        consumption += RAN.nextFloat(0.30f, 0.36f);
        break;
      case -1:
        consumption += RAN.nextFloat(0.36f, 0.42f);
        break;
      case 1:
        consumption += RAN.nextFloat(0.59f, 0.65f);
        break;
      case 2:
        consumption += RAN.nextFloat(0.65f, 0.71f);
        break;
      case 3:
        consumption += RAN.nextFloat(0.71f, 0.77f);
        break;
      case 4:
        consumption += RAN.nextFloat(0.77f, 0.83f);
        break;
      case 5:
        consumption += RAN.nextFloat(0.83f, 0.89f);
        break;
      case 6:
        consumption += RAN.nextFloat(0.89f, 0.95f);
        break;
      default:
        consumption += RAN.nextFloat(0.42f, 0.59f);
        break;
    }

    float dividedConsumption = consumption / 3;

    return new Fuel(dividedConsumption, dividedConsumption, dividedConsumption);
  }

  /**
   * Returns the calculated wind direction based on the two-dimensional vector derived from the
   * wind <code>u</code> and <code>v</code> values.
   *
   * <p>A two-dimensional reference vector is used to calculated the appropriate angle. The
   * reference vector is pointed directly towards North.</p>
   *
   * @return The calculated wind direction
   */
  private float calculateWindDirection() {
    // Reference vector
    float refU = 0.0f;
    float refV = 1.0f;

    double cos = (refU * windU + refV * windV) / (
        Math.sqrt(Math.pow(refU, 2) + Math.pow(refV, 2)) *
            Math.sqrt(Math.pow(windU, 2) + Math.pow(windV, 2))
    );
    double ang = Math.acos(cos);

    if (windU < 0) {
      ang = 360.0 - ang;
    }

    return (float) ang;
  }
}
