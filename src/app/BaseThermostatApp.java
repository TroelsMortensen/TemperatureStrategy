package app;

import app.model.ThermostatModel;
import app.model.ThermostatModelImpl;
import app.ui.ThermostatController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BaseThermostatApp extends Application {

    protected ThermostatModel openView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../app/ui/Thermostat.fxml"));
        Parent root = loader.load();

        ThermostatController view = loader.getController();

        ThermostatModel model = new ThermostatModelImpl();

        view.init(model);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return model;
    }
}
