package no.ntnu.idata2900.project.esg_module_backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;

import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.services.TripService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TripController class handles HTTP requests related to trips. It provides endpoints for
 * starting, stopping, editing, and deleting trip logs. The class uses the TripService to manage
 * trip-related operations and the TripLogRepository to interact with the database.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/trip")
@Tag(name = "Trip Controller", description = "API for managing fishing trip logs")
public class TripController {
    private final Logger logger = LoggerFactory.getLogger(TripController.class);
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    /**
     * Endpoint to start a trip. This method is called when the user wants to start a new trip.
     *
     * @return ResponseEntity containing a message indicating that the trip has started.
     */
    @Operation(summary = "Start a new trip", description = "Initiates a new fishing trip recording")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully started",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @PostMapping("/start")
    public ResponseEntity<String> start() {
        logger.info("Starting a new trip");
        tripService.startTrip();
        return ResponseEntity.ok("Trip started");
    }

    /**
     * Endpoint to stop a trip. This method is called when the user wants to end an ongoing trip.
     *
     * @param requestBody A map containing trip details: 'comments' (optional) and 'area' (required)
     * @return String indicating the status of the operation
     */
    @Operation(summary = "Stop the current trip", description = "Ends the current fishing trip and saves it to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully stopped and saved",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid request - area not specified",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @PostMapping("/stop")
    public ResponseEntity<String> stop(@RequestBody Map<String, String> requestBody) {
        String comments = requestBody.get("comments");
        String area = requestBody.get("area");

        ResponseEntity<String> response;

        //guard clauses and cleaning data
        if (area == null || area.isEmpty()) {
            return new ResponseEntity<>("Area must be specified", HttpStatus.BAD_REQUEST);
        }

        area = area.strip();

        if (comments != null && !comments.isEmpty()) {
            comments = comments.strip();
        }

        tripService.stopTrip();
//        TODO: temporarliy commented out because trip logic will be changed
//        if (tripService.saveTrip(tripService.getCurrentTrip().toTripLog(comments, area))) {
//            logger.info("Trip successfully stopped");
//            response = new ResponseEntity<>("Trip successfully saved", HttpStatus.OK);
//        } else {
//            logger.error("Trip could not be saved");
//            response = new ResponseEntity<>("Trip could not be saved", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        response = new ResponseEntity<>("Trip successfully saved", HttpStatus.OK);

        return response;
    }

    /**
     * Endpoint to check if a trip is active. Returns true if a trip is active, false otherwise.
     *
     * @return boolean indicating whether a trip is active or not.
     */
    @Operation(summary = "Check if a trip is active", description = "Checks if a fishing trip is currently in progress")
    @ApiResponses(value = {

    })
    @GetMapping("/active")
    public ResponseEntity<Boolean> isTripActive() {
        return new ResponseEntity<>(tripService.isTripActive(), HttpStatus.OK);
    }

    @Operation(summary = "Get current trip data points", description = "Gets the current trip data points")
    @ApiResponses(value = {

    })
    @GetMapping("/data")
    public ResponseEntity<List<ShipDto>> getCurrentTripDataPoints() {
        ResponseEntity<List<ShipDto>> response;

        List<ShipDto> dataPoints = tripService.getTripDataPoints();
        if (dataPoints.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(dataPoints, HttpStatus.OK);
        }
        return response;
    }


}
