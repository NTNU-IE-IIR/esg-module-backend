package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.models.datapoints.Weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WeatherTest {

    @Test
    void testConstructorInitializesFields() {
        Weather weather = new Weather(10.0f, 5.0f, 15.0f);
        assertEquals(10.0f, weather.getWindU());
        assertEquals(5.0f, weather.getWindV());
        assertEquals(15.0f, weather.getGust());
        assertNull(weather.getDp(), "Data point should be null by default");
        assertNull(weather.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        Weather weather = new Weather();
        assertEquals(0.0f, weather.getWindU());
        assertEquals(0.0f, weather.getWindV());
        assertEquals(0.0f, weather.getGust());
        assertNull(weather.getDp());
        assertNull(weather.getId());
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
        Weather weather = new Weather(20.0f, 5.0f, 15.0f);
        assertFalse(weather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveWindV() {
        Weather weather = new Weather(10.0f, 20.0f, 15.0f);
        assertFalse(weather.isGeneratedValid());
    }

    @Test
    void testIsGeneratedValidWithExcessiveGust() {
        Weather weather = new Weather(10.0f, 5.0f, 25.0f);
        assertFalse(weather.isGeneratedValid());
    }

    @Test
    void testSetAndGetDataPoint() {
        Weather weather = new Weather();
        DataPoint dp = new DataPoint();
        weather.setDp(dp);
        assertEquals(dp, weather.getDp());
    }
}