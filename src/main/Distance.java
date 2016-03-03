package main;


import java.util.Random;

public class Distance implements SensorInterface{
    private float distance = 0;
    private float time = (float)0.1;
    //the method takes in the distance measured in seconds (travel-time for the ultrasound) converts it into meters.
    public static float distance(float time){
        float distance;
        distance = (time * (float)(171.5));      //formula: 171.5 m/s = distance/time
        return distance;
    }

    @Override
    public void update() {
        Random rand = new Random();
        time = rand.nextFloat()*(0.5f-0.2f)+0.2f;
        distance = distance(time);
    }

    @Override
    public float getData() {
        return distance;
    }

    public void setTime(float newTime){
        this.time = newTime;
    }
}
