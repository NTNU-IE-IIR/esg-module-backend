package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselTest {

    @Test
    void testValidVesselCreation() {
        Vessel vessel = new Vessel(90.0f, 10.0f);
        assertEquals(90.0f, vessel.getHeading());
        assertEquals(10.0f, vessel.getSpeed());
        assertEquals(0.0f, vessel.getTargetSpeed());
    }

    @Test
    void testSetTargetSpeed() {
        Vessel vessel = new Vessel(90.0f, 10.0f);
        vessel.setTargetSpeed(12.0f);
        assertEquals(12.0f, vessel.getTargetSpeed());
    }

    @Test
    void testIsValidWithValidData() {
        Vessel vessel = new Vessel(180.0f, 5.0f);
        assertTrue(vessel.isValid());
    }

    @Test
    void testIsValidWithInvalidHeading() {
        Vessel vessel = new Vessel(-10.0f, 5.0f);
        assertFalse(vessel.isValid());
    }

    @Test
    void testIsValidWithHeadingOutOfRange() {
        Vessel vessel = new Vessel(400.0f, 5.0f);
        assertFalse(vessel.isValid());
    }

    @Test
    void testIsValidWithNegativeSpeed() {
        Vessel vessel = new Vessel(90.0f, -5.0f);
        assertFalse(vessel.isValid());
    }

    @Test
    void testIsValidWithExcessiveSpeed() {
        Vessel vessel = new Vessel(90.0f, 20.0f);
        assertFalse(vessel.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        Vessel vessel = new Vessel(90.0f, 10.0f);
        assertTrue(vessel.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveSpeed() {
        Vessel vessel = new Vessel(90.0f, 16.0f);
        assertFalse(vessel.isGeneratedValid());
    }
}