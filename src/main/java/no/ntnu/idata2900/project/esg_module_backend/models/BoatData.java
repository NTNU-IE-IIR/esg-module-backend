package no.ntnu.idata2900.project.esg_module_backend.models;

public class BoatData {
    private int id;
    private String name;
    private double lat;
    private double lng;
    private double heading;
    private double course;
    private double speed;
    private String timestamp;

    private double fishAmount;

    private double fuelLevel;
    private double totalDistance;

    public BoatData(int id, String name, double lat, double lng, double heading, double course,
                    double speed, String timestamp, double fishAmount, double fuelLevel, double totalDistance) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.heading = heading;
        this.course = course;
        this.speed = speed;
        this.timestamp = timestamp;
        this.fishAmount = fishAmount;
        this.fuelLevel = fuelLevel;
        this.totalDistance = totalDistance;
    }

    public double getFishAmount() {
        return fishAmount;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getId() {
        return id;
    }

    public double getHeading() {
        return heading;
    }

    public double getCourse() {
        return course;
    }

    public double getSpeed() {
        return speed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
