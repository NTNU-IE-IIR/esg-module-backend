package no.ntnu.idata2900.project.backend;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * The RestTemplateResponseErrorHandler represents an error handler for rest template instances.
 * The class is specifically for allowing rest templates to receive error status codes
 * (4xx and 5xx) without throwing exceptions.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.12)
 * @see RestTemplate
 */
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return httpResponse.getStatusCode().is4xxClientError()
        || httpResponse.getStatusCode().is5xxServerError();
  }
}
