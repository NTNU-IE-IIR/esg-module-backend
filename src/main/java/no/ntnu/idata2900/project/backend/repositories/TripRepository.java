package no.ntnu.idata2900.project.backend.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.backend.models.Trip;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * The TripRepository class represents the repository for {@link Trip trips}.
 */
public interface TripRepository extends CrudRepository<Trip, Long> {

  Optional<Trip> findTripByRegistrationMarkAndActiveTrue(String registrationMark);

  List<Trip> findByRegistrationMarkAndActiveFalse(String registrationMark);

  @Modifying
  @Query(
      "UPDATE Trip t SET t.active = false WHERE t.registrationMark = :registrationMark AND "
      + "t.active = true"
  )
  void deactivateActiveTripsByRegistrationMark(@Param("registrationMark") String registrationMark);

  List<Trip> findByActiveTrue();

  List<Trip> findByRegistrationMarkAndStartDateBetween(
      String registrationMark,
      Instant startDateAfter,
      Instant startDateBefore
  );

  List<Trip> findByRegistrationMark(String registrationMark);
}
