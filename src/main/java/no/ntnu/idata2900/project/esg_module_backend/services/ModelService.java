package no.ntnu.idata2900.project.esg_module_backend.services;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.VesselRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.ntnu.idata2900.project.esg_module_backend.RestTemplateResponseErrorHandler;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.DataPointRepository;

/**
 * The ModelService class represents the service for communicating with the machine learning model
 * service.
 * 
 * @author Group 14
 * @version v0.3.0 (2025.05.13)
 */
@Service
public class ModelService {
  private DataPointRepository repo;
  private VesselRepository vesselRepo;
  private RestTemplate restTemplate;

  private final Logger logger = LoggerFactory.getLogger(ModelService.class);

  /**
   * Constructor for the ModelService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   * 
   * @param repo                The specified data point repository
   * @param restTemplateBuilder The specified rest template builder
   */
  @Autowired
  public ModelService(DataPointRepository repo, VesselRepository vesselRepo,
                      RestTemplateBuilder restTemplateBuilder) {
    this.repo = repo;
    this.vesselRepo = vesselRepo;
    this.restTemplate = restTemplateBuilder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .build();
  }

  /**
   * Fetches target values for vessel data by making the machine learning model make predictions on
   * the specified data point. If an error occurs when making predictions, the specified data point
   * is returned as it was passed, without target values.
   * 
   * @param dp The specified data point
   * @return Data point with target values on success or same data point on error
   * @see DataPoint
   */
  public DataPoint fetchTargetValues(DataPoint dp) {
    ResponseEntity<String> res = this.predict(dp);

    if (res.getStatusCode() == HttpStatus.OK) {
      String resBody = res.getBody();

      String[] values = resBody.split(":");

      // Target values as strings
      String targetSpeedString = values[0];
      String targetFuelConsumptionString = values[1].substring(1, values[1].length() - 1);
      // Target values
      float targetSpeed = Float.parseFloat(targetSpeedString);
      float targetFuelConsumption = Float.parseFloat(targetFuelConsumptionString);

      dp.getVessel().setTargetSpeed(targetSpeed);
      dp.getVessel().setTargetFuelConsumption(targetFuelConsumption);

      // Update vessel data in storage
      vesselRepo.save(dp.getVessel());
    } else {
      logger.error("Could not make prediction on data point");
    }

    return dp;
  }

  /**
   * Requests the machine learning model service to perform prediction on the specified data point.
   * The specified data point is passed along in the request as input. The response to the request
   * is returned, which contains the prediction output.
   * 
   * @param dp The specified data point
   * @return Response containing prediction output
   */
  public ResponseEntity<String> predict(DataPoint dp) {
    // Create collection containing single data point
    List<DataPoint> dps = new ArrayList<>();
    dps.add(dp);
    // Create request headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // Create full request
    HttpEntity<Iterable<DataPoint>> request = new HttpEntity<>(dps, headers);
    return restTemplate.exchange(
      "http://127.0.0.1:5000/predict",
      HttpMethod.POST,
      request,
      String.class
    );
  }

  /**
   * Requests the machine learning model service to train the model. All data points in storage are
   * passed along in the request, serving the model with training data. The response to the request
   * is returned which tells whether the model was successfully trained.
   * 
   * @return Response telling whether model was successfully trained
   */
  public ResponseEntity<String> train() {
    Iterable<DataPoint> dps = this.repo.findAll();
    // Create request headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // Create full request
    HttpEntity<Iterable<DataPoint>> request = new HttpEntity<>(dps, headers);
    // Send request and return request response
    return restTemplate.exchange(
      "http://127.0.0.1:5000/train",
      HttpMethod.POST,
      request,
      String.class
    );
  }
}
