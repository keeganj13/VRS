package com.vrs;

public class Vehicle {
    String make;
    String model;
    double MPG;
    int year;
    String color;
    String engineType;

    public Vehicle(String make, String model, double mpg, int year, String color, String engine) {
        this.make = make;
        this.model = model;
        MPG = mpg;
        this.year = year;
        this.color = color;
        engineType = engine;
    }

    public Vehicle() {
        make = "Nissan";
        model = "Altima";
        MPG = 33.0;
        year = 2023;
        color = "Black";
        engineType = "Gas";
    }

    // getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getEngineType() {
        return engineType;
    }

    // setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void startEngine() {
        System.out.println("Vroom Vroom");
    }

    public void honk() {
        System.out.println("Honk honk!");
    }
}