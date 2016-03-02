package main;

/**
 * Created by marianaspas on 26.02.2016.
 */

public class Distance {

    //the method takes in the distance measured in seconds (travel-time for the ultrasound) converts it into meters.
    public static float distance(float time){
        float distance;
        distance = (time * (float)(171.5));      //formula: 171.5 m/s = distance/time
        return distance;
    }

}
