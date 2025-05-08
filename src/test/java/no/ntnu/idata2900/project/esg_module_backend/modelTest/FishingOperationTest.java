package no.ntnu.idata2900.project.esg_module_backend.modelTest;
import no.ntnu.idata2900.project.esg_module_backend.models.FishingOperation;
import no.ntnu.idata2900.project.esg_module_backend.models.FishingSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FishingOperationTest {

    @Test
    void testValidFishingOperationCreation() {
        FishingOperation operation = new FishingOperation("2025-05-07 08:00:00", "2025-05-08 16:00:00", 100L, 50L, "Trawling");
        assertEquals("2025-05-07 08:00:00", operation.getStartDate());
        assertEquals("2025-05-08 16:00:00", operation.getEndDate());
        assertEquals(100L, operation.getFuelConsumed());
        assertEquals(50L, operation.getFishAmount());
        assertEquals("Trawling", operation.getFishingMethod());
    }

    @Test
    void testSetFishingSession() {
        FishingOperation operation = new FishingOperation();
        FishingSession session = new FishingSession("2025-05-08 16:00:00", "2025-05-09 16:00:00", 500L);
        operation.setFishingSession(session);
        assertEquals(session, operation.getFishingSession());
    }

    @Test
    void testNullStartDate() {
        Exception exception = assertThrows(NullPointerException.class, () -> new FishingOperation(null, "2025-05-08 16:00:00", 100L, 50L, "Trawling"));
        assertEquals("Start date cannot be null", exception.getMessage());
    }

}
