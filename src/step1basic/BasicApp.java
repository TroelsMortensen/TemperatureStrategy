package step1basic;

import app.BaseThermostatApp;
import app.model.ThermostatModel;
import javafx.stage.Stage;
import step1basic.thermometer.ThermometerBasic;

public class BasicApp extends BaseThermostatApp {

    @Override
    public void start(Stage stage) throws Exception {
        ThermostatModel model = openView(stage);
        new Thread(new ThermometerBasic("Living room", 1,"C", model)).start();
        new Thread(new ThermometerBasic("Kitchen", 2,"C", model)).start();
        new Thread(new ThermometerBasic("Bed room", 3,"C", model)).start();
    }


}
