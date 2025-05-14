package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Waves;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.WindWaves;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.SwellWaves;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarineWeatherTest {

    @Test
    void testConstructorInitializesFields() {
        MarineWeather marineWeather = new MarineWeather(0.2f, 90.0f);
        assertEquals(0.2f, marineWeather.getOceanCurrentVelocity());
        assertEquals(90.0f, marineWeather.getOceanCurrentDirection());
        assertNull(marineWeather.getWaves(), "Waves should be null by default");
        assertNull(marineWeather.getWwaves(), "Wind waves should be null by default");
        assertNull(marineWeather.getSwellWaves(), "Swell waves should be null by default");
        assertNull(marineWeather.getDp(), "Data point should be null by default");
        assertNull(marineWeather.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        MarineWeather marineWeather = new MarineWeather();
        assertEquals(0.0f, marineWeather.getOceanCurrentVelocity());
        assertEquals(0.0f, marineWeather.getOceanCurrentDirection());
        assertNull(marineWeather.getWaves());
        assertNull(marineWeather.getWwaves());
        assertNull(marineWeather.getSwellWaves());
        assertNull(marineWeather.getDp());
        assertNull(marineWeather.getId());
    }

    @Test
    void testSetAndGetWaves() {
        MarineWeather marineWeather = new MarineWeather();
        Waves waves = new Waves();
        marineWeather.setWaves(waves);
        assertEquals(waves, marineWeather.getWaves());
    }

    @Test
    void testSetAndGetWindWaves() {
        MarineWeather marineWeather = new MarineWeather();
        WindWaves windWaves = new WindWaves();
        marineWeather.setWwaves(windWaves);
        assertEquals(windWaves, marineWeather.getWwaves());
    }

    @Test
    void testSetAndGetSwellWaves() {
        MarineWeather marineWeather = new MarineWeather();
        SwellWaves swellWaves = new SwellWaves();
        marineWeather.setSwellWaves(swellWaves);
        assertEquals(swellWaves, marineWeather.getSwellWaves());
    }

    @Test
    void testSetAndGetDataPoint() {
        MarineWeather marineWeather = new MarineWeather();
        DataPoint dp = new DataPoint();
        marineWeather.setDp(dp);
        assertEquals(dp, marineWeather.getDp());
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
}