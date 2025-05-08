package no.ntnu.idata2900.project.esg_module_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class ClientIdInterceptor implements HandshakeInterceptor {

  private final Logger logger = LoggerFactory.getLogger(ClientIdInterceptor.class);

  /**
   * Before the WebSocket handshake occurs, extract the clientId from the request parameters
   * and store it in the session attributes.
   *
   * @param request  The HTTP request object
   * @param response The HTTP response object
   * @param wsHandler The WebSocket handler
   * @param attributes A map of attributes to be stored in the WebSocketSession
   * @return true if the handshake is allowed, false otherwise
   */
  @Override
  public boolean beforeHandshake(ServerHttpRequest request,
                                 ServerHttpResponse response,
                                 WebSocketHandler wsHandler,
                                 Map<String, Object> attributes) {
    // Extract the clientId from query parameters
    String regMark = request.getURI().getQuery();

    logger.debug("Received WebSocket handshake request for clientId: {}", regMark);

    if (regMark == null || regMark.isBlank()) {
      logger.warn("WebSocket handshake rejected: clientId is missing");
      return false; // Reject the handshake if no clientId is provided
    }

    // Add the clientId to the WebSocket session attributes
    attributes.put("clientId", regMark);
    logger.debug("WebSocket handshake accepted for clientId: {}", regMark);
    return true; // Proceed with the handshake
  }

  @Override
  public void afterHandshake(ServerHttpRequest request,
                             ServerHttpResponse response,
                             WebSocketHandler wsHandler,
                             Exception exception) {
    // No action needed after the handshake
  }
}