package no.ntnu.idata2900.project.esg_module_backend.models;

import java.time.Instant;
import java.util.ArrayList;

import no.ntnu.idata2900.project.esg_module_backend.dtos.BoatDataDto;

/**
 * The Trip class represents a fishing trip. The class will contain a list of BoatData objects.
 */
public class Trip {
    private Instant startDate;
    private Instant endDate;
    private float tripDistance;
    private float fishCaught;
    private float fuelConsumed;
    private ArrayList<BoatDataDto> boatData;

    public Trip() {
        this.boatData = new ArrayList<>();
    }

    public void start() {
        this.startDate = Instant.now();
    }

    public void end() {
        this.endDate = Instant.now();
        summarizeTrip();
    }

    private void summarizeTrip() {
        this.tripDistance = calculateTotalDistance();
        this.fishCaught += boatData.getLast().getFishAmount();
        this.fuelConsumed += boatData.getFirst().getFuelLevel() - boatData.getLast().getFuelLevel();
    }

    public void addBoatData(BoatDataDto boatData) {
        this.boatData.add(boatData);
    }

    private float calculateTotalDistance() {
        float distance = 0;
        for (int i = 0; i < boatData.size() - 1; i++) {
            distance += calculateDistance(boatData.get(i), boatData.get(i + 1));
        }
        return distance;
    }

    private float calculateDistance(BoatDataDto bd1, BoatDataDto bd2) {
        float lat1 = bd1.getLat();
        float lon1 = bd1.getLng();
        float lat2 = bd2.getLat();
        float lon2 = bd2.getLng();

        float theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(Double.parseDouble(Float.toString(lat1)))) * Math.sin(Math.toRadians(Double.parseDouble(Float.toString(lat2)))) + Math.cos(Math.toRadians(Double.parseDouble(Float.toString(lat1)))) * Math.cos(Math.toRadians(Double.parseDouble(Float.toString(lat2)))) * Math.cos(Math.toRadians(Double.parseDouble(Float.toString(theta))));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 0.8684;

        dist = Float.parseFloat(Double.toString(dist));

        return (float) dist;
    }
}
