package step3inheritance.thermometer;

import app.model.ThermostatModel;

public class InsideThermometer extends BaseThermometer{

    public InsideThermometer(String id, int distance, String unit, ThermostatModel model) {
        super(id, distance, unit, model);
    }

    @Override
    protected double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
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
}
