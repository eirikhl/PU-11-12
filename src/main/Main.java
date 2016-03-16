package main;

import weatherBusiness.weatherToCoefficient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main{
    ArrayList<SensorInterface> sensors;
    HashMap<SensorInterface, Float> values;
    Distance d;
    weatherToCoefficient w;
    FileReading fr;

    public Main(String filename) throws FileNotFoundException {
        sensors = new ArrayList<>();
        values = new HashMap<>();
        d = new Distance();
        w = new weatherToCoefficient();
        fr = new FileReading(filename);

        sensors.add(d);
        sensors.add(w);
        sensors.add(fr);

        values.put(d, 0.0f);
        values.put(w, 0.0f);
        values.put(fr, 0.0f);
    }

    private static float startTime = (float)0.6; // a chosen time to start with

    public void update(){
        for(Iterator<SensorInterface> i = sensors.iterator(); i.hasNext();){
            SensorInterface sensor = i.next();
            sensor.update();
            values.put(sensor, sensor.getData());
        }
    }

    public static void main(String [] args) throws FileNotFoundException, InterruptedException{
        boolean safe;
        Main m = new Main("src/dataInput/downtown-crosstown.json");
        int i = 0;
        while(true){
            m.update();
            Thread.sleep(25);
            if(i%50 == 1){
                safe = isSafe(m.getVelocity(), m.getDistance(), m.getWeather());
                if(! safe){
                    System.out.println("You should break");
                }
            }
            if(i >= 350000){
                break;
            }
        }
    }

    //method returns breaking distance
    public static float breakDistance(float velocity, float condition){ // velocity in m/s  In function- call: write weather condition or get weather data
        double gravity = 9.81;
        double breakingDistance = (velocity*velocity)/(2*condition*gravity);
        return (float)breakingDistance;
    }

    //calculates if the distance is safe, with respect to the weather condition and the velocity
    public static boolean isSafe(float velocity, float distance, float condition ){
        float breakingDistance = breakDistance(velocity, condition );

        if(distance > breakingDistance){
            return true;
        }
        return false;
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
}
