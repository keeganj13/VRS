import java.io.*;
import java.util.ArrayList;

public class Customer {
    public String name;
    private Date DOB;
    private Address address;

    public Customer(String name, Date bday, Address add) {
        this.name = name;
        DOB = bday;
        address = add;
    }

    public String toString() {
        String out = String.format("%s Lives: %s, DOB: %s", name, address.toString(), DOB.toString());
        return out;
    }
    public static String[] read() {
        isFile();
        ArrayList<String> lines = new ArrayList<>();
        try {
            // Create a new FileReader object for the file
            FileReader reader = new FileReader("Customer.txt");

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
        File file = new File("Customer.txt");try {
            if (file.createNewFile()) {
                System.out.println("File Customer.txt created");
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
            FileWriter myWriter = new FileWriter("Customer.txt");
            myWriter.write(this.toString()+"\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
 
