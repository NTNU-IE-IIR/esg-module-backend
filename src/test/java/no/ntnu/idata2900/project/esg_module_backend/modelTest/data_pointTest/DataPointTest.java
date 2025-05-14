package no.ntnu.idata2900.project.esg_module_backend.modelTest.data_pointTest;

import no.ntnu.idata2900.project.esg_module_backend.models.data_points.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Vessel;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Weather;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.MarineWeather;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataPointTest {

    @Test
    void testConstructorInitializesFields() {
        DataPoint dataPoint = new DataPoint(1627849200L);
        assertEquals(1627849200L, dataPoint.getTs());
        assertNull(dataPoint.getPos());
        assertNull(dataPoint.getVessel());
        assertNull(dataPoint.getWeather());
        assertNull(dataPoint.getMarineWeather());
        assertNull(dataPoint.getTrip());
        assertNull(dataPoint.getId(), "ID should be null before persistence");
    }

    @Test
    void testDefaultConstructor() {
        DataPoint dataPoint = new DataPoint();
        assertEquals(0L, dataPoint.getTs());
        assertNull(dataPoint.getPos());
        assertNull(dataPoint.getVessel());
        assertNull(dataPoint.getWeather());
        assertNull(dataPoint.getMarineWeather());
        assertNull(dataPoint.getTrip());
        assertNull(dataPoint.getId());
    }

    @Test
    void testSetAndGetPosition() {
        DataPoint dataPoint = new DataPoint();
        Position position = new Position();
        dataPoint.setPos(position);
        assertEquals(position, dataPoint.getPos());
    }

    @Test
    void testSetAndGetVessel() {
        DataPoint dataPoint = new DataPoint();
        Vessel vessel = new Vessel();
        dataPoint.setVessel(vessel);
        assertEquals(vessel, dataPoint.getVessel());
    }

    @Test
    void testSetAndGetWeather() {
        DataPoint dataPoint = new DataPoint();
        Weather weather = new Weather();
        dataPoint.setWeather(weather);
        assertEquals(weather, dataPoint.getWeather());
    }

    @Test
    void testSetAndGetMarineWeather() {
        DataPoint dataPoint = new DataPoint();
        MarineWeather marineWeather = new MarineWeather();
        dataPoint.setMarineWeather(marineWeather);
        assertEquals(marineWeather, dataPoint.getMarineWeather());
    }

    @Test
    void testSetAndGetTrip() {
        DataPoint dataPoint = new DataPoint();
        Trip trip = new Trip();
        dataPoint.setTrip(trip);
        assertEquals(trip, dataPoint.getTrip());
    }

    @Test
    void testIsValidWithValidTimestamp() {
        DataPoint dataPoint = new DataPoint(1627849200L);
        assertTrue(dataPoint.isValid());
    }

    @Test
    void testIsValidWithNegativeTimestamp() {
        DataPoint dataPoint = new DataPoint(-1L);
        assertFalse(dataPoint.isValid());
    }
}