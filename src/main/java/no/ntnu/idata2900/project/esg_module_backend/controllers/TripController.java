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
import no.ntnu.idata2900.project.esg_module_backend.TripService;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.repositories.TripLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * The TripController class handles HTTP requests related to trip logs. It provides endpoints for
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
  private final TripLogRepository tripLogRepository;

  @Autowired
  public TripController(TripLogRepository tripLogRepository, TripService tripService) {
    this.tripService = tripService;
    this.tripLogRepository = tripLogRepository;
  }

  /**
   * Endpoint to get all trip logs.
   *
   * @return List of all trip logs
   */
  @Operation(summary = "Get all trip logs", description = "Retrieves a list of all recorded trip logs from the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved all trip logs",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = TripLog.class)))
  })
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
  public ResponseEntity<String> startTrip() {
    System.out.println("Request received to start trip");
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
  public String stopTrip(@RequestBody Map<String, String> requestBody) {
    String comments = requestBody.get("comments");
    String area = requestBody.get("area");

    if (area == null || area.isEmpty()) {
      return "Area must be specified";
    }

    area = area.strip();

    if (comments != null && !comments.isEmpty()) {
      comments = comments.strip();
    }

    tripService.stopTrip();
    tripLogRepository.save(tripService.getCurrentTrip().toTripLog(comments, area));
    return "Trip stopped";
  }

  /**
   * Endpoint to edit comments for an existing trip log.
   *
   * @param id          The ID of the trip log to edit
   * @param requestBody A map containing the 'comments' field to update
   * @return String indicating the status of the operation
   */
  @Operation(summary = "Edit trip comments", description = "Updates the comments for an existing trip log")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Comments successfully updated",
          content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
      @ApiResponse(responseCode = "404", description = "Trip log not found",
          content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
  })
  @PutMapping("/edit/{id}")
  public String editComments(
      @Parameter(description = "ID of the trip log to edit", required = true) @PathVariable int id,
      @Parameter(description = "Request body containing comments field", required = true)
      @RequestBody Map<String, String> requestBody) {
    String comments = requestBody.get("comments");

    if (comments != null && !comments.isEmpty()) {
      comments = comments.strip();
    }

    if (tripLogRepository.existsById(id)) {
      TripLog tripLog = tripLogRepository.findById(id).orElse(null);
      if (tripLog != null) {
        tripLog.setComments(comments);
        tripLogRepository.save(tripLog);
        logger.info("Updated comments for trip log with ID: {}", id);
        return "Comments updated for trip log with ID: " + id;
      }
    }
    logger.info("Trip log with ID: {} not found", id);
    return "Trip log with ID: " + id + " not found";
  }

  /**
   * Endpoint to delete a trip log.
   *
   * @param id The ID of the trip log to delete
   * @return String indicating the status of the operation
   */
  @Operation(summary = "Delete a trip log", description = "Removes a trip log from the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Trip log successfully deleted",
          content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
      @ApiResponse(responseCode = "404", description = "Trip log not found",
          content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
  })
  @DeleteMapping("/delete/{id}")
  public String deleteTrip(
      @Parameter(description = "ID of the trip log to delete", required = true) @PathVariable
      int id) {
    if (tripLogRepository.existsById(id)) {
      tripLogRepository.deleteById(id);
      logger.info("Deleted trip log with ID: {}", id);
      return "Trip log deleted";
    } else {
      logger.info("Trip log with ID: {} not found", id);
      return "Trip log not found";
    }
  }
}
