package step3inheritance.thermometer;

import app.model.ThermostatModel;

public class StaticOutsideThermometer extends BaseThermometer{
    public StaticOutsideThermometer(String id, int distance, String unit, ThermostatModel model) {
        super(id, distance, unit, model);
    }

    @Override
    protected double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
        return 0;
    }
}
