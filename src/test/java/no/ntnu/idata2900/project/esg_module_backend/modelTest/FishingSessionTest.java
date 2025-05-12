package no.ntnu.idata2900.project.esg_module_backend.modelTest;

import no.ntnu.idata2900.project.esg_module_backend.models.FishingSession;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FishingSessionTest {
    private FishingSession fishingSession;

    @BeforeEach
    void setUp() {
        fishingSession = new FishingSession("2025-05-07 06:00:00", "2025-05-07 12:00:00", 100L);
    }

    @Test
    void testParameterizedConstructorAndGetters() {
        assertEquals("2025-05-07 06:00:00", fishingSession.getStartDate());
        assertEquals("2025-05-07 12:00:00", fishingSession.getEndDate());
        assertEquals(100L, fishingSession.getFuelConsumed());
        assertNull(fishingSession.getId(), "ID should be null before persistence");
        assertNull(fishingSession.getOperations(), "Operations should be null by default");
        assertNull(fishingSession.getTrip(), "Trip should be null by default");
    }

    @Test
    void testDefaultConstructor() {
        FishingSession defaultSession = new FishingSession();
        assertNull(defaultSession.getStartDate());
        assertNull(defaultSession.getEndDate());
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
    void testSetAndGetOperations() {
        assertNull(fishingSession.getOperations(), "Operations should be null by default");
    }
}