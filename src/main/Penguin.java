package main;

import Sensors.DistanceMonitor;
import Sensors.FileReading;
import Sensors.SensorInterface;
import Sensors.WeatherToCoefficient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Penguin {
    ArrayList<SensorInterface> sensors;
    HashMap<SensorInterface, Float> values;
    DistanceMonitor d;
    WeatherToCoefficient w;
    FileReading fr;

    GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput ledPin;

    public Penguin(String filename) throws FileNotFoundException {
        ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pinLED", Pinstate.LOW); //pin nr 13

        sensors = new ArrayList<>();
        values = new HashMap<>();

        d = new DistanceMonitor(RaspiPin.GPIO_00, RaspiPin.GPIO_07);
        w = new WeatherToCoefficient();
        fr = new FileReading(filename, "vehicle_speed"); // The string defines what the object will be looking for

        sensors.add(d);
        sensors.add(w);
        sensors.add(fr);

        values.put(d, 0.0f);
        values.put(w, 0.0f);
        values.put(fr, 0.0f);
    }

    public void update(){
        for (SensorInterface sensor : sensors) {
            sensor.update();
            values.put(sensor, sensor.getData());
        }
    }

    public static void main(String [] args) throws FileNotFoundException, InterruptedException{
        boolean safe;
        Penguin m = new Penguin("dataInput/downtown-crosstown.json");
        int i = 0;
        while(true){
            i++;
            m.update();
            Thread.sleep(25);
            if(i%50 == 1){
                safe = isSafe(m.getVelocity(), m.getDistance(), m.getWeather());
                if(! safe){
                    System.out.println("You should break");
                    m.ledPin.high();
                }
                else{
                    m.ledPin.low();
                }
            }
            if(i >= 350000){
                break;
            }
        }
    }

    // method returns breaking distance
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
