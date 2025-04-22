package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a trip log entry containing details about a specific trip.
 * This information includes fish caught, fuel consumed, nautical miles traveled,
 * comments, area, and the start and end dates of the trip.
 * This is a persistence entity mapped to a database table.
 *
 * @author Group 14
 * @version v0.1.0 (2025.04.22)
 */
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

    /**
     * Constructor for the TripLog class.
     * @param fish The fish caught during the trip in kg.
     * @param fuel The fuel consumed during the trip in liters.
     * @param nm The nautical miles traveled during the trip.
     * @param comments The comments associated with the trip. Can be null.
     * @param area The area the trip was conducted in.
     * @param startDate The start date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     * @param endDate The end date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     */
    public TripLog(double fish, double fuel, double nm, String comments, String area, String startDate, String endDate) {
        this.fish = fish;
        this.fuel = fuel;
        this.nm = nm;
        this.comments = comments;
        this.area = area;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Default constructor for the TripLog class.
     */
    public TripLog() {
    }

    /**
     * Sets the comments associated with the trip.
     * @param comments The comments associated with the trip. Can be null.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Sets the area the trip was conducted in.
     * @param area The area the trip was conducted in. Can be null.
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Sets the start date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     * @param startDate The start date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     * @param endDate The end date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the unique identifier of the TripLog entry.
     *
     * @return the unique identifier of the TripLog as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the TripLog entry.
     *
     * @param id the unique identifier of the TripLog as an integer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the amount of fish caught during the trip.
     *
     * @return the amount of fish caught, measured in kilograms.
     */
    public double getFish() {
        return fish;
    }

    /**
     * Sets the amount of fish caught during the trip.
     *
     * @param fish the amount of fish caught, measured in kilograms.
     */
    public void setFish(double fish) {
        this.fish = fish;
    }

    /**
     * Retrieves the amount of fuel consumed during the trip.
     *
     * @return the amount of fuel consumed, measured in liters.
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * Sets the amount of fuel consumed during the trip.
     *
     * @param fuel the amount of fuel consumed, measured in liters.
     */
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    /**
     * Retrieves the distance traveled during the trip in nautical miles.
     *
     * @return the distance traveled, measured in nautical miles.
     */
    public double getNm() {
        return nm;
    }

    /**
     * Retrieves the comments associated with the trip.
     *
     **/
    public String getComments() {
        return comments;
    }

    /**
     * Retrieves the area where the trip was conducted.
     *
     */
    public String getArea() {
        return area;
    }

    /**
     * Retrieves the start date of the trip.
     *
     * @return the start date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the trip.
     *
     * @return the end date of the trip in the format "yyyy-MM-dd HH:mm:ss".
     */
    public String getEndDate() {
        return endDate;
    }
}
