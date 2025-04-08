package no.ntnu.idata2900.project.esg_module_backend.models;

public class BoatData extends Data {
    private int id;
    private String name;
    private float heading;
    private float course;
    private float speed;

    private float fishAmount;

    private float fuelLevel;
    private float totalDistance;

    public BoatData(int id, String name, float lat, float lng, float heading, float course,
                    float speed, long ts, float fishAmount, float fuelLevel, float totalDistance) {
        super(new Position(lat, lng), ts);
        this.id = id;
        this.name = name;
        this.heading = heading;
        this.course = course;
        this.speed = speed;
        this.fishAmount = fishAmount;
        this.fuelLevel = fuelLevel;
        this.totalDistance = totalDistance;
    }

    public float getFishAmount() {
        return fishAmount;
    }

    public float getFuelLevel() {
        return fuelLevel;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getHeading() {
        return heading;
    }

    public float getCourse() {
        return course;
    }

    public float getSpeed() {
        return speed;
    }

    public float getTotalDistance() {
        return totalDistance;
    }
}
