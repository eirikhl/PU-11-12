package Sensors;

public class MockSpeed implements SensorInterface {
    public void update() {}

    public float getData() {
        return 70.0f;
    }
}
