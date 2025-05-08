package no.ntnu.idata2900.project.esg_module_backend.modelTest;

import no.ntnu.idata2900.project.esg_module_backend.models.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void testValidConfigurationCreation() {
        Configuration config = new Configuration("REG123", "VesselName", true);
        assertEquals("REG123", config.getRegistrationMark());
        assertEquals("VesselName", config.getName());
        assertTrue(config.isLevel1());
    }

    @Test
    void testSetName() {
        Configuration config = new Configuration();
        config.setName("NewVesselName");
        assertEquals("NewVesselName", config.getName());
    }

    @Test
    void testSetLevel1() {
        Configuration config = new Configuration();
        config.setLevel1(true);
        assertTrue(config.isLevel1());
    }

    @Test
    void testIsValidWithValidData() {
        Configuration config = new Configuration("REG123", "VesselName", true);
        assertTrue(config.isValid());
    }

    @Test
    void testIsValidWithInvalidRegistrationMark() {
        Configuration config = new Configuration(null, "VesselName", true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithInvalidName() {
        Configuration config = new Configuration("REG123", null, true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithBlankRegistrationMark() {
        Configuration config = new Configuration("   ", "VesselName", true);
        assertFalse(config.isValid());
    }

    @Test
    void testIsValidWithBlankName() {
        Configuration config = new Configuration("REG123", "   ", true);
        assertFalse(config.isValid());
    }
}