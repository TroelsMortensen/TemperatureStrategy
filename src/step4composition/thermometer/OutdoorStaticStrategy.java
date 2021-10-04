package step4composition.thermometer;

public class OutdoorStaticStrategy implements Strategy{
    @Override
    public double updateTemperature(double tempPrev, int power, int dist, double outsideTemp, int seconds) {
        return 0;
    }
}
