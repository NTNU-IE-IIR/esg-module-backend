package no.ntnu.idata2900.project.backend.modelTest;

import no.ntnu.idata2900.project.backend.models.Configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConfigurationTest {

    @Test
    void testConstructorInitializesFields() {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);
        assertEquals("REG123", config.getRegistrationMark());
        assertEquals("VesselName", config.getName());
        assertEquals("Diesel", config.getFuelType());
        assertTrue(config.isLevel1());
    }

    @Test
    void testDefaultConstructor() {
        Configuration config = new Configuration();
        assertNull(config.getRegistrationMark());
        assertNull(config.getName());
        assertNull(config.getFuelType());
        assertFalse(config.isLevel1());
    }

    @Test
    void testSetName() {
        Configuration config = new Configuration();
        config.setName("NewVesselName");
        assertEquals("NewVesselName", config.getName());
    }

    @Test
    void testSetFuelType() {
        Configuration config = new Configuration();
        config.setFuelType("Electric");
        assertEquals("Electric", config.getFuelType());
    }

    @Test
    void testSetLevel1() {
        Configuration config = new Configuration();
        config.setLevel1(true);
        assertTrue(config.isLevel1());
    }

    @Test
    void testIsValidWithValidData() {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);
        assertTrue(config.isValid());
    }

    @Test
    void testIsValidWithInvalidRegistrationMark() {
        Configuration config = new Configuration(null, "VesselName", "Diesel", true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithInvalidName() {
        Configuration config = new Configuration("REG123", null, "Diesel", true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithBlankRegistrationMark() {
        Configuration config = new Configuration("   ", "VesselName", "Diesel", true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithBlankName() {
        Configuration config = new Configuration("REG123", "   ", "Diesel", true);
        assertFalse(config.isValid());
    }
}