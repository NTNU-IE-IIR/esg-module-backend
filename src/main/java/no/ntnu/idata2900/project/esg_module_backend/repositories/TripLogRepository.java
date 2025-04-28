package no.ntnu.idata2900.project.esg_module_backend.repositories;

import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and performing database operations on TripLog entities.
 * Extends JpaRepository to provide standard CRUD operations and pagination functionality.
 * The entity class associated with this repository is TripLog, and the primary key type is Integer.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.22)
 */
public interface TripLogRepository extends JpaRepository<TripLog, Long> {
}
