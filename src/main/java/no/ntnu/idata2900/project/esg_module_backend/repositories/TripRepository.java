package no.ntnu.idata2900.project.esg_module_backend.repositories;

import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
    boolean findByRegistrationMark(String registrationMark);

    Long findIdByRegistrationMarkAndActiveTrue(String registrationMark);
}
