package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WavesTest {

    @Test
    void testConstructorInitializesFields() {
        Waves waves = new Waves(2.5f, 90.0f, 12.0f);
        assertEquals(2.5f, waves.getWavesHeight());
        assertEquals(90.0f, waves.getWavesDirection());
        assertEquals(12.0f, waves.getWavesPeriod());
        assertNull(waves.getMarineWeather(), "Marine weather should be null by default");
        assertNull(waves.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        Waves waves = new Waves();
        assertEquals(0.0f, waves.getWavesHeight());
        assertEquals(0.0f, waves.getWavesDirection());
        assertEquals(0.0f, waves.getWavesPeriod());
        assertNull(waves.getMarineWeather());
        assertNull(waves.getId());
    }

    @Test
    void testIsValidWithValidData() {
        Waves waves = new Waves(2.5f, 90.0f, 12.0f);
        assertTrue(waves.isValid());
    }

    @Test
    void testIsValidWithNegativeHeight() {
        Waves waves = new Waves(-2.5f, 90.0f, 12.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsValidWithNegativeDirection() {
        Waves waves = new Waves(2.5f, -90.0f, 12.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsValidWithDirectionOutOfRange() {
        Waves waves = new Waves(2.5f, 400.0f, 12.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsValidWithNegativePeriod() {
        Waves waves = new Waves(2.5f, 90.0f, -12.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        Waves waves = new Waves(2.5f, 90.0f, 12.0f);
        assertTrue(waves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveHeight() {
        Waves waves = new Waves(7.0f, 90.0f, 12.0f);
        assertFalse(waves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessivePeriod() {
        Waves waves = new Waves(2.5f, 90.0f, 20.0f);
        assertFalse(waves.isGeneratedValid());
    }

    @Test
    void testSetAndGetMarineWeather() {
        Waves waves = new Waves();
        MarineWeather marineWeather = new MarineWeather();
        waves.setMarineWeather(marineWeather);
        assertEquals(marineWeather, waves.getMarineWeather());
    }
}