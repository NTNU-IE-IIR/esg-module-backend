package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.Fuel;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FuelTest {

    @Test
    void testConstructorInitializesFields() {
        Fuel fuel = new Fuel(10.0f, 5.0f, 3.0f);
        assertEquals(10.0f, fuel.getDrift());
        assertEquals(5.0f, fuel.getProduction());
        assertEquals(3.0f, fuel.getHotel());
        assertNull(fuel.getVessel(), "Vessel should be null by default");
    }

    @Test
    void testDefaultConstructor() {
        Fuel fuel = new Fuel();
        assertEquals(0.0f, fuel.getDrift());
        assertEquals(0.0f, fuel.getProduction());
        assertEquals(0.0f, fuel.getHotel());
        assertNull(fuel.getVessel());
    }

    @Test
    void testSetAndGetVessel() {
        Fuel fuel = new Fuel();
        Vessel vessel = new Vessel();
        fuel.setVessel(vessel);
        assertEquals(vessel, fuel.getVessel());
    }

    @Test
    void testGetTotalFuelConsumption() {
        Fuel fuel = new Fuel(10.0f, 5.0f, 3.0f);
        assertEquals(18.0f, fuel.getTotal());
    }

    @Test
    void testIsValidWithValidData() {
        Fuel fuel = new Fuel(10.0f, 5.0f, 3.0f);
        assertTrue(fuel.isValid());
    }

    @Test
    void testIsValidWithNegativeDrift() {
        Fuel fuel = new Fuel(-1.0f, 5.0f, 3.0f);
        assertFalse(fuel.isValid());
    }

    @Test
    void testIsValidWithNegativeProduction() {
        Fuel fuel = new Fuel(10.0f, -5.0f, 3.0f);
        assertFalse(fuel.isValid());
    }

    @Test
    void testIsValidWithNegativeHotel() {
        Fuel fuel = new Fuel(10.0f, 5.0f, -3.0f);
        assertFalse(fuel.isValid());
    }

    @Test
    void testIsValidWithAllZeroValues() {
        Fuel fuel = new Fuel(0.0f, 0.0f, 0.0f);
        assertTrue(fuel.isValid());
    }
}