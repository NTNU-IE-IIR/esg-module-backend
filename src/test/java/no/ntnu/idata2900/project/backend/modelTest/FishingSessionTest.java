package no.ntnu.idata2900.project.backend.modelTest;

import no.ntnu.idata2900.project.backend.models.FishingSession;
import no.ntnu.idata2900.project.backend.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

class FishingSessionTest {

    private FishingSession fishingSession;

    @BeforeEach
    void setUp() {
        fishingSession = new FishingSession(1683897600L, 1683984000L, 100L);
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals(1683897600L, fishingSession.getStartDate());
        assertEquals(1683984000L, fishingSession.getEndDate());
        assertEquals(100L, fishingSession.getFuelConsumed());
        assertNull(fishingSession.getId(), "ID should be null before persistence");
        assertNull(fishingSession.getOperations(), "Operations should be null by default");
        assertNull(fishingSession.getTrip(), "Trip should be null by default");
    }

    @Test
    void testDefaultConstructor() {
        FishingSession defaultSession = new FishingSession();
        assertEquals(0L, defaultSession.getStartDate());
        assertEquals(0L, defaultSession.getEndDate());
        assertNull(defaultSession.getFuelConsumed());
        assertNull(defaultSession.getId());
        assertNull(defaultSession.getOperations());
        assertNull(defaultSession.getTrip());
    }

    @Test
    void testSetAndGetTrip() {
        Trip trip = new Trip("Test Trip", "AA-123-BB");
        fishingSession.setTrip(trip);
        assertEquals(trip, fishingSession.getTrip());
    }

    @Test
    void testGetOperations() {
        assertNull(fishingSession.getOperations(), "Operations should be null by default");
    }

    @Test
    void testSetTripToNull() {
        fishingSession.setTrip(null);
        assertNull(fishingSession.getTrip(), "Trip should be null after setting it to null");
    }
}