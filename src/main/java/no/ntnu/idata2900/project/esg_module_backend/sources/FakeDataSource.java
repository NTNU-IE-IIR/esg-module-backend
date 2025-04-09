package no.ntnu.idata2900.project.esg_module_backend.sources;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FakeDataSource implements DataSource {
    private Timer timer;
    private DataListener listener;
    private int i = 0;

    private BoatData fakeBoatData;
    private ZonedDateTime timestamp;
    private Random random = new Random();

    @Override
    public void start() {
        i = 0;
        fakeBoatData = createInitialBoatData();
        timestamp = ZonedDateTime.parse("2025-02-13T10:15:30Z");
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (i < 50) {
                    if (listener != null) {
                        fakeBoatData = generateBoatData();
                        listener.onDataReceived(fakeBoatData);
                    }
                    i = (i + 1);
                } else {
                    stop();
                }
            }
        };

        timer.schedule(task, 0, 15000);
        System.out.println("FakeDataSource started");
    }

    private BoatData createInitialBoatData() {
        return new BoatData(1, "Boat1", 61.6031484, 5.0445668, 90.0, 85.0, 12.5,
                "2025-02-13T10:15:30Z", 0.0, 500.0, 0.0);
    }

    private BoatData generateBoatData() {
        double lat = fakeBoatData.getLat() + (random.nextDouble() * 0.01 - 0.005);
        double lng = fakeBoatData.getLng() + (random.nextDouble() * 0.01 - 0.005);
        timestamp = timestamp.plusMinutes(1);
        String timestampStr = timestamp.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        double fishAmount = fakeBoatData.getFishAmount() + (random.nextDouble() * 10);
        double fuelLevel = fakeBoatData.getFuelLevel() - (random.nextDouble() * 4);
        double totalDistance = fakeBoatData.getTotalDistance() + (random.nextDouble() * 10);

        return new BoatData(1, "Boat1", lat, lng, 90.0, 85.0, 12.5,
                timestampStr, fishAmount, fuelLevel, totalDistance);
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        i = 0;
        System.out.println("FakeDataSource stopped");
    }

    @Override
    public void setDataListener(DataListener listener) {
        this.listener = listener;
        System.out.println("FakeDataSource sending data");
    }
}