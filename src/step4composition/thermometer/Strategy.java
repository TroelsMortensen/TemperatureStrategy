package step4composition.thermometer;

public interface Strategy {
    double updateTemperature(double tempPrev, int power,
                             int dist, double outsideTemp, int seconds);
}
