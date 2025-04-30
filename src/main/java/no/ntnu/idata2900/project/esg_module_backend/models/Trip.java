package no.ntnu.idata2900.project.esg_module_backend.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;

// TODO Refactor class to contain a list of data points instead of ship DTOs

/**
 * The Trip class represents a fishing trip. The class contain a list of
 * {@link ShipDto ship data}.
 *
 * @author Group 14
 * @version v0.1.1 (2025.04.24)
 */
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private float tripDistance;
    private float fuelConsumed;
    private float fishCaught;
    @OneToMany(mappedBy = "trip")
    private Set<FishingSession> fishingSessions;
    @OneToMany(mappedBy = "trip")
    private final List<DataPoint> shipData;

    /**
     * Constructor for the Trip class.
     */
    public Trip() {
        this.shipData = new ArrayList<>();
    }

    //TODO: potentially remove trip logs

    /**
     * Returns a trip log derived from the fishing trip.
     *
     * @param comments The specified comments
     * @param area     The specified area
     * @return A trip log of the fishing trip
     * @see TripLog
     */
    public TripLog toTripLog(String comments, String area) {
        return new TripLog(fishCaught, fuelConsumed, tripDistance, comments, area, startDate.toString(),
                endDate.toString());
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
}
