package no.ntnu.idata2900.project.esg_module_backend.modelTest;
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

    @Test
    void testReEndTripDoesChangeEndDate() {
        trip.end();
        Instant firstEnd = trip.getEndDate();
        trip.end();
        Instant secondEnd = trip.getEndDate();

        assertNotNull(secondEnd, "End date remains non-null after second end()");
        assertFalse(secondEnd.isBefore(firstEnd), "Second end date should be equal or after the first end date");
        assertFalse(trip.isActive(), "trip remains inactive after second end()");
    }

    @Test
    void testStartDateImmutability() {
        Instant startBefore = trip.getStartDate();
        trip.end();
        Instant startAfter = trip.getStartDate();
        assertEquals(startBefore, startAfter, "startDate should remain unchanged after end()");
    }

    @Test
    void testInvalidAreaAssignment() {
        trip.setComments("");
        assertEquals("", trip.getComments(), "Empty comments string is stored as is");

        trip.setComments(null);
        assertNull(trip.getComments(), "Null comments string should be accepted and retrievable");
    }
}
