package step3inheritance;

import app.BaseThermostatApp;
import app.model.ThermostatModel;
import javafx.stage.Stage;
import step3inheritance.thermometer.DynamicOutsideThermometer;
import step3inheritance.thermometer.InsideThermometer;
import step3inheritance.thermometer.StaticOutsideThermometer;

public class InheritanceApp extends BaseThermostatApp {

    @Override
    public void start(Stage stage) throws Exception {
        ThermostatModel model = openView(stage);
        new Thread(new InsideThermometer("Living room", 1,"C", model)).start();
        new Thread(new InsideThermometer("Kitchen", 2,"C", model)).start();
        new Thread(new InsideThermometer("Bed room", 3,"C", model)).start();
        new Thread(new StaticOutsideThermometer("Outside static", 0,"C", model)).start();
        new Thread(new DynamicOutsideThermometer("Outside", 0,"C", model, -5, 5)).start();
    }


}
