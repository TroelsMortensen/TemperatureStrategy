package step3inheritance.thermometer;

import app.model.Temperature;
import app.model.ThermostatModel;

public abstract class BaseThermometer implements Runnable {

    private double temp;
    private final String id, unit;
    private final int distance;
    private final ThermostatModel model;

    public BaseThermometer(String id, int distance, String unit, ThermostatModel model) {
        this.id = id;
        this.distance = distance;
        this.unit = unit;
        this.model = model;
    }

    protected abstract double updateTemperature(double tempPrev, int power,
                                                int dist, double outsideTemp, int seconds);

    @Override
    public void run() {
        while (true) {

            temp = updateTemperature(temp, model.getPower(), distance, 0, 4);

            model.addTemperature(new Temperature(temp, id, unit));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
