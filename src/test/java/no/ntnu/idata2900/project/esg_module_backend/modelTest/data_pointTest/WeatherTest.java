package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void testValidWeatherCreation() {
        Weather weather = new Weather(10.0f, 5.0f, 15.0f);
        assertEquals(10.0f, weather.getWindU());
        assertEquals(5.0f, weather.getWindV());
        assertEquals(15.0f, weather.getGust());
    }

    @Test
    void testIsValidWithValidData() {
        Weather weather = new Weather(10.0f, 5.0f, 15.0f);
        assertTrue(weather.isValid());
    }

    @Test
    void testIsValidWithNegativeWindU() {
        Weather weather = new Weather(-10.0f, 5.0f, 15.0f);
        assertFalse(weather.isValid());
    }

    @Test
    void testIsValidWithNegativeWindV() {
        Weather weather = new Weather(10.0f, -5.0f, 15.0f);
        assertFalse(weather.isValid());
    }

    @Test
    void testIsValidWithNegativeGust() {
        Weather weather = new Weather(10.0f, 5.0f, -15.0f);
        assertFalse(weather.isValid());
    }

    @Test
    void testIsGeneratedValidWithValidData() {
        Weather weather = new Weather(10.0f, 5.0f, 15.0f);
        assertTrue(weather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveWindU() {
        Weather weather = new Weather(20f, 5.0f, 15.0f);
        assertFalse(weather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveWindV() {
        Weather weather = new Weather(10.0f, 20f, 15.0f);
        assertFalse(weather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveGust() {
        Weather weather = new Weather(10.0f, 5.0f, 25f);
        assertFalse(weather.isGeneratedValid());
    }
}
