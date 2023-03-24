package com.vrs;

public class Truck extends Vehicle {
    public int type;
    public double load;

    public Truck(String make, String model, double mpg, int year, String color, String engine, int type, double load) {
        super(make, model, mpg, year, color, engine);
        this.type = type;
        this.load = load;
    }
}
