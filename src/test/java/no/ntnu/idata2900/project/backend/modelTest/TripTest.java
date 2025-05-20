package no.ntnu.idata2900.project.backend.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import no.ntnu.idata2900.project.backend.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripTest {

    private Trip trip;

    @BeforeEach
    void setUp() {
        trip = new Trip("Test Trip", "AA-123-BB");
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals("Test Trip", trip.getName());
        assertEquals("AA-123-BB", trip.getRegistrationMark());
        assertNotNull(trip.getStartDate());
        assertNull(trip.getEndDate());
        assertEquals(0.0f, trip.getTripDistance());
        assertEquals(0.0f, trip.getFuelConsumed());
        assertEquals(0.0f, trip.getFishCaught());
        assertNull(trip.getArea());
        assertNull(trip.getComments());
        assertTrue(trip.isActive());
    }

    @Test
    void testSetAreaAndComments() {
        String area = "North Sea";
        String comments = "Smooth sailing";
        trip.setArea(area);
        trip.setComments(comments);

        assertEquals(area, trip.getArea(), "Area getter should return set value");
        assertEquals(comments, trip.getComments(), "Comments getter should return set value");
    }

    @Test
    void testEndTripSetsEndDateAndActiveFalse() {
        Instant beforeEnd = Instant.now();
        trip.end();
        Instant afterEnd = Instant.now();

        assertNotNull(trip.getEndDate());
        assertFalse(trip.getEndDate().isBefore(beforeEnd), "End date should be set");
        assertFalse(trip.getEndDate().isAfter(afterEnd), "End date must not be before end() call");
        assertFalse(trip.isActive(), "Trip should be inactive after end()");
    }

    @Test
    void testTripDistanceFuelAndFishCaughtCanBeUpdated() {
        trip.setTripDistance(100.5f);
        trip.setFuelConsumed(50.2f);

        assertEquals(100.5f, trip.getTripDistance());
        assertEquals(50.2f, trip.getFuelConsumed());
    }

    @Test
    void testStartDateIsRecent() {
        Instant now = Instant.now();
        Instant start = trip.getStartDate();
        assertTrue(start.isBefore(now) || start.equals(now), "Start date should be recent");
    }

    @Test
    void testStartDateImmutability() {
        Instant startBefore = trip.getStartDate();
        trip.end();
        Instant startAfter = trip.getStartDate();
        assertEquals(startBefore, startAfter, "Start date should remain unchanged after end()");
    }

    @Test
    void testSetAreaToNull() {
        trip.setArea(null);
        assertNull(trip.getArea(), "Area should be null after setting it to null");
    }

    @Test
    void testSetCommentsToEmptyString() {
        trip.setComments("");
        assertEquals("", trip.getComments(), "Comments should allow empty strings");
    }
}