package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.models.datapoints.SwellWaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SwellWavesTest {

    @Test
    void testConstructorInitializesFields() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertEquals(0.8f, swellWaves.getSwell1Height());
        assertEquals(90.0f, swellWaves.getSwell1Direction());
        assertEquals(10.0f, swellWaves.getSwell1Period());
        assertEquals(0.5f, swellWaves.getSwell2Height());
        assertEquals(180.0f, swellWaves.getSwell2Direction());
        assertEquals(12.0f, swellWaves.getSwell2Period());
        assertNull(swellWaves.getMarineWeather(), "Marine weather should be null by default");
        assertNull(swellWaves.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        SwellWaves swellWaves = new SwellWaves();
        assertEquals(0.0f, swellWaves.getSwell1Height());
        assertEquals(0.0f, swellWaves.getSwell1Direction());
        assertEquals(0.0f, swellWaves.getSwell1Period());
        assertEquals(0.0f, swellWaves.getSwell2Height());
        assertEquals(0.0f, swellWaves.getSwell2Direction());
        assertEquals(0.0f, swellWaves.getSwell2Period());
        assertNull(swellWaves.getMarineWeather());
        assertNull(swellWaves.getId());
    }

    @Test
    void testIsValidWithValidData() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertTrue(swellWaves.isValid());
    }

    @Test
    void testIsValidWithNegativeSwell1Height() {
        SwellWaves swellWaves = new SwellWaves(-0.5f, 90.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertFalse(swellWaves.isValid());
    }

    @Test
    void testIsValidWithInvalidSwell1Direction() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 400.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertFalse(swellWaves.isValid());
    }

    @Test
    void testIsValidWithNegativeSwell2Height() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, -0.5f, 180.0f, 12.0f);
        assertFalse(swellWaves.isValid());
    }

    @Test
    void testIsValidWithInvalidSwell2Direction() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 0.5f, 400.0f, 12.0f);
        assertFalse(swellWaves.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertTrue(swellWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveSwell1Height() {
        SwellWaves swellWaves = new SwellWaves(1.5f, 90.0f, 10.0f, 0.5f, 180.0f, 12.0f);
        assertFalse(swellWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveSwell1Period() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 15.0f, 0.5f, 180.0f, 12.0f);
        assertFalse(swellWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveSwell2Height() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 1.0f, 180.0f, 12.0f);
        assertFalse(swellWaves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveSwell2Period() {
        SwellWaves swellWaves = new SwellWaves(0.8f, 90.0f, 10.0f, 0.5f, 180.0f, 16.0f);
        assertFalse(swellWaves.isGeneratedValid());
    }

    @Test
    void testSetAndGetMarineWeather() {
        SwellWaves swellWaves = new SwellWaves();
        MarineWeather marineWeather = new MarineWeather();
        swellWaves.setMarineWeather(marineWeather);
        assertEquals(marineWeather, swellWaves.getMarineWeather());
    }
}