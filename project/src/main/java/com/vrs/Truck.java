package com.vrs;
import java.io.*;
import java.util.ArrayList;
public class Truck extends Vehicle {
    public int type;
    public double load; //in tons
    private String[] sizes = {"light","medium","heavy"};

    public Truck(String make, String model, int year, double mpg, String color, String engine, int type, double load) {
        super(make, model, year, mpg, color, engine);
        this.type = type;
        this.load = load;
    }
    public void honk() {
        System.out.println("HONK HOONK!");
    }

    public String sizeType()
    {
        //1=light, 2=medium, or 3=heavy
        return sizes[type-1];
    }
    public String toString()
    {
        String out;
        out = " Year: "+year+" "+color + " " + make + " " + model + ". MPG: "+ MPG + ", "+this.sizeType()+" engine, load: "+load+" tons";
        return out;
    }
    public static String[] read() {
        isFile();
        ArrayList<String> lines = new ArrayList<>();
        try {
            // Create a new FileReader object for the file
            FileReader reader = new FileReader("Truck.txt");

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
        File file = new File("Truck.txt");try {
                if (file.createNewFile()) {
                    System.out.println("File Truck.txt created");
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
            FileWriter myWriter = new FileWriter("Truck.txt");
            myWriter.write(this.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void rentV(String vehi) {
        GUI.moveVehicle("Truck.txt","RTruck.txt",vehi);
    }

    public static void returnV(String vehi) {
        GUI.moveVehicle("RTruck.txt","Truck.txt",vehi);
    }
}
