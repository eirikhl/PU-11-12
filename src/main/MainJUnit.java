package main;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class MainJUnit {
    Main m;

    public MainJUnit() throws FileNotFoundException {
        m = new Main("src/dataInput/downtown-crosstown.json");
    }

    @Test
    public void testInitialize() throws FileNotFoundException{
    }

    @Test
    public void testVelocityData() throws Exception{
        float v = m.getVelocity();
        assertEquals(v, 0, 0.0f);
    }

    @Test
    public void testDistanceData() throws Exception{
        float d = m.getDistance();
        assertEquals(d, 0, 0.0f);
    }

    @Test
    public void testWeatherData() throws Exception{
        float w = m.getWeather();
        assertEquals(w, 0, 0.0f);
    }

    @Test
    public void testIsSafe() throws Exception{
        boolean safe = m.isSafe(12.5f, 40.0f, 0.6f);
        assertTrue(safe);
    }

    @Test
    public void testIsNotSafe() throws Exception{
        boolean safe = m.isSafe(12.5f, 10.0f, 0.6f);
        assertFalse(safe);
    }
}
