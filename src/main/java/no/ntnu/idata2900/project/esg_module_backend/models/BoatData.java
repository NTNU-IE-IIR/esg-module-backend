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
    public BoatData() {
    }

    public BoatData(int id, String name, double lat, double lng, double heading, double course, double speed, String timestamp) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.heading = heading;
        this.course = course;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    public double getFishAmount() {
        return fishAmount;
    }

    public void setFishAmount(double fishAmount) {
        this.fishAmount = fishAmount;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
