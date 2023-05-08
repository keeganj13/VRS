package com.vrs;
import java.io.*;
import java.util.ArrayList;

public class Bus extends Vehicle {
    public int numSeats;
    public int numFloors;

    public Bus(String make, String model, int year, double mpg, String color, String engine, int seats, int floors) {
        super(make, model, year, mpg, color, engine);
        numSeats = seats;
        numFloors = floors;
    }

    public String toString()
    {
        String out;
        out = " Year: "+year+" "+color + " " + make + " " + model + ". MPG: "+ MPG + ", floors: "+numFloors+", seats: "+numSeats;
        return out;
    }
    public static String[] read() {
        isFile();
        ArrayList<String> lines = new ArrayList<>();
        try {
            // Create a new FileReader object for the file
            FileReader reader = new FileReader("Bus.txt");

            // Create a new BufferedReader object to read from the FileReader
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Read each line of text from the file and add it to the ArrayList
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            // Close the BufferedReader object to release any resources it was using
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Convert the ArrayList to a String array and return it
        String[] linesArray = new String[lines.size()];
        lines.toArray(linesArray);
        return linesArray;
    }
    public static int isFile() {
        File file = new File("Bus.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File Bus.txt created");
            } else {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                System.out.println("File already exists.");
                int lineCount = 0;
                while (bufferedReader.readLine() != null) {
                    lineCount++;
                }

                bufferedReader.close();
                return lineCount;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // If an error occurs or the file doesn't exist, return 0
        return 0;
    }
    public void addData() {
        isFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Bus.txt", true));
            writer.write(this.toString());
            writer.newLine();
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void rentV(String vehi) {
        GUI.moveVehicle("Bus.txt","RBus.txt",vehi);
    }

    public static void returnV(String vehi) {
        GUI.moveVehicle("RBus.txt","Bus.txt",vehi);
    }

}
