package no.ntnu.idata2900.project.esg_module_backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class BoatDataHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private WebSocketSession session;

    public void sendBoatData(ShipDto boatData) {
        try {
            if (session != null && session.isOpen()) {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(boatData)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
    }

    public boolean isConnected() {
        return session != null && session.isOpen();
    }
}
