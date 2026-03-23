package models;

public class Vehicle {
    private String number;
    private String color;
    private String model;
    private VehicleType type;

    public Vehicle(String number, String color, String model, VehicleType type) {
        this.number = number;
        this.color = color;
        this.model = model;
        this.type = type;
    }

    public String getNumber() { return number; }
    public String getColor() { return color; }
    public String getModel() { return model; }
    public VehicleType getType() { return type; }
}
