package com.vrs;

public class Address {
    public int addressNumber;
    public String street;
    public String city;
    public String state;

    public Address(int addressNum, String streetName, String cityName, String stateName) {
        addressNumber = addressNum;
        street = streetName;
        city = cityName;
        state = stateName;
    }

    public String toString() {
        String out = String.format("%d %s, %s, %s", addressNumber, street, city, state);
        return out;
    }
}
