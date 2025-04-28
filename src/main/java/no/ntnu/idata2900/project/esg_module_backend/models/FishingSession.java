package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Set;

@Entity
public class FishingSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String startDate;
  private String endDate;
  private Long fuelConsumed;
  private Set<FishingOperation> operations;

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public Long getFuelConsumed() {
    return fuelConsumed;
  }

  public Set<FishingOperation> getOperations() {
    return operations;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
