package main;

/**
 * Created by marianaspas on 24.02.2016.
 */
public class DistanceSimulation extends Distance {

    private float currentTime;
    private float distance;


    public float calculateDistance(float startTime){
        currentTime = time(startTime);
        for(int i= 0; i<20; i++){
            distance = distance(currentTime);
            System.out.println(distance);
            currentTime = time(currentTime);
        }

        return currentTime;
    }


    //this method is used for the ultrasound time- simulation
    public float time(float currentTime){
        double randomFactor = Math.random();
        if(Math.random() < 0.5 && currentTime >= 0.2) {
            currentTime -= randomFactor * 0.05;
        }
        else{
            currentTime += randomFactor * 0.05;
        }
        return  currentTime;
    }
}

