package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FishingOperation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String startDate;
  private String endDate;
  private Long fuelConsumed;
  private Long fishAmount;
  private String fishingMethod;

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public Long getFuelConsumed() {
    return fuelConsumed;
  }

  public Long getFishAmount() {
    return fishAmount;
  }

  public String getFishingMethod() {
    return fishingMethod;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
