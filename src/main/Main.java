package main;

/**
 * Created by marianaspas on 24.02.2016.
 */
public class Main extends Distance{

    private static float startTime = (float)0.6; // a chosen time to start with

    public static void main(String [] args){
        DistanceSimulation distanceSimulation = new DistanceSimulation();
        distanceSimulation.calculateDistance(startTime);
        boolean safe = isSafe(15, startTime, "snow");
        System.out.println("Is it safe to drive at this velocity: "+safe);

    }

    //method returns breaking distance
    public static float breakDistance(float velocity, String condition){ // velocity in m/s  In function- call: write weather condition or get weather data


//        private double dry = 0.95;
//        private double wet = 0.6;
//        private double snow = 0.3;
//        private double ice = 0.2;

        double friction = 0;
        double gravity = 9.81;

        switch (condition){
            case "dry":
                friction = 0.95;
                break;
            case "wet":
                friction = 0.6;
                break;
            case "snow":
                friction = 0.3;
                break;
            case "ice":
                friction = 0.2;
                break;
            default:
                System.out.println("Invalid condition");
                break;
        }

        double breakingDistance = (velocity*velocity)/(2*friction*gravity);
        return (float)breakingDistance;
    }

    //calculates if the distance is safe, with respect to the weather condition and the velocity
    public static boolean isSafe(float velocity, float time, String condition ){
        float distance = distance(time);
        float breakingDistance = breakDistance(velocity, condition );

        if(distance > breakingDistance){
            return true;
        }
        return false;
    }
}
