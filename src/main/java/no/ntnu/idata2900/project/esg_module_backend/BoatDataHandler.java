package no.ntnu.idata2900.project.esg_module_backend;

import no.ntnu.idata2900.project.esg_module_backend.data.DummyBoatData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Timer;
import java.util.TimerTask;
import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class BoatDataHandler extends TextWebSocketHandler {
    private int i = 0;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private void sendBoatData(WebSocketSession session, BoatData boatData) throws Exception {
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(boatData)));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        startTimer(session);
    }

    public void startTimer(WebSocketSession session) {
        DummyBoatData dummyBoatData = new DummyBoatData();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    sendBoatData(session, dummyBoatData.getDummyBoatData(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
                if (i >= 5) {
                    i = 0;
                }
            }
        };

        timer.schedule(task, 0, 15000);
    }
}
