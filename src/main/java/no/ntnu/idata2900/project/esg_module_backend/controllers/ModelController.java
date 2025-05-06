package no.ntnu.idata2900.project.esg_module_backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.ntnu.idata2900.project.esg_module_backend.services.ModelService;

/**
 * The ModelController class represents the REST controller for communicating with the machine
 * learning model service.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.06)
 */
@RestController
public class ModelController {
  private ModelService modelService;

  private final Logger logger = LoggerFactory.getLogger(ModelController.class);

  /**
   * Constructor for the ModelController class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instane to instances of the class.
   * 
   * @param modelService The specified model service
   */
  @Autowired
  public ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  // TODO Add remaining status codes to docs
  /**
   * Endpoint for training the model in the machine learning service.
   * 
   * @return <p><b>200 OK</b> if the model was successfully trained</p>
   */
  @Operation(
    summary = "Train model",
    description = "Trains the model in the machine learning service"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Signals success"
    )
  })
  @CrossOrigin(origins = "http://localhost:5173")
  @PostMapping("/api/model/train")
  public ResponseEntity<Void> train() {
    ResponseEntity<String> modelResponse = this.modelService.train();
    HttpStatusCode statusCode = modelResponse.getStatusCode();
    logger.info(
      "Response from training model was returned [{}], forwarding response...",
      statusCode
    );
    return new ResponseEntity<>(statusCode);
  }
}
