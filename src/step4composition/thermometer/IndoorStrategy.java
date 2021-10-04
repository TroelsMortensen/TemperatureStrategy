package step4composition.thermometer;

public class IndoorStrategy implements Strategy
{
    @Override
    public double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
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
