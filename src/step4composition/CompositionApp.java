package step4composition;

import app.BaseThermostatApp;
import app.model.ThermostatModel;
import javafx.stage.Stage;
import step4composition.thermometer.*;

public class CompositionApp extends BaseThermostatApp {

    @Override
    public void start(Stage stage) throws Exception {
        ThermostatModel model = openView(stage);
        Strategy indoorStrategy = new IndoorStrategy();
        Strategy outDynamic = new OutdoorDynamicStrategy(-5, 5);
        Strategy outStatic = new OutdoorStaticStrategy();

        new Thread(new Thermometer("Living room", 1,"C", model, indoorStrategy)).start();
        new Thread(new Thermometer("Kitchen", 2,"C", model, indoorStrategy)).start();
        new Thread(new Thermometer("Bed room", 3,"C", model, indoorStrategy)).start();
        new Thread(new Thermometer("Outside static", 0,"C", model, outStatic)).start();
        new Thread(new Thermometer("Outside", 0,"C", model, outDynamic)).start();
    }
}
