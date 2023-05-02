package com.vrs;

public class Truck extends Vehicle {
    public int type;
    public double load;

    public Truck(String make, String model, double mpg, int year, String color, String engine, int type, double load) {
        super(make, model, mpg, year, color, engine);
        this.type = type;
        this.load = load;
    }

    public Truck() {
        make = "Ford";
        model = "F-150 XL";
        MPG = 21;
        year = 2023;
        color = "Grey";
        engineType = "Gas";
        type = 3;
        load = 1800;
    }
}
