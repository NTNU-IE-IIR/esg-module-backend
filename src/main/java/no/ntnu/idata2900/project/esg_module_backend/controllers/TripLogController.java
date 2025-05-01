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
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.services.TripLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles HTTP requests relating to trip logs. It provides endpoints for CRUD operations
 * on trip logs.
 *
 * @author Group 14
 * @version 0.1.0 (2025.04.29)
 */
@CrossOrigin(origins = "http://localhost:5137")
@RestController
@RequestMapping("/api/logs")
@Tag(name = "Trip Log Controller", description = "API for managing fishing trip logs")
public class TripLogController {
  private final Logger logger = LoggerFactory.getLogger(TripLogController.class);
  private final TripLogService tripLogService;

  @Autowired
  public TripLogController(TripLogService tripLogService) {
    this.tripLogService = tripLogService;
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
    return tripLogService.getAllTripLogs();
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
  public ResponseEntity<String> editComments(
      @Parameter(description = "ID of the trip log to edit", required = true) @PathVariable Long id,
      @Parameter(description = "Request body containing comments field", required = true)
      @RequestBody Map<String, String> requestBody) {
    String comments = requestBody.get("comments");
    ResponseEntity<String> response;

    if (comments != null && !comments.isEmpty()) {
      comments = comments.strip();
    }

    if (tripLogService.editComments(comments, id)) {
      response = new ResponseEntity<>("Trip comment was updated", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Trip log not found", HttpStatus.NOT_FOUND);
    }

    return response;
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
  public ResponseEntity<String> deleteTrip(
      @Parameter(description = "ID of the trip log to delete", required = true) @PathVariable
      Long id) {
    ResponseEntity<String> response;
    if (tripLogService.deleteTripLog(id)) {
      response = new ResponseEntity<>("Trip log deleted", HttpStatus.OK);
      logger.info("Deleted trip log with ID: {}", id);
    } else {
      response = new ResponseEntity<>("Trip log not found", HttpStatus.NOT_FOUND);
      logger.info("Trip log with ID: {} not found", id);
    }
    return response;
  }
}
