package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TripLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double fish;
    private double fuel;
    private double nm;
    private String comments;
    private String area;
    private String startDate;
    private String endDate;
    public TripLog(double fish, double fuel, double nm, String comments, String area, String startDate, String endDate) {
        this.fish = fish;
        this.fuel = fuel;
        this.nm = nm;
        this.comments = comments;
        this.area = area;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TripLog() {
    }

    public int getId() {
        return id;
    }

    public double getFish() {
        return fish;
    }

    public double getFuel() {
        return fuel;
    }

    public double getNm() {
        return nm;
    }

    public String getComments() {
        return comments;
    }

    public String getArea() {
        return area;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
