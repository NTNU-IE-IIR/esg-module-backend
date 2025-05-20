package no.ntnu.idata2900.project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * The EsgModuleBackendApplication class represents the runner class for the application.
 *
 * @author Group 14
 * @version v0.0.1 (2025.02.13)
 */
@SpringBootApplication
public class EsgModuleBackendApplication {
  /**
   * The main entry point for the application. This method initializes and starts
   * the Spring Boot application, while also creating a PID file for process management.
   */
  public static void main(String[] args) {
    // Create PID file for application
    SpringApplication springApplication = new SpringApplication(EsgModuleBackendApplication.class);
    springApplication.addListeners(new ApplicationPidFileWriter("esgmodule.pid"));
    // Run application
    SpringApplication.run(EsgModuleBackendApplication.class, args);
  }
}
