package no.ntnu.idata2900.project.esg_module_backend.repositories;

import java.util.List;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TripRepository extends CrudRepository<Trip, Long> {

    Optional<Trip> findTripByRegistrationMarkAndActiveTrue(String registrationMark);

    List<Trip> findByRegistrationMarkAndActiveFalse(String registrationMark);

    @Modifying
    @Query("UPDATE Trip t SET t.active = false WHERE t.registrationMark = :registrationMark AND t.active = true")
    void deactivateActiveTripsByRegistrationMark(@Param("registrationMark") String registrationMark);

    List<Trip> findByActiveTrue();
}
