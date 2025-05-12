package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarineWeatherTest {

    @Test
    void testValidMarineWeatherCreation() {
        MarineWeather marineWeather = new MarineWeather(0.2f, 90.0f);
        assertEquals(0.2f, marineWeather.getOceanCurrentVelocity());
        assertEquals(90.0f, marineWeather.getOceanCurrentDirection());
    }

    @Test
    void testIsValidWithValidData() {
        MarineWeather marineWeather = new MarineWeather(0.2f, 90.0f);
        assertTrue(marineWeather.isValid());
    }

    @Test
    void testIsValidWithNegativeOceanCurrentVelocity() {
        MarineWeather marineWeather = new MarineWeather(-0.1f, 90.0f);
        assertFalse(marineWeather.isValid());
    }

    @Test
    void testIsValidWithInvalidOceanCurrentDirectionTooHigh() {
        MarineWeather marineWeather = new MarineWeather(0.2f, 400.0f);
        assertFalse(marineWeather.isValid());
    }

    @Test
    void testIsValidWithInvalidOceanCurrentDirectionTooLow() {
        MarineWeather marineWeather = new MarineWeather(0.2f, -10.0f);
        assertFalse(marineWeather.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        MarineWeather marineWeather = new MarineWeather(0.2f, 90.0f);
        assertTrue(marineWeather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveOceanCurrentVelocity() {
        MarineWeather marineWeather = new MarineWeather(0.3f, 90.0f);
        assertFalse(marineWeather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithBoundaryOceanCurrentVelocity() {
        MarineWeather marineWeather = new MarineWeather(0.25f, 90.0f);
        assertTrue(marineWeather.isGeneratedValid());
    }
}