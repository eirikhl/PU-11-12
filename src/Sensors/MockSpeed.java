package Sensors;

/**
 * Created by Eirik on 20.04.2016.
 */
public class MockSpeed implements SensorInterface {
    @Override
    public void update() {

    }

    @Override
    public float getData() {
        return 70;
    }
}
