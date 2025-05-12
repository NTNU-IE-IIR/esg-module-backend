package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Fuel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelTest {

    @Test
    void testValidFuelCreation() {
        Fuel fuel = new Fuel(10.0f, 5.0f, 3.0f);
        assertEquals(10.0f, fuel.getDrift());
        assertEquals(5.0f, fuel.getProduction());
        assertEquals(3.0f, fuel.getHotel());
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