package app.model;

import java.beans.PropertyChangeListener;

public interface ThermostatModel {
    void powerUp();
    void powerDown();
    void attachListener(String evtName, PropertyChangeListener lstnr);
    int getPower();
    void addTemperature(Temperature temperature);
}
