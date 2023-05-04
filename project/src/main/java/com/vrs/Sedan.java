package com.vrs;

public class Sedan extends Vehicle {
    public int numSeats;
    public Sedan (String make, String model, int year, String color, String engineType, int numSeats)
    {
           this.make = make;
           this.model = model;
           this.year = year;
           this.color = color;
           this.engineType = engineType;
           this.numSeats = numSeats;
   }
}
