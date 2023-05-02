package com.vrs;

public class Motorcycle extends Vehicle {

    public Motorcycle(String make, String model, double mpg, int year, String color, String engine) {
        super(make, model, mpg, year, color, engine);
    }

    public Motorcycle() {
        make = "Yamaha";
        model = "YZF-R1M";
        MPG = 33;
        year = 2023;
        color = "Black";
        engineType = "gas";
    }

}