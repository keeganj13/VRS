package com.vrs;

public class Customer {
    public String Name;
    private Date DOB;
    private Address address;

    public Customer(String name, Date bday, Address add) {
        Name = name;
        DOB = bday;
        address = add;
    }
}
