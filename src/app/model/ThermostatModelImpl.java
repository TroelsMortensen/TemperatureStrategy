package app.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

public class ThermostatModelImpl implements ThermostatModel {

    private final Map<String, Temperature> temps = new HashMap<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private int power = 0;

    @Override
    public void powerUp() {
        power = Math.min(++power, 3);
        support.firePropertyChange("PowerChange", -1, power);
    }

    @Override
    public void powerDown() {
        power = Math.max(--power, 0);
        support.firePropertyChange("PowerChange", -1, power);
    }

    @Override
    public void attachListener(String evtName, PropertyChangeListener lstnr) {
        support.addPropertyChangeListener(evtName, lstnr);
    }

    public int getPower(){
        return power;
    }

    @Override
    public void addTemperature(Temperature temperature) {
        Temperature prev = temps.get(temperature.getId());

        support.firePropertyChange("NewTemp", prev, temperature);

        temps.put(temperature.getId(), temperature);
    }
}
