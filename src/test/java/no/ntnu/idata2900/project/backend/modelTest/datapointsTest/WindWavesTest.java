package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.WindWaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WindWavesTest {

    @Test
    void testConstructorInitializesFields() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 5.0f);
        assertEquals(1.5f, windWaves.getWwavesHeight());
        assertEquals(90.0f, windWaves.getWwavesDirection());
        assertEquals(5.0f, windWaves.getWwavesPeriod());
        assertNull(windWaves.getMarineWeather(), "Marine weather should be null by default");
        assertNull(windWaves.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        WindWaves windWaves = new WindWaves();
        assertEquals(0.0f, windWaves.getWwavesHeight());
        assertEquals(0.0f, windWaves.getWwavesDirection());
        assertEquals(0.0f, windWaves.getWwavesPeriod());
        assertNull(windWaves.getMarineWeather());
        assertNull(windWaves.getId());
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
    void testIsGeneratedValidWithExcessiveHeight() {
        WindWaves windWaves = new WindWaves(3.0f, 90.0f, 5.0f);
        assertFalse(windWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessivePeriod() {
        WindWaves windWaves = new WindWaves(1.5f, 90.0f, 15.0f);
        assertFalse(windWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveDirection() {
        WindWaves windWaves = new WindWaves(1.5f, 400.0f, 5.0f);
        assertFalse(windWaves.isGeneratedValid());
    }
}