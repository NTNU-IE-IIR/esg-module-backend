package no.ntnu.idata2900.project.backend.modelTest.datapointsTest;

import no.ntnu.idata2900.project.backend.models.datapoints.DataPoint;
import no.ntnu.idata2900.project.backend.models.datapoints.Position;
import no.ntnu.idata2900.project.backend.models.datapoints.Vessel;
import no.ntnu.idata2900.project.backend.models.datapoints.Weather;
import no.ntnu.idata2900.project.backend.models.datapoints.MarineWeather;
import no.ntnu.idata2900.project.backend.models.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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