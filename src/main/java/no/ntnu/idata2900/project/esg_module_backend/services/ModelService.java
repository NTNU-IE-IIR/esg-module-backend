package no.ntnu.idata2900.project.esg_module_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.repositories.data_points.DataPointRepository;

/**
 * The ModelService class represents the service for communicating with the machine learning model
 * service.
 * 
 * @author Group 14
 * @version v0.1.0 (2025.05.06)
 */
@Service
public class ModelService {
  private DataPointRepository repo;
  private RestTemplate restTemplate;

  /**
   * Constructor for the ModelService class. The constructor is annotated with
   * <code>@Autowired</code>, meaning that Spring Boot will automatically inject the specified
   * instanes to instances of the class.
   * 
   * @param repo                The specified data point repository
   * @param restTemplateBuilder The specified rest template builder
   */
  @Autowired
  public ModelService(DataPointRepository repo, RestTemplateBuilder restTemplateBuilder) {
    this.repo = repo;
    this.restTemplate = restTemplateBuilder.build();
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
