package no.ntnu.idata2900.project.esg_module_backend.modelTest;

import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripLogTest {

    private TripLog log;
    private static final double DELTA = 1e-6;

    @BeforeEach
    void setUp() {
        log = new TripLog(
                100.5,
                250.7,
                30.5,
                "Great Trip!",
                "North Sea",
                "2025-05-07 08:00:00",
                "2025-05-07 18:00:00"
        );
    }

    @Test
    void testParameterizedConstructorAssignFields() {
        assertEquals(100.5, log.getFish(), DELTA);
        assertEquals(250.7, log.getFuel(), DELTA);
        assertEquals(30.5, log.getNm(), DELTA);
        assertEquals("Great Trip!", log.getComments());
        assertEquals("North Sea", log.getArea());
        assertEquals("2025-05-07 08:00:00", log.getStartDate());
        assertEquals("2025-05-07 18:00:00", log.getEndDate());
        assertNull(log.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructorAndSetters() {
        TripLog emptyLog = new TripLog();

        assertNull(emptyLog.getId(), "Default ID should be null");
        assertEquals(0.0, emptyLog.getFish(), DELTA);
        assertEquals(0.0, emptyLog.getFuel(), DELTA);
        assertEquals(0.0, emptyLog.getNm(), DELTA);
        assertNull(emptyLog.getComments(), "Default comments should be null");
        assertNull(emptyLog.getArea(), "Default area should be null");
        assertNull(emptyLog.getStartDate(), "Default start date should be null");
        assertNull(emptyLog.getEndDate(), "Default end date should be null");

        emptyLog.setId(30L);
        emptyLog.setFish(50.0);
        emptyLog.setFuel(100.0);
        emptyLog.setComments("Test Comment");
        emptyLog.setArea("Test Area");
        emptyLog.setStartDate("2025-05-07 08:00:00");
        emptyLog.setEndDate("2025-05-07 18:00:00");

        assertEquals(30L, emptyLog.getId());
        assertEquals(50.0, emptyLog.getFish(), DELTA);
        assertEquals(100.0, emptyLog.getFuel(), DELTA);
        assertEquals("Test Comment", emptyLog.getComments());
        assertEquals("Test Area", emptyLog.getArea());
        assertEquals("2025-05-07 08:00:00", emptyLog.getStartDate());
        assertEquals("2025-05-07 18:00:00", emptyLog.getEndDate());
    }

    @Test
    void testNegativeValuesAllowedForFishAndFuel() {
        log.setFish(-10.0);
        log.setFuel(-5.5);

        assertEquals(-10.0, log.getFish(), DELTA);
        assertEquals(-5.5, log.getFuel(), DELTA);
        assertEquals(30.5, log.getNm(), DELTA, "nm is only set via constructor and remains unchanged");
    }

    @Test
    void testNullAndEmptyStringsForTextFields() {
        log.setComments("");
        log.setArea("");
        log.setStartDate("");
        log.setEndDate("");

        assertEquals("", log.getComments());
        assertEquals("", log.getArea());
        assertEquals("", log.getStartDate());
        assertEquals("", log.getEndDate());

        log.setComments(null);
        log.setArea(null);
        log.setStartDate(null);
        log.setEndDate(null);

        assertNull(log.getComments());
        assertNull(log.getArea());
        assertNull(log.getStartDate());
        assertNull(log.getEndDate());
    }

    @Test
    void testInvalidDateFormatsStoredAsIs() {
        String badStart = "not-a-date";
        String badEnd = "123";

        log.setStartDate(badStart);
        log.setEndDate(badEnd);

        assertEquals(badStart, log.getStartDate());
        assertEquals(badEnd, log.getEndDate());
    }

    @Test
    void testIdSetterAcceptsNull() {
        log.setId(100L);
        assertEquals(100L, log.getId());
        log.setId(null);
        assertNull(log.getId());
    }
}
