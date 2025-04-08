package no.ntnu.idata2900.project.esg_module_backend.models;

public class BoatData extends Data {
    private int id;
    private String name;
    private double heading;
    private double course;
    private double speed;

    private double fishAmount;

    private double fuelLevel;
    private double totalDistance;

    public BoatData(int id, String name, float lat, float lng, double heading, double course,
                    double speed, long ts, double fishAmount, double fuelLevel, double totalDistance) {
        super(lat, lng, ts);
        this.id = id;
        this.name = name;
        this.heading = heading;
        this.course = course;
        this.speed = speed;
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

    public double getTotalDistance() {
        return totalDistance;
    }
}
