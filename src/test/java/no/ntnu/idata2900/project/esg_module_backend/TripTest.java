package no.ntnu.idata2900.project.esg_module_backend;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.time.Duration;

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
    void testEndTripSetsEndDateAndActiveFalse() {
        Instant beforeEnd = Instant.now();
        trip.end();
        Instant afterEnd = Instant.now();

        assertNotNull(trip.getEndDate());

        assertFalse(trip.getEndDate().isBefore(beforeEnd));
        assertFalse(trip.getEndDate().isAfter(afterEnd));
        assertFalse(trip.isActive());
    }

    @Test
    void testTripDistanceFuelAndFishCaughtCanBeUpdated() {
        assertEquals(0.0f, trip.getTripDistance());
        assertEquals(0.0f, trip.getFuelConsumed());
        assertEquals(0.0f, trip.getFishCaught());
    }

    @Test
    void testStartDateIsRecent() {
        Instant now = Instant.now();
        Instant start = trip.getStartDate();
        Duration diff = Duration.between(start, now).abs();
        assertTrue(diff.toSeconds() < 5, "Start date is not recent");
    }

}
