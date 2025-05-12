package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataPointTest {

    @Test
    void testValidDataPointCreation() {
        DataPoint dataPoint = new DataPoint(1627849200L);
        assertEquals(1627849200L, dataPoint.getTs());
    }

    @Test
    void testIsValidWithValidTimestamp() {
        DataPoint dataPoint = new DataPoint(1627849200L);
        assertTrue(dataPoint.isValid());
    }

    @Test
    void testIsValidWithNegativeTimestamp() {
        DataPoint dataPoint = new DataPoint(-1L);
        assertFalse(dataPoint.isValid());
    }

    @Test
    void testSetAndGetTrip() {
        DataPoint dataPoint = new DataPoint(1627849200L);
        assertNull(dataPoint.getTrip());
        dataPoint.setTrip(null);
        assertNull(dataPoint.getTrip());
    }

    @Test
    void testDefaultConstructor() {
        DataPoint dataPoint = new DataPoint();
        assertEquals(0L, dataPoint.getTs());
        assertNull(dataPoint.getTrip());
    }
}