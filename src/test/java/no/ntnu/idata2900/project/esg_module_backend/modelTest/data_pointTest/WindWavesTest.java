package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.WindWaves;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindWavesTest {

    @Test
    void testValidWindWavesCreation() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 5.0f);
        assertEquals(1.5f, windWaves.getWwavesHeight());
        assertEquals(90.0f, windWaves.getWwavesDirection());
        assertEquals(5.0f, windWaves.getWwavesPeriod());
    }

    @Test
    void testIsValidWithValidData() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 5.0f);
        assertTrue(windWaves.isValid());
    }

    @Test
    void testIsValidWithNegativeHeight() {
        WindWaves windWaves = new WindWaves(-1.5f, 90.0f, 5.0f);
        assertFalse(windWaves.isValid());
    }

    @Test
    void testIsValidWithNegativeDirection() {
        WindWaves windWaves = new WindWaves(1.5f, -90.0f, 5.0f);
        assertFalse(windWaves.isValid());
    }

    @Test
    void testIsValidWithDirectionOutOfRange() {
        WindWaves windWaves = new WindWaves(1.5f, 400.0f, 5.0f);
        assertFalse(windWaves.isValid());
    }

    @Test
    void testIsValidWithNegativePeriod() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, -5.0f);
        assertFalse(windWaves.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 5.0f);
        assertTrue(windWaves.isGeneratedValid());
    }

    @Test
    void testisGeneratedValidWithExcessiveHeight() {
        WindWaves windWaves = new WindWaves(100.0f, 90.0f, 5.0f);
        assertFalse(windWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessivePeriod() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 100.0f);
        assertFalse(windWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveDirection() {
        WindWaves windWaves = new WindWaves(1.5f, 400.0f, 5.0f);
        assertFalse(windWaves.isGeneratedValid());
    }
}
