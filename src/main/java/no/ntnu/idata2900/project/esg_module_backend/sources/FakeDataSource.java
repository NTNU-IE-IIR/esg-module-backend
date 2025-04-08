package no.ntnu.idata2900.project.esg_module_backend.sources;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import no.ntnu.idata2900.project.esg_module_backend.dtos.BoatDataDto;
import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
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

    private BoatDataDto fakeBoatData;
    private long ts;
    private ZoneOffset offset;
    private Random random = new Random();

    @Override
    public void start() {
        ts = 1739438130;
        // Add time zone offset to timestamp
        offset = ZoneOffset.ofHours(1);

        fakeBoatData = createInitialBoatData();

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

    private BoatDataDto createInitialBoatData() {
        BoatData boatData = new BoatData(
            1, 
            "Boat1", 
            61.6031484f, 
            5.0445668f, 
            90.0, 
            85.0, 
            12.5,
            ts,
            100.0, 
            500.0, 
            0.0);

        return new BoatDataDto(
            boatData.getId(),
            boatData.getName(),
            boatData.getHeading(),
            boatData.getCourse(),
            boatData.getSpeed(),
            boatData.getPos().getLat(),
            boatData.getPos().getLng(),
            // UNIX timestamp in seconds
            ZonedDateTime
                .of(
                    LocalDateTime.ofEpochSecond(boatData.getTs(), 0, offset),
                    ZoneId.ofOffset("UTC", offset)
                )
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
            boatData.getFishAmount(),
            boatData.getTotalDistance()
        );
    }

    private BoatDataDto generateBoatData() {
        float lat = fakeBoatData.getLat() + (random.nextFloat() * 0.01f - 0.005f);
        float lng = fakeBoatData.getLng() + (random.nextFloat() * 0.01f - 0.005f);
        // Increment timestamp by 1 minute
        ts += 60;
        double fishAmount = fakeBoatData.getFishAmount() + (random.nextDouble() * 10);
        double fuelLevel = fakeBoatData.getFuelLevel() - (random.nextDouble() * 4);
        double totalDistance = fakeBoatData.getTotalDistance() + (random.nextDouble() * 10);

        BoatData boatData = new BoatData(
            1,
            "Boat1",
            lat,
            lng,
            90.0,
            85.0,
            12.5,
            ts,
            fishAmount,
            fuelLevel,
            totalDistance
        );

        return new BoatDataDto(
            boatData.getId(),
            boatData.getName(),
            boatData.getHeading(),
            boatData.getCourse(),
            boatData.getSpeed(),
            boatData.getPos().getLat(),
            boatData.getPos().getLng(),
            // UNIX timestamp in seconds
            ZonedDateTime
                .of(
                    LocalDateTime.ofEpochSecond(boatData.getTs(), 0, offset),
                    ZoneId.ofOffset("UTC", offset)
                )
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
            boatData.getFishAmount(),
            boatData.getTotalDistance());
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