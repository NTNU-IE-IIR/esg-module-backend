package no.ntnu.idata2900.project.backend.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.backend.models.datapoints.Fuel;
import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import no.ntnu.idata2900.project.backend.models.datapoints.Weather;
import no.ntnu.idata2900.project.backend.services.datapoints.FuelService;
import no.ntnu.idata2900.project.backend.utils.Wind;

@Component
public class FuelGenerator {
  private FuelService fuelService;

  private static final float INTERVAL = 90.0f;
  private static final float BASE = 6.67f;

  private static final Random RAN = new Random();

  @Autowired
  public FuelGenerator(FuelService fuelService) {
    this.fuelService = fuelService;
  }

  public Fuel generate(Weather weather, MarineWeather marineWeather, Vessel vessel) {
    Fuel fuel = this.calculateFuelConsumption(weather, marineWeather, vessel);
    fuel.setVessel(vessel);
    // Add fuel consumption to storage
    this.fuelService.add(fuel);
    return fuel;
  }

  /**
   * Calculated fuel consumption based on the direction of various parameters included in the
   * {@link Weather weather data} and {@link MarineWeather marine weather data}.
   *
   * <p><b>How it works:</b></p>
   *
   * <p>First, the <code>INTERVAL</code> constant is used to define the upper and lower bounds for
   * positive and negative resistance.</p>
   *
   * <p>Positive resistance represents the front attack area, and all parameters who falls within
   * this area applies a resistance on the ship. Negative resistance on the other hand represents
   * the rear attack area, and all parameters who boosts the ship forward, and thus applies a
   * negative resistance, goes under here.</p>
   *
   * <p>The amount of parameters who go under either of the resistance types makes up a resistance
   * factor. The factor is used to determine a resistance multiplier, which is used along with the
   * calculated base consumption to calculate the resulting fuel consumption.</p>
   *
   * @return Calculated fuel consumption based on directional weather and marine weather parameters
   */
  public Fuel calculateFuelConsumption(Weather weather, MarineWeather marineWeather, Vessel vessel) {
    float windDirection = Wind.calculateWindDirection(weather.getWindV(), weather.getWindV());

    // Define collection of resistance parameters
    List<Float> resistParams = new ArrayList<>();
    resistParams.add(windDirection);
    resistParams.add(marineWeather.getWaves().getWavesDirection());
    resistParams.add(marineWeather.getWwaves().getWwavesDirection());
    resistParams.add(marineWeather.getSwellWaves().getSwell1Direction());
    resistParams.add(marineWeather.getSwellWaves().getSwell2Direction());
    resistParams.add(marineWeather.getOceanCurrentDirection());

    // Upper bound for positive resistance interval
    float upperPositive = vessel.getHeading() + (INTERVAL / 2) + 180.0f;
    if (upperPositive > 360.0f) {
      upperPositive -= 360.0f;
    }
    // Lower bound for positive resistance interval
    float lowerPositive = vessel.getHeading() - (INTERVAL / 2) + 180.0f;
    if (lowerPositive < 0.0f) {
      lowerPositive += 360.0f;
    }
    // Upper bound for negative resistance interval
    float upperNegative = vessel.getHeading() + INTERVAL / 2;
    if (upperNegative > 360.0f) {
      upperNegative -= 360.0f;
    }
    // Lower bound for negative resistance interval
    float lowerNegative = vessel.getHeading() - INTERVAL / 2;
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

    float baseConsumption =
        BASE * (float) Math.pow(vessel.getSpeed() / 12, 3);

    float resistanceMultiplier;

    switch (resist) {
      case -6:
        resistanceMultiplier = 0.6f;
        break;
      case -5:
        resistanceMultiplier = 0.65f;
        break;
      case -4:
        resistanceMultiplier = 0.7f;
        break;
      case -3:
        resistanceMultiplier = 0.75f;
        break;
      case -2:
        resistanceMultiplier = 0.8f;
        break;
      case -1:
        resistanceMultiplier = 0.9f;
        break;
      case 1:
        resistanceMultiplier = 1.1f;
        break;
      case 2:
        resistanceMultiplier = 1.2f;
        break;
      case 3:
        resistanceMultiplier = 1.4f;
        break;
      case 4:
        resistanceMultiplier = 1.6f;
        break;
      case 5:
        resistanceMultiplier = 1.8f;
        break;
      case 6:
        resistanceMultiplier = 2.0f;
        break;
      default:
        resistanceMultiplier = 1.0f;
        break;
    }

    float consumption = baseConsumption * resistanceMultiplier;

    return new Fuel(consumption, 0.0f, 0.0f);
  }
}
