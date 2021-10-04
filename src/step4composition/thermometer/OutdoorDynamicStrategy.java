package step4composition.thermometer;

public class OutdoorDynamicStrategy implements Strategy {

    private final double min, max;

    public OutdoorDynamicStrategy(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
        double left = tempPrev - min;

        double right = max - tempPrev;

        int sign = Math.random() * (left + right) > left ? 1 : -1;

        tempPrev += sign * Math.random();

        return tempPrev;
    }
}
