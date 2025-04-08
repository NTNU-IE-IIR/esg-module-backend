package no.ntnu.idata2900.project.esg_module_backend.sources;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import no.ntnu.idata2900.project.esg_module_backend.models.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.Ship;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FakeDataSource implements DataSource {
    private Timer timer;
    private DataListener listener;
    private int i = 0;

    private ShipDto fakeBoatData;
    private long ts;
    private ZoneOffset offset;
    private Random random = new Random();

    @Override
    public void start() {
        ts = 1739438130;
        // Add time zone offset to timestamp
        offset = ZoneOffset.ofHours(1);

        fakeBoatData = createInitialShipData();

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

    private ShipDto createInitialShipData() {
        DataPoint dp = new DataPoint(new Position(61.6031484f, 5.0445668f), ts);

        dp.setShip(new Ship(1, "Ship1", 90.0f, 85.0f, 12.5f, 500.0f, 100.0f, 0.0f));

        return new ShipDto(
            dp.getShip().getId(),
            dp.getShip().getName(),
            dp.getShip().getHeading(),
            dp.getShip().getCourse(),
            dp.getShip().getSpeed(),
            dp.getShip().getFishAmount(),
            dp.getShip().getTotalDistance(),
            dp.getPos().getLat(),
            dp.getPos().getLng(),
            // UNIX timestamp in seconds
            ZonedDateTime
                .of(
                    LocalDateTime.ofEpochSecond(dp.getTs(), 0, offset),
                    ZoneId.ofOffset("UTC", offset)
                )
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        );
    }

    private ShipDto generateBoatData() {
        float lat = fakeBoatData.getLat() + (random.nextFloat() * 0.01f - 0.005f);
        float lng = fakeBoatData.getLng() + (random.nextFloat() * 0.01f - 0.005f);
        // Increment timestamp by 1 minute
        ts += 60;
        float fishAmount = fakeBoatData.getFishAmount() + (random.nextFloat() * 10);
        float fuelLevel = fakeBoatData.getFuelLevel() - (random.nextFloat() * 4);
        float totalDistance = fakeBoatData.getTotalDistance() + (random.nextFloat() * 10);

        DataPoint dp = new DataPoint(new Position(lat, lng), ts);

        dp.setShip(
            new Ship(1, "Ship1", 90.0f, 85.0f, 12.5f, fuelLevel, fishAmount, totalDistance)
        );

        return new ShipDto(
            dp.getShip().getId(),
            dp.getShip().getName(),
            dp.getShip().getHeading(),
            dp.getShip().getCourse(),
            dp.getShip().getSpeed(),
            dp.getShip().getFishAmount(),
            dp.getShip().getTotalDistance(),
            dp.getPos().getLat(),
            dp.getPos().getLng(),
            // UNIX timestamp in seconds
            ZonedDateTime
                .of(
                    LocalDateTime.ofEpochSecond(dp.getTs(), 0, offset),
                    ZoneId.ofOffset("UTC", offset)
                )
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
            );
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
