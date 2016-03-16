package Sensors;

import java.util.Random;

public class Distance implements SensorInterface {
    private float distance;
    private float time;

    public Distance(){
        distance = 0;
        time = (float)0.1;
    }
    //the method takes in the distance measured in seconds (travel-time for the ultrasound) converts it into meters.
    private static float calculateDistance(float time){
        float distance;
        distance = (time * (float)(171.5));      //formula: 171.5 m/s = distance/time
        return distance;
    }

    private void setDistance(float dis){
        distance = dis;
    }

    public void update() {
        Random rand = new Random();
        time = rand.nextFloat()*(0.5f-0.2f)+0.2f;
        setDistance(calculateDistance(time));
    }

    public float getData() {
        return distance;
    }
}
