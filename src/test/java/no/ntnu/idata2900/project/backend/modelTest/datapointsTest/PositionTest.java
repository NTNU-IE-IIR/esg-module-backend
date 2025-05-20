package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.Position;
import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void testConstructorInitializesFields() {
        Position position = new Position(45.0f, 90.0f);
        assertEquals(45.0f, position.getLat());
        assertEquals(90.0f, position.getLng());
        assertNull(position.getDp(), "Data point should be null by default");
        assertNull(position.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        Position position = new Position();
        assertEquals(0.0f, position.getLat());
        assertEquals(0.0f, position.getLng());
        assertNull(position.getDp());
        assertNull(position.getId());
    }

    @Test
    void testIsValidWithValidData() {
        Position position = new Position(45.0f, 90.0f);
        assertTrue(position.isValid());
    }

    @Test
    void testIsValidWithInvalidLatitudeTooHigh() {
        Position position = new Position(100.0f, 90.0f);
        assertFalse(position.isValid());
    }

    @Test
    void testIsValidWithInvalidLatitudeTooLow() {
        Position position = new Position(-100.0f, 90.0f);
        assertFalse(position.isValid());
    }

    @Test
    void testIsValidWithInvalidLongitudeTooHigh() {
        Position position = new Position(45.0f, 200.0f);
        assertFalse(position.isValid());
    }

    @Test
    void testIsValidWithInvalidLongitudeTooLow() {
        Position position = new Position(45.0f, -200.0f);
        assertFalse(position.isValid());
    }

    @Test
    void testIsValidWithBoundaryLatitudeAndLongitude() {
        Position position = new Position(90.0f, 180.0f);
        assertTrue(position.isValid());
    }

    @Test
    void testIsValidWithNegativeBoundaryLatitudeAndLongitude() {
        Position position = new Position(-90.0f, -180.0f);
        assertTrue(position.isValid());
    }

    @Test
    void testSetAndGetDataPoint() {
        Position position = new Position();
        DataPoint dp = new DataPoint();
        position.setDp(dp);
        assertEquals(dp, position.getDp());
    }
}