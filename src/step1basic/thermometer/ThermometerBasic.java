package step1basic.thermometer;

import app.model.Temperature;
import app.model.ThermostatModel;

public class ThermometerBasic implements Runnable {

    private double temp;
    private final String id;
    private final int distance;
    private final String unit;
    private final ThermostatModel model;

    public ThermometerBasic(String id, int distance, String unit, ThermostatModel model) {
        this.id = id;
        this.distance = distance;
        this.unit = unit;
        this.model = model;
    }

    @Override
    public void run() {
        while(true) {

            temp = updateTemperature(temp, model.getPower(), distance, 0, 4);
            model.addTemperature(new Temperature(temp, id,unit ));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private double updateTemperature(double prevTemp, int power, int dist, double outsideTemp, int seconds) {

        double tMax = Math.min(11 * power + 10, 11 * power + 10 + outsideTemp);

        tMax = Math.max(Math.max(prevTemp, tMax), outsideTemp);

        double heaterTerm = 0;

        if (power > 0) {

            double den = Math.max((tMax * (20 - 5 * power) * (dist + 5)), 0.1);

            heaterTerm = 30 * seconds * Math.abs(tMax - prevTemp) / den;

        }

        double outdoorTerm = (prevTemp - outsideTemp) * seconds / 250.0;

        prevTemp = Math.min(Math.max(prevTemp - outdoorTerm + heaterTerm, outsideTemp), tMax);

        return prevTemp;

    }
}
