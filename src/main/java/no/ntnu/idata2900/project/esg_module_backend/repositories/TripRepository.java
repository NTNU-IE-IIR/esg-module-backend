package no.ntnu.idata2900.project.esg_module_backend.repositories;

import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.springframework.data.repository.CrudRepository;

interface TripRepository extends CrudRepository<Trip, Long> {
}
