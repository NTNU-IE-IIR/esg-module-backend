package no.ntnu.idata2900.project.esg_module_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
  private BoatDataHandler boatDataHandler;

  @Autowired
  public WebSocketConfig(BoatDataHandler boatDataHandler) {
    this.boatDataHandler = boatDataHandler;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(boatDataHandler, "/boatdata")
        .addInterceptors(new ClientIdInterceptor())
        .setAllowedOrigins("*");
  }
}
