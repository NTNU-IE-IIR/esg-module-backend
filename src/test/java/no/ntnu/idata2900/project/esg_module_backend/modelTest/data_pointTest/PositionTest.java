package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testValidPositionCreation() {
        Position position = new Position(45.0f, 90.0f);
        assertEquals(45.0f, position.getLat());
        assertEquals(90.0f, position.getLng());
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
}