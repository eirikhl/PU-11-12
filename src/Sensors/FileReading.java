package Sensors;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReading implements SensorInterface{
    BufferedReader br;
    StringBuilder sb;
    float data;
    String value;

    // constructor, allows for custom filename and makes the BufferedReader and StringBuilder usable
    // it is now possible to look for specific data, not only vehicle_speed
    public FileReading(String filename, String value) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(filename));
        sb = new StringBuilder();
        data = -1;
        this.value = value;
    }

    // sets data
    private void setData(float x){
        if(value.equals("vehicle_speed")){
            data = x/3.6f;
        }
        else{
            data = x;
        }
    }

    // converts a json line to float for vehicle_data
    private float getVehicleData(String line){
        String jsonLine = line;
        jsonLine = jsonLine.replaceAll("[{}\"]", "");
        String[] partLine = jsonLine.split(",");
        String[] nameValue = partLine[0].split(":");
        if(nameValue[1].equals(value)) {
            return Float.parseFloat(partLine[1].split(":")[1]);
        }
        return -1;
    }

    // updates the object's data
    public void update(){
        String line;
        String output;
        float utputt;

        // reads one line at a time, puts it into a stringbuilder, converts it to float, sets the current data,
        // and finally clears the stringbuilder
        try {
            line = br.readLine();
            sb.append(line);
            output = sb.toString();
            sb.setLength(0);
            utputt = getVehicleData(output);
            if(utputt != -1){
                setData(utputt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // returns the object's data
    public float getData(){
        return data;
    }
}
