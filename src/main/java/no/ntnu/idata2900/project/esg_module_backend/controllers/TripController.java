package no.ntnu.idata2900.project.esg_module_backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import no.ntnu.idata2900.project.esg_module_backend.services.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TripController class handles HTTP requests related to trips. It provides endpoints for
 * starting and stopping trips, as well as seeing if there is an active trip.
 * The class uses the TripService to manage trip-related operations and the TripRepository to
 * handle database communication.
 *
 * @author Group 14
 * @version v0.2.0 (2025.05.01)
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
  public ResponseEntity<String> start(@RequestBody Map<String, String> requestBody) {
    try {
      String name = validateString(requestBody.get("name"), "Name must be specified");
      String registrationMark = validateString(requestBody.get("registrationMark"),
          "Registration mark must be specified");

      tripService.startTrip(registrationMark, name);

      logger.info("New trip successfully started");
      return ResponseEntity.ok("Trip successfully started");
    } catch (IllegalArgumentException ex) {
      logger.warn("Invalid request for starting a new trip: {}", ex.getMessage());
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Endpoint to stop a trip. This method is called when the user wants to end an ongoing trip.
   *
   * @param tripId      The id of the trip the user wants to stop
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
  @PostMapping("/stop/{tripId}")
  public ResponseEntity<String> stop(@RequestBody Map<String, String> requestBody,
                                     @PathVariable Long tripId) {
    try {
      String area = validateString(requestBody.get("area"), "Area must be specified");
      String comments = stripComments(requestBody.get("comments"));

      tripService.stopTrip(tripId, comments, area);

      logger.info("Trip with ID: {} successfully stopped and saved", tripId);
      return ResponseEntity.ok("Trip successfully stopped and saved");
    } catch (IllegalArgumentException ex) {
      logger.warn("Invalid request for stopping trip with ID {}: {}", tripId, ex.getMessage());
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      logger.error("Unexpected error while stopping the trip with ID {}: {}", tripId,
          ex.getMessage());
      return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Validates the provided string value to ensure it is not null or blank. If the value is
   * invalid, an {@code IllegalArgumentException} is thrown with the provided error message.
   * If valid, the string is stripped of leading and trailing whitespaces.
   *
   * @param value        The string value to validate.
   * @param errorMessage The error message to include in the exception if validation fails.
   * @return The validated string, stripped of leading and trailing whitespaces.
   * @throws IllegalArgumentException if the provided string is null or blank.
   */
  private String validateString(String value, String errorMessage) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(errorMessage);
    }
    return value.strip();
  }

  private String stripComments(String value) {
    return (value != null && !value.isBlank()) ? value.strip() : null;
  }

  @Operation(summary = "Get trip ID if active", description = "Gets the ID of the current trip if it is active")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Active trip found",
          content = @Content(mediaType = "application/json", schema = @Schema(type = "integer", format = "int64"))),
      @ApiResponse(responseCode = "404", description = "No active trip found",
          content = @Content)
  })
  @GetMapping("/tripId/{registrationMark}")
  public ResponseEntity<Long> findTripIdIfActive(@PathVariable String registrationMark) {
    ResponseEntity<Long> response;

    Long tripId = tripService.findTripIdIfActive(registrationMark.strip());

    if (tripId == null) {
      response = ResponseEntity.notFound().build();
      logger.info("Could not find trip with registration mark: {}", registrationMark);
    } else {
      response = ResponseEntity.ok(tripId);
      logger.info("Found trip with ID: {} for registration mark: {}", tripId, registrationMark);
    }

    return response;
  }

// TODO: move to a DataPoint controller
//  @Operation(summary = "Get current trip data points", description = "Gets the current trip data points")
//  @ApiResponses(value = {
//  })
//  @GetMapping("/data")
//  public ResponseEntity<List<ShipDto>> getCurrentTripDataPoints() {
//    ResponseEntity<List<ShipDto>> response;
//    List<ShipDto> dataPoints = tripService.getTripDataPoints();
//    if (dataPoints.isEmpty()) {
//      response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    } else {
//      response = new ResponseEntity<>(dataPoints, HttpStatus.OK);
//    }
//    return response;
//  }
}
