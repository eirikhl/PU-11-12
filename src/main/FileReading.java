package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReading implements SensorInterface{
    // defines variables
    BufferedReader br;
    StringBuilder sb;
    float speed;

    // constructor, allows for custom filename and gives the BufferedReader and StringBuilder variables
    public FileReading(String filename) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(filename));
        sb = new StringBuilder();
        speed = -1;
    }

    // updates the object's data
    public void update(){
        String line;
        String output;
        float utputt;

        // reads one line at a time, puts it into a stringbuilder, converts it to float, sets the current data,
        // clears the stringbuilder
        try {
            line = br.readLine();
            sb.append(line);
            output = sb.toString();
            sb.setLength(0);
            utputt = getVehicleSpeed(output);
            if(utputt != -1){
                setData(utputt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // converts a json line to float for vehicle_speed
    public float getVehicleSpeed(String line){
        String jsonLine = line.replace("{","");
        jsonLine = jsonLine.replace("}","");
        jsonLine = jsonLine.replace("\"","");
        String[] partLine = jsonLine.split(",");
        String[] nameValue = partLine[0].split(":");
        if(nameValue[1].equals("vehicle_speed")) {
            return Float.parseFloat(partLine[1].split(":")[1]);
        }
        return -1;
    }

    // sets speed
    private void setData(float x){
        speed = x/3.6f;
    }

    // returns the object's data (current vehicle_speed)
    public float getData(){
        return speed;
    }
}
