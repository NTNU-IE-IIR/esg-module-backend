package no.ntnu.idata2900.project.esg_module_backend.controllers;

import java.util.List;
import java.util.Map;
import no.ntnu.idata2900.project.esg_module_backend.TripService;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/trip")
public class TripController {
    private Logger logger = LoggerFactory.getLogger(TripController.class);
    private TripService tripService;
    private final TripLogRepository tripLogRepository;

    @Autowired
    public TripController(TripLogRepository tripLogRepository, TripService tripService) {
        this.tripService = tripService;
        this.tripLogRepository = tripLogRepository;
    }

    @GetMapping("/all")
    public List<TripLog> getAllTripLogs() {
        List<TripLog> tripLogs = tripLogRepository.findAll();
        logger.info("Found {} trip logs", tripLogs.size());
        for (TripLog tripLog : tripLogs) {
            logger.info("Trip log: {}", tripLog);
            logger.info("Trip log area: {}", tripLog.getArea());
        }
        return tripLogRepository.findAll();
    }

    @PostMapping("/start")
    public String startTrip() {
        System.out.println("Request received to start trip");
        tripService.startTrip();
        return "Trip started";
    }

    @RequestMapping("/stop")
    public String stopTrip(@RequestBody Map<String, String> requestBody) {
        String comments = requestBody.get("comments");
        String area = requestBody.get("area");
        tripService.stopTrip();
        tripLogRepository.save(tripService.getCurrentTrip().toTripLog(comments, area));
        return "Trip stopped";
    }
}
