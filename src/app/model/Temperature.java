package app.model;

public class Temperature {
    private double value;
    private String id;
    private String tempUnit;

    public Temperature(double value, String id, String tempUnit) {
        this.value = value;
        this.id = id;
        this.tempUnit = tempUnit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }
}
