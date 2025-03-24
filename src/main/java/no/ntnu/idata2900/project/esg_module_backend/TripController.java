package no.ntnu.idata2900.project.esg_module_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping("/start")
    public String startTrip() {
        System.out.println("Request received to start trip");
        tripService.startTrip();
        return "Trip started";
    }

    @RequestMapping("/stop")
    public String stopTrip() {
        tripService.stopTrip();
        return "Trip stopped";
    }
}
