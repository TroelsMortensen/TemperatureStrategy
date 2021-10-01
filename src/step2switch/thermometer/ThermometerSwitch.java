package step2switch.thermometer;

import app.model.Temperature;
import app.model.ThermostatModel;

public class ThermometerSwitch implements Runnable {

    private double temp;
    private final String id;
    private final int distance;
    private final String unit;
    private final ThermostatModel model;
    private final Type type;

    public enum Type {
        INSIDE,
        OUTSIDE_STATIC,
        OUTSIDE_DYNAMIC
    }

    public ThermometerSwitch(String id, int distance, String unit, ThermostatModel model, Type type) {
        this.id = id;
        this.distance = distance;
        this.unit = unit;
        this.model = model;
        this.type = type;
    }

    private double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {

        double tMax = Math.min(11 * power + 10, 11 * power + 10 + outsideTemp);

        tMax = Math.max(Math.max(tempPrev, tMax), outsideTemp);

        double heaterTerm = 0;

        if (power > 0) {

            double den = Math.max((tMax * (20 - 5 * power) * (dist + 5)), 0.1);

            heaterTerm = 30 * seconds * Math.abs(tMax - tempPrev) / den;

        }

        double outdoorTerm = (tempPrev - outsideTemp) * seconds / 250.0;

        tempPrev = Math.min(Math.max(tempPrev - outdoorTerm + heaterTerm, outsideTemp), tMax);

        return tempPrev;

    }

    public double externalTemperature(double tempPrev, double min, double max) {

        double left = tempPrev - min;

        double right = max - tempPrev;

        int sign = Math.random() * (left + right) > left ? 1 : -1;

        tempPrev += sign * Math.random();

        return tempPrev;

    }

    @Override
    public void run() {
        while (true) {

            if (type == Type.OUTSIDE_STATIC) { // static outside temp
                temp = 0;
            } else if (type == Type.OUTSIDE_DYNAMIC) { // dynamic
                temp = externalTemperature(temp, -5, 5); // what if I want other values? More constructor parameters? New constructor?
            } else if (type == Type.INSIDE) {
                temp = updateTemperature(temp, model.getPower(), distance, 0, 4);
            }

            model.addTemperature(new Temperature(temp, id, unit));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
