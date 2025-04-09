package no.ntnu.idata2900.project.esg_module_backend.repositories;

import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripLogRepository extends JpaRepository<TripLog, Integer> {
}
