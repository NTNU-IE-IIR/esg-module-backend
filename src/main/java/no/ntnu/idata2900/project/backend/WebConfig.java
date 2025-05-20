package no.ntnu.idata2900.project.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The WebConfig class represents the web configuration for the Spring Boot application. Here, the
 * permitted communcation origions for HTTP requests are stated.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Apply to all endpoints
        .allowedOrigins("*") // Allow all origins
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // HTTP methods to allow
        .allowedHeaders("*") // Allow all headers
        .allowCredentials(false); // Set to true if you need cookies/auth headers sent
  }
}
