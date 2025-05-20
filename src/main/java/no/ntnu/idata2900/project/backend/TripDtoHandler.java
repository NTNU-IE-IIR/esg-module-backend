package no.ntnu.idata2900.project.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import no.ntnu.idata2900.project.backend.dtos.TripDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket handler for boat data communication. This class is responsible for
 * managing WebSocket connections with clients and sending boat data updates.
 * It extends TextWebSocketHandler to handle text-based WebSocket messages.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.09)
 */
@Component
public class TripDtoHandler extends TextWebSocketHandler {
  private final Logger logger = LoggerFactory.getLogger(TripDtoHandler.class);
  private final ObjectMapper objectMapper = new ObjectMapper();

  private final Map<String, WebSocketSession> clientSessions = new ConcurrentHashMap<>();

  /**
   * Sends a tripDto to the connected WebSocket client.
   * The method converts the tripDto to JSON and sends it as a text message.
   * If no session is established or the session is closed, the message is not sent.
   *
   * @param tripDto The boat data to be sent to the client
   */
  public void sendTripDto(String regMark, TripDto tripDto) {
    WebSocketSession session = this.clientSessions.get(regMark);
    if (session == null) {
      logger.warn("No session found for regMark {}", regMark);
      return;
    }

    try {
      if (session.isOpen()) {
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(tripDto)));
        logger.debug("Sending tripDto to client: {}", tripDto);
      } else {
        logger.warn("Session for regMark {} is closed", regMark);
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
    String regMark = (String) session.getAttributes().get("clientId");
    if (regMark != null) {
      clientSessions.put(regMark, session);
      logger.debug("WebSocket connection established for regMark {}", regMark);
    } else {
      logger.warn("WebSocket connection established without clientId");
    }
  }

  /**
   * Called after a WebSocket connection has been closed.
   * Removes the session associated with the clientId.
   *
   * @param session The WebSocket session that has been closed
   * @param status  The status of the connection closure
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    try {
      String clientId = (String) session.getAttributes().get("clientId");
      if (clientId != null) {
        clientSessions.remove(clientId);
        logger.debug("WebSocket connection closed for clientId: {}", clientId);
      }
    } catch (Exception e) {
      logger.error("Failed to remove WebSocket session for ",  e);
    }
  }

  /**
   * Checks if a WebSocket session exists for a specific client ID and is open.
   *
   * @param clientId The unique ID associated with the client
   * @return true if the WebSocket session exists and is open, false otherwise
   */
  public boolean isClientConnected(String clientId) {
    WebSocketSession session = clientSessions.get(clientId);
    return session != null && session.isOpen();
  }
}
