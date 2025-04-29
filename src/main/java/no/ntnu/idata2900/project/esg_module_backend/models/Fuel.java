package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Fuel class represents fuel consumption divided into different posts. The class is part of
 * the data packaged into ship data.
 * 
 * <p>Each post represents an individual section of the ship where fuel is consumed. The different
 * posts include:</p>
 * 
 * <ul>
 *   <li><b>Drift:</b> Fuel consumption caused by the motors for ship movement</li>
 *   <li><b>Production:</b> Fuel consumption caused by generating electricity for fish catching
 *   mechanisms</li>
 *   <li><b>Hotel:</b> Fuel consumption caused by generating electricity for ship housing
 *   operations (e.g. lighting and heating)</li>
 * </ul>
 * 
 * @author Group 14
 * @version v0.1.1 (2025.04.29)
 * @see Ship
 */
@Entity(name = "fuel")
public class Fuel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private float drift;
  private float production;
  private float hotel;

  /**
   * Constructor for the Fuel class.
   * 
   * @param drift       The specified drift fuel consumption
   * @param production  The specified production fuel consumption
   * @param hotel       The specified hotel fuel consumption
   */
  public Fuel(float drift, float production, float hotel) {
    this.drift = drift;
    this.production = production;
    this.hotel = hotel;
  }

  /**
   * Getter for fuel consumption caused by drift.
   * 
   * @return Drift fuel consumption
   */
  public float getDrift() {
    return this.drift;
  }

  /**
   * Getter for fuel consumption caused by production.
   * 
   * @return Production fuel consumption
   */
  public float getProduction() {
    return this.production;
  }

  /**
   * Getter for fuel consumption caused by hotel.
   * 
   * @return Hotel fuel consumption
   */
  public float getHotel() {
    return this.hotel;
  }

  /**
   * Returns the total fuel consumption aggregated over all posts.
   * 
   * @return Total fuel consumption
   */
  public float getTotal() {
    return this.drift + this.production + this.hotel;
  }
}
