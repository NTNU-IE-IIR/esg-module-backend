package no.ntnu.idata2900.project.esg_module_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
  private final TripDtoHandler tripDtoHandler;

  @Autowired
  public WebSocketConfig(TripDtoHandler tripDtoHandler) {
    this.tripDtoHandler = tripDtoHandler;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(tripDtoHandler, "/tripdto")
        .addInterceptors(new ClientIdInterceptor())
        .setAllowedOrigins("*");
  }
}
