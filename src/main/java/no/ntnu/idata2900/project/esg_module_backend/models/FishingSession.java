package no.ntnu.idata2900.project.esg_module_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class FishingSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String startDate;
  private String endDate;
  private Long fuelConsumed;
  @OneToMany(mappedBy = "fishingSession")
  private Set<FishingOperation> operations;
  @ManyToOne
  @JsonIgnore
  private Trip trip;

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

  public Long getId() {
    return id;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }
}
