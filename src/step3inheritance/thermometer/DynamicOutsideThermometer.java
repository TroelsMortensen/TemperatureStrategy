package step3inheritance.thermometer;

import app.model.ThermostatModel;

public class DynamicOutsideThermometer extends BaseThermometer{

    private double min;
    private double max;

    public DynamicOutsideThermometer(String id, int distance, String unit, ThermostatModel model, double min, double max) {
        super(id, distance, unit, model);
        this.min = min;
        this.max = max;
    }

    @Override
    protected double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
        double left = tempPrev - min;

        double right = max - tempPrev;

        int sign = Math.random() * (left + right) > left ? 1 : -1;

        tempPrev += sign * Math.random();

        return tempPrev;
    }
}
