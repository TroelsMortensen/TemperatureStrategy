package step4composition.thermometer;

import app.model.Temperature;
import app.model.ThermostatModel;

public class Thermometer implements Runnable {

    private double temp;
    private final String id, unit;
    private final int distance;
    private final ThermostatModel model;
    private Strategy strategy;

    public Thermometer(String id, int distance, String unit,
                       ThermostatModel model, Strategy strategy) {
        this.id = id;
        this.distance = distance;
        this.unit = unit;
        this.model = model;
        this.strategy = strategy;
    }


    @Override
    public void run() {
        while (true) {

            temp = strategy.updateTemperature(temp, model.getPower(), distance, 0, 4);

            model.addTemperature(new Temperature(temp, id, unit));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
