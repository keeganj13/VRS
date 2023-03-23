package com.vrs;

public class Vehicle {
    String make;
    String model;
    Double MPG;
    int year;
    String color;
    String engineType;

    public Vehicle(String make, String model, int year, String color, String engine) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        engineType = engine;
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
}