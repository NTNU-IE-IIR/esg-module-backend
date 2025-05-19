package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Fuel;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VesselTest {

    @Test
    void testConstructorInitializesFields() {
        Vessel vessel = new Vessel(90.0f, 10.0f);
        assertEquals(90.0f, vessel.getHeading());
        assertEquals(10.0f, vessel.getSpeed());
        assertEquals(0.0f, vessel.getTargetSpeed());
        assertNull(vessel.getFuelConsumption(), "Fuel consumption should be null by default");
        assertNull(vessel.getDp(), "Data point should be null by default");
        assertNull(vessel.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        Vessel vessel = new Vessel();
        assertEquals(0.0f, vessel.getHeading());
        assertEquals(0.0f, vessel.getSpeed());
        assertEquals(0.0f, vessel.getTargetSpeed());
        assertNull(vessel.getFuelConsumption());
        assertNull(vessel.getDp());
        assertNull(vessel.getId());
    }

    @Test
    void testSetAndGetTargetSpeed() {
        Vessel vessel = new Vessel();
        vessel.setTargetSpeed(12.0f);
        assertEquals(12.0f, vessel.getTargetSpeed());
    }

    @Test
    void testSetAndGetFuelConsumption() {
        Vessel vessel = new Vessel();
        Fuel fuel = new Fuel();
        vessel.setFuelConsumption(fuel);
        assertEquals(fuel, vessel.getFuelConsumption());
    }

    @Test
    void testSetAndGetDataPoint() {
        Vessel vessel = new Vessel();
        DataPoint dp = new DataPoint();
        vessel.setDp(dp);
        assertEquals(dp, vessel.getDp());
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