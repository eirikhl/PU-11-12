package main;

import Sensors.Distance;
import Sensors.FileReading;
import Sensors.SensorInterface;
import Sensors.WeatherToCoefficient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    ArrayList<SensorInterface> sensors;
    HashMap<SensorInterface, Float> values;
    Distance d;
    WeatherToCoefficient w;
    FileReading fr;

    public Main(String filename) throws FileNotFoundException {
        sensors = new ArrayList<>();
        values = new HashMap<>();
        d = new Distance();
        w = new WeatherToCoefficient();
        fr = new FileReading(filename, "vehicle_speed"); // The string defines what the object will be looking for

        sensors.add(d);
        sensors.add(w);
        sensors.add(fr);

        values.put(d, 0.0f);
        values.put(w, 0.0f);
        values.put(fr, 0.0f);
    }

    public float getDistance(){
        return values.get(d);
    }
    public float getWeather(){
        return values.get(w);
    }
    public float getVelocity(){
        return values.get(fr);
    }

    public void update(){
        for (SensorInterface sensor : sensors) {
            sensor.update();
            values.put(sensor, sensor.getData());
        }
    }

    //method returns breaking distance
    // velocity in m/s  In function- call: write weather condition or get weather data
    public static float breakDistance(float velocity, float condition){
        double gravity = 9.81;
        double breakingDistance = (velocity*velocity)/(2*condition*gravity);
        return (float)breakingDistance;
    }

    //calculates if the distance is safe, with respect to the weather condition and the velocity
    public static boolean isSafe(float velocity, float distance, float condition ){
        float breakingDistance = breakDistance(velocity, condition );

        return distance > breakingDistance;
    }

    public static void main(String [] args) throws FileNotFoundException {
        boolean safe;
        Main m = new Main("src/dataInput/downtown-crosstown.json");
        for(int i = 0; i < 355000; i++){
            m.update();
        }
        safe = isSafe(m.getVelocity(), m.getDistance(), m.getWeather());
        System.out.println(m.getVelocity());
        System.out.println(m.getDistance());
        System.out.println(safe);
    }
}
