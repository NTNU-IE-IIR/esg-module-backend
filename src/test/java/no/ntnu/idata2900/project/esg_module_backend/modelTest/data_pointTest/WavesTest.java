package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WavesTest {

    @Test
    void testValidWavesCreation() {
        Waves waves = new Waves(2.0f, 45.0f, 10.0f);

        assertEquals(2.0f, waves.getWavesHeight());
        assertEquals(45.0f, waves.getWavesDirection());
        assertEquals(10.0f, waves.getWavesPeriod());
    }

    @Test
    void testIsValidWithValidData() {
        Waves waves = new Waves(2.0f, 45.0f, 10.0f);
        assertTrue(waves.isValid());
    }

    @Test
    void testIsValidWithNegativeWavesHeight() {
        Waves waves = new Waves(-2.0f, 45.0f, 10.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsValidWithNegativeWavesDirection() {
        Waves waves = new Waves(2.0f, -45.0f, 10.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsValidWithNegativeWavesPeriod() {
        Waves waves = new Waves(2.0f, 45.0f, -10.0f);
        assertFalse(waves.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        Waves waves = new Waves(2.0f, 45.0f, 10.0f);
        assertTrue(waves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveWavesHeight() {
        Waves waves = new Waves(20f, 45.0f, 10.0f);
        assertFalse(waves.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveWavesPeriod() {
        Waves waves = new Waves(2.0f, 45.0f, 20.0f);
        assertFalse(waves.isGeneratedValid());
    }
}
