package weatherBusiness;

import java.util.Scanner;

/**
 * Created by TorMagne on 02.03.2016.
 */
public class UserInput {

    //Gets user input and returns coefficient as float
    public float getInput(){
        float weatherCoefficient;
        Scanner sc = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter either [dry|wet|snow|ice]: ");
        String s = sc.nextLine(); // Scans the next token of the input as a String.
        sc.close();
        switch(s){
            case "dry": weatherCoefficient = (float)0.95;//0,6 0,3 0,2
                break;
            case "wet": weatherCoefficient = (float)0.6;
                break;
            case "snow": weatherCoefficient = (float)0.3;
                break;
            case "ice": weatherCoefficient = (float)0.2;
                break;
            default: weatherCoefficient = 1;
        }
        return weatherCoefficient;

    }
}
