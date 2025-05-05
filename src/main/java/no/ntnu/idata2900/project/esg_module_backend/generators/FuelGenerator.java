package no.ntnu.idata2900.project.esg_module_backend.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Fuel;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import no.ntnu.idata2900.project.esg_module_backend.services.data_points.FuelService;
import no.ntnu.idata2900.project.esg_module_backend.utils.Wind;

@Component
public class FuelGenerator {
  private FuelService fuelService;

  private static final float INTERVAL = 90.0f;

  private static final Random RAN = new Random();

  @Autowired
  public FuelGenerator(FuelService fuelService) {
    this.fuelService = fuelService;
  }

  public void generate(Weather weather, MarineWeather marineWeather, Vessel vessel) {
    Fuel fuel = this.calculateFuelConsumption(weather, marineWeather, vessel);
    fuel.setVessel(vessel);
    // Add fuel consumption to storage
    this.fuelService.add(fuel);
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
   * factor. The factor is used to define the interval of which random fuel consumption can be
   * generated (e.g. bigger resistance factor implies greater fuel consumption).</p>
   * 
   * <p>In the end, the randomly generated fuel consumption is divided among posts.</p>
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

    // Calculate fuel consumption by posts
    float hotel = consumption / 3;
    float production = 0;
    float drift = consumption - hotel;

    return new Fuel(drift, production, hotel);
  }
}
