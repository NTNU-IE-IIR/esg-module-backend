package no.ntnu.idata2900.project.esg_module_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idata2900.project.esg_module_backend.dtos.TripDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket handler for boat data communication. This class is responsible for
 * managing WebSocket connections with clients and sending boat data updates.
 * It extends TextWebSocketHandler to handle text-based WebSocket messages.
 *
 * @author Group 14
 * @version v0.0.1 (2025.04.22)
 */
@Component
public class BoatDataHandler extends TextWebSocketHandler {
  private final Logger logger = LoggerFactory.getLogger(BoatDataHandler.class);
  private final ObjectMapper objectMapper = new ObjectMapper();
  private WebSocketSession session;

  /**
   * Sends boat data to the connected WebSocket client.
   * The method converts the boat data to JSON and sends it as a text message.
   * If no session is established or the session is closed, the message is not sent.
   *
   * @param tripDto The boat data to be sent to the client
   */
  public void sendBoatData(TripDto tripDto) {
    try {
      if (session != null && session.isOpen()) {
        logger.debug("Sending tripDto to client: {}", tripDto);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(tripDto)));
      }
    } catch (Exception e) {
      logger.error("Failed to send boat data to client", e);
    }
  }

  /**
   * Called after a new WebSocket connection has been established.
   * This method stores the session for later use when sending boat data.
   *
   * @param session The WebSocket session that has been established
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    this.session = session;
    logger.debug("WebSocket connection established");
  }

  /**
   * Checks if there is an active WebSocket connection.
   *
   * @return true if a WebSocket session exists and is open, false otherwise
   */
  public boolean isConnected() {
    return session != null && session.isOpen();
  }
}
