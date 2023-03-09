package com.vrs;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Date test = new Date(10, 9, 2002);
        System.out.println(test.toString());
        Address test2 = new Address(40, "Main St", "Fort Wayne", "Indiana");
        System.out.println(test2.toString());
    }
}
