package main;

/**
 * Created by marianaspas on 26.02.2016.
 */

public class Distance implements SensorInterface{

    private float distance = 0;
    private float time = (float)0.6;

    //the method takes in the distance measured in seconds (travel-time for the ultrasound) converts it into meters.
    public static float distance(float time){
        float distance;
        distance = (time * (float)(171.5));      //formula: 171.5 m/s = distance/time
        return distance;
    }

    //This method sets a new time-measurement from the distance sensor. Time is measured in seconds
    public void setTime(float newTime){
        this.time=newTime;
    }

    @Override
    public void update() {
        distance = distance(time);

    }

    @Override
    public float getData() {
        return 0;
    }
}
