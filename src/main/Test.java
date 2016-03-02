package main;

import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        float x;
        try {
            FileReading fr = new FileReading("src/dataInput/downtown-crosstown.json");
            for(int i = 0; i < 100; i++){
                fr.update();
                x = fr.getData();
                System.out.println(x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
