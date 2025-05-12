package no.ntnu.idata2900.project.esg_module_backend.modelTest;

import no.ntnu.idata2900.project.esg_module_backend.models.FishingOperation;
import no.ntnu.idata2900.project.esg_module_backend.models.FishingSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FishingOperationTest {

    private FishingOperation fishingOperation;

    @BeforeEach
    void setUp() {
        fishingOperation = new FishingOperation(1683897600L, 1683984000L, 50L, 100L, "Trawling");
    }

    @Test
    void testConstructorInitializesFields() {
        assertEquals(1683897600L, fishingOperation.getStartDate());
        assertEquals(1683984000L, fishingOperation.getEndDate());
        assertEquals(50L, fishingOperation.getFuelConsumed());
        assertEquals(100L, fishingOperation.getFishAmount());
        assertEquals("Trawling", fishingOperation.getFishingMethod());
        assertNull(fishingOperation.getFishingSession(), "Fishing session should be null by default");
        assertNull(fishingOperation.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        FishingOperation defaultOperation = new FishingOperation();
        assertEquals(0L, defaultOperation.getStartDate());
        assertEquals(0L, defaultOperation.getEndDate());
        assertNull(defaultOperation.getFuelConsumed());
        assertNull(defaultOperation.getFishAmount());
        assertNull(defaultOperation.getFishingMethod());
        assertNull(defaultOperation.getFishingSession());
        assertNull(defaultOperation.getId());
    }

    @Test
    void testSetAndGetFishingSession() {
        FishingSession session = new FishingSession(1683984000L, 1684070400L, 200L);
        fishingOperation.setFishingSession(session);
        assertEquals(session, fishingOperation.getFishingSession());
    }

    @Test
    void testSetFishingSessionToNull() {
        fishingOperation.setFishingSession(null);
        assertNull(fishingOperation.getFishingSession(), "Fishing session should be null after setting it to null");
    }
}