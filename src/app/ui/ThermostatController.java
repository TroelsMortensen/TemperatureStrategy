package app.ui;

import app.model.Temperature;
import app.model.ThermostatModel;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
import java.util.Comparator;

public class ThermostatController {

    public Label powerLabel;
    public TableView<Temperature> table;
    public TableColumn<String, Temperature> idColumn;
    public TableColumn<String, Temperature> tempColumn;
    public TableColumn<String, Temperature> unitColumn;

    private ThermostatModel model;

    private ObservableList<Temperature> temps;

    public void init(ThermostatModel model) {
        this.model = model;
        temps = FXCollections.observableArrayList();

        model.attachListener("NewTemp", (evt) -> Platform.runLater(() ->
                onNewTemp(evt))
        );
        model.attachListener("PowerChange", (evt) -> Platform.runLater(() ->
                onPowerChange(evt))
        );

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("tempUnit"));
        table.setItems(temps);
    }

    private void onPowerChange(PropertyChangeEvent evt) {
        powerLabel.setText(evt.getNewValue().toString());
    }

    private void onNewTemp(PropertyChangeEvent evt) {
        Temperature newValue = (Temperature) evt.getNewValue();
        roundDouble(newValue);
        temps.removeIf(temperature -> temperature.getId().equals(newValue.getId()));
        temps.add(newValue);
        temps.sort((o1, o2) -> o1.getId().compareTo(o2.getId())); // sorting list alphabetically by temperature id.
    }

    private void roundDouble(Temperature newValue) {
        int scale = (int) Math.pow(10, 1);
        double d =  (double) Math.round(newValue.getValue() * scale) / scale;
        newValue.setValue(d);
    }

    public void powerDown() {
        model.powerDown();
    }

    public void powerUp() {
        model.powerUp();
    }
}
