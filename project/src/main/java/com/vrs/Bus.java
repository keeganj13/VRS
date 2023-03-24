package com.vrs;

public class Bus extends Vehicle {
    public int numSeats;
    public int numFloors;

    public Bus(String make, String model, double mpg, int year, String color, String engine, int seats, int floors) {
        super(make, model, mpg, year, color, engine);
        numSeats = seats;
        numFloors = floors;
    }
}
