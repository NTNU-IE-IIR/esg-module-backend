package no.ntnu.idata2900.project.backend.controllers;

import java.util.Optional;
import no.ntnu.idata2900.project.backend.dtos.ConfigurationDto;
import no.ntnu.idata2900.project.backend.models.Configuration;
import no.ntnu.idata2900.project.backend.services.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * The ConfigurationController class represents the REST controller for the configuraiton entity.
 * All HTTP traffic affiliated with configurations are handled in this class.
 *
 * @author Group 14
 * @version 0.1.0 (2025.05.20)
 * @see Configuration
 */
@RestController
@RequestMapping("/api/config")
public class ConfigurationController {
  private ConfigurationService configurationService;

  private final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);

  @Autowired
  public ConfigurationController(ConfigurationService configurationService) {
    this.configurationService = configurationService;
  }

  /**
   * Endpoint for getting the configuration with the specified registration mark.
   *
   * @param registrationMark The specified registration mark
   * @return <p><b>200 OK</b> if configuration exists (<i>body:</i> configuration)</p>
   *         <li><p><b>404 NOT FOUND</b> if configuration does not exist</p></li>
   */
  @GetMapping("/{registrationMark}")
  public ResponseEntity<Configuration> get(@PathVariable String registrationMark) {
    ResponseEntity<Configuration> response;
    Optional<Configuration> configuration = this.configurationService.get(registrationMark);
    if (configuration.isPresent()) {
      logger.info("Configuration exists, sending configuration data...");
      logger.info(configuration.get().getFuelType());
      response = ResponseEntity.ok().body(configuration.get());
    } else {
      logger.error("Configuration does not exist, sending error response...");
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Endpoint for adding the specified configuration.
   *
   * @param configuration The specified configuration
   * @return <p><b>200 OK</b> if the configuration is valid</p>
   *         <li><p><b>400 BAD REQUEST</b> if the configuration is invalid (<i>body:</i> error
   *         message)</p></li>
   */
  @PostMapping
  public ResponseEntity<String> add(@RequestBody Configuration configuration) {
    ResponseEntity<String> response;
    try {
      logger.info("Adding config with fuel type: {}", configuration.getFuelType());
      this.configurationService.add(configuration);
      logger.info("Configuration added, sending success response...");
      response = ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
      logger.error("Configuration invalid, sending error message");
      response = ResponseEntity.badRequest().body(e.getMessage());
    }
    return response;
  }

  /**
   * Endpoint for updating the configuration with the specified registration mark with the
   * specified configuration data.
   *
   * @param registrationMark  The specified registration mark
   * @param configurationDto  The specified configuration data
   * @return <p><b>200 OK</b> if the configuration exists and is updated with the configuration
   *         data</p>
   *         <li><p><b>400 BAD REQUEST</b> if the configuration data is invalid (<i>body:</i> error
   *         message)</p></li>
   *         <li><p><b>404 NOT FOUND</b> if the configuration does not exist</p></li>
   */
  @PutMapping("/{registrationMark}")
  public ResponseEntity<String> update(
      @PathVariable String registrationMark,
      @RequestBody ConfigurationDto configurationDto
  ) {
    ResponseEntity<String> response;
    logger.info("Updating config with fuel type: {}", configurationDto.getFuelType());
    try {
      if (this.configurationService.update(registrationMark, configurationDto)) {
        logger.info("Configuration updated, sending success response...");
        response = ResponseEntity.ok().build();
      } else {
        logger.error("Configuration does not exist, sendig error response...");
        response = ResponseEntity.notFound().build();
      }
    } catch (IllegalArgumentException e) {
      logger.error("Configuration data inalid, sending error message...");
      response = ResponseEntity.badRequest().body(e.getMessage());
    }
    return response;
  }

  /**
   * Endpoint for deleting the configuratio with the specified registration mark.
   *
   * @param registrationMark The specified registration mark
   * @return <p><b>200 OK</b> if the configuration exists and is deleted</p>
   *         <li><b>404 NOT FOUND</b> if the configuration does not exist</p></li>
   */
  @DeleteMapping("/{registrationMark}")
  public ResponseEntity<String> delete(@PathVariable String registrationMark) {
    ResponseEntity<String> response;
    if (this.configurationService.delete(registrationMark)) {
      logger.info("Configuration deleted, sending success response...");
      response = ResponseEntity.ok().build();
    } else {
      logger.error("Configuration does not exist, sendig error response...");
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Exception handler for handling exceptions caused by invalid formatting of path variables. This
   * method sends a response to the request causing the specified exception.
   *
   * @param e The specified exception
   * @return <b>400 BAD REQUEST</b> (<i>body:</i> error message)
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handlePathVarException(MethodArgumentTypeMismatchException e) {
    logger.error("Received request contains invalid formatting, sending error message...");
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  /**
   * Exception handler for handling exceptions caused by invalid formatting of request bodies. This
   * method sends a response to the request causing the specified exception.
   *
   * @param e The specified exception
   * @return <b>400 BAD REQUEST</b> (<i>body:</i> error message)
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleRequestBodyException(HttpMessageNotReadableException e) {
    logger.error("Received request body contains invalid formatting, sending error message...");
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}
