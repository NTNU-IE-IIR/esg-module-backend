package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * The Trip class represents a fishing trip. The class contain a list of
 * {@link DataPoint data points} and a list of {@link FishingSession fishing sessions}.
 *
 * @author Group 14
 * @version v0.1.1 (2025.04.24)
 */
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String registrationMark;
    private Instant startDate;
    private Instant endDate;
    private float tripDistance;
    private float fuelConsumed;
    private float fishCaught;
    private boolean active;
    private String area;
    private String comments;
    @OneToMany(mappedBy = "trip")
    private Set<FishingSession> fishingSessions;
    @OneToMany(mappedBy = "trip")
    private List<DataPoint> shipData;

    /**
     * Constructor for the Trip class.
     */
    public Trip() {
    }

    /**
     * Getter for ID.
     *
     * @return ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for start date.
     *
     * @return Start date
     */
    public Instant getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for end date.
     *
     * @return End date
     */
    public Instant getEndDate() {
        return this.endDate;
    }

    /**
     * Getter for total trip distance.
     *
     * @return Total trip distance
     */
    public float getTripDistance() {
        return this.tripDistance;
    }

    /**
     * Getter for total fuel consumed.
     *
     * @return Total fuel consumed
     */
    public float getFuelConsumed() {
        return this.fuelConsumed;
    }

    /**
     * Getter for total fish caught.
     *
     * @return Total fish caught
     */
    public float getFishCaught() {
        return this.fishCaught;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
