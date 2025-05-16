package no.ntnu.idata2900.project.esg_module_backend.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.dtos.EsgDto;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling Environmental, Social, and Governance (ESG) data operations.
 * This service provides methods for retrieving and calculating ESG data based on vessel trips.
 */
@Service
public class EsgService {
  /**
   * Repository for accessing trip data.
   */
  private final TripRepository tripRepository;

  /**
   * Constructs a new EsgService with the specified TripRepository.
   *
   * @param tripRepository The repository for accessing trip data
   */
  @Autowired
  public EsgService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  /**
   * Retrieves ESG data for a vessel identified by its registration mark.
   * This method fetches all trips for the specified vessel and calculates the ESG data.
   *
   * @param registrationMark The registration mark of the vessel
   * @return Optional containing the ESG data if trips are found, or an empty Optional if no trips exist
   */
  public Optional<EsgDto> esgFromVessel(String registrationMark) {
    Optional<EsgDto> esgDto;

    List<Trip> trips = tripRepository.findByRegistrationMark(registrationMark);

    if (!trips.isEmpty()) {
      esgDto = esgFromTrips(trips);
    } else {
      esgDto = Optional.empty();
    }

    return esgDto;
  }

  /**
   * Retrieves ESG data for a vessel identified by its registration mark within a specific time period.
   * This method fetches all trips for the specified vessel that started within the given time range
   * and calculates the ESG data.
   *
   * @param registrationMark The registration mark of the vessel
   * @param start The start timestamp in milliseconds since epoch
   * @param end The end timestamp in milliseconds since epoch
   * @return Optional containing the ESG data if trips are found, or an empty Optional if no trips exist
   */
  public Optional<EsgDto> esgFromVesselTime(String registrationMark, long start, long end) {
    Optional<EsgDto> esgDto;
    Instant startInstant = Instant.ofEpochMilli(start);
    Instant endInstant = Instant.ofEpochMilli(end);
    List<Trip> trips =
        tripRepository.findByRegistrationMarkAndStartDateBetween(registrationMark, startInstant,
            endInstant);

    if (!trips.isEmpty()) {
      esgDto = esgFromTrips(trips);
    } else {
      esgDto = Optional.empty();
    }

    return esgDto;
  }

  /**
   * Calculates ESG data from a list of trips.
   * This method sums up the fuel consumption and fish weight from all trips
   * and creates an EsgDto with these values.
   *
   * @param trips The list of trips to calculate ESG data from
   * @return Optional containing the calculated ESG data if the list is not empty,
   *         or an empty Optional if the list is empty
   */
  public Optional<EsgDto> esgFromTrips(List<Trip> trips) {
    Optional<EsgDto> esgDto;

    float fuelConsumption = 0;
    float fishWeight = 0;

    if (!trips.isEmpty()) {
      for (Trip trip : trips) {
        fuelConsumption = fuelConsumption + trip.getFuelConsumed();
        fishWeight = fishWeight + trip.getFishWeight();
      }
      esgDto = Optional.of(new EsgDto(fuelConsumption, fishWeight));
    } else {
      esgDto = Optional.empty();
    }

    return esgDto;
  }
}
