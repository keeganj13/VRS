package com.vrs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame implements ActionListener {
    private static String searchForString(String query, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(query)) {
                    return line;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private static void combineTextFiles(String... filePaths) {
        boolean foundValidFile = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("combined.txt"))) {
            for (String filePath : filePaths) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    foundValidFile = true;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    // Ignore file if it hasn't been made yet
                }
            }
            if (!foundValidFile) {
                // create the combined.txt file but keep it empty if no files are created
                writer.write("");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        if(true){}
    }
    private static void deleteCombined() {
        File file = new File("combined.txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }
    public static void moveVehicle(String in, String out, String a) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(out, true));
            writer.newLine(); // Add a new line before appending the text
            writer.write(a);
            writer.close();

            File inputFile = new File(in);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // if the current line contains the string to remove, skip it
                if (currentLine.contains(a)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            reader.close();
            writer.close();

            // delete the original file
            inputFile.delete();

            // rename the temporary file to the original file name
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public GUI() {
        // Window name
        setTitle("VRS");
        setSize(2 * 690, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button names
        JButton button1 = new JButton("Add customer");
        JButton button2 = new JButton("Add vehicle");
        JButton button3 = new JButton("Rent vehicle");
        JButton button4 = new JButton("Return vehicle");
        JButton button5 = new JButton("Search for a customer");
        JButton button6 = new JButton("Search for a vehicle");
        JButton button7 = new JButton("Generate a report for available vehicles");
        JButton button8 = new JButton("Generate a report for rented vehicles");

        // Add the buttons to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 4));
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(button5);
        contentPane.add(button6);
        contentPane.add(button7);
        contentPane.add(button8);

        // Add the action listeners
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
    }


    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getText();
        switch (buttonName) {
            case "Add customer": {
                final JFrame inputWindow = new JFrame("Add Customer");
                JPanel inputPanel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);

                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                final JLabel nameLabel = new JLabel("Full Name: ");
                constraints.gridx = 0;
                constraints.gridy = 0;
                inputPanel.add(nameLabel, constraints);

                final JTextField nameTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(nameTextField, constraints);

                final JLabel houseNumLabel = new JLabel("House Number: ");
                constraints.gridx = 0;
                constraints.gridy = 1;
                inputPanel.add(houseNumLabel, constraints);

                final JTextField houseNumTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(houseNumTextField, constraints);

                final JLabel streetNameLabel = new JLabel("Street: ");
                constraints.gridx = 0;
                constraints.gridy = 2;
                inputPanel.add(streetNameLabel, constraints);

                final JTextField streetNameTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(streetNameTextField, constraints);

                final JLabel cityLabel = new JLabel("City: ");
                constraints.gridx = 0;
                constraints.gridy = 3;
                inputPanel.add(cityLabel, constraints);

                final JTextField cityTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(cityTextField, constraints);

                final JLabel stateLabel = new JLabel("State: ");
                constraints.gridx = 0;
                constraints.gridy = 4;
                inputPanel.add(stateLabel, constraints);

                final JTextField stateTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(stateTextField, constraints);

                final JLabel dobLabel = new JLabel("Date of Birth [mm/dd/yyyy]: ");
                constraints.gridx = 0;
                constraints.gridy = 5;
                inputPanel.add(dobLabel, constraints);

                final JTextField dobTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(dobTextField, constraints);


                final JButton cancelButton = new JButton("Cancel");
                constraints.gridx = 0;
                constraints.gridy = 6;
                inputPanel.add(cancelButton, constraints);

                final JButton submitButton = new JButton("Submit");
                constraints.gridx = 1;
                inputPanel.add(submitButton, constraints);

                inputWindow.add(inputPanel);
                inputWindow.setSize(400, 300);
                inputWindow.setVisible(true);

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = nameTextField.getText();
                        String houseN = houseNumTextField.getText();
                        String streetName = streetNameTextField.getText();
                        String city = cityTextField.getText();
                        String state = stateTextField.getText();
                        String dobString = dobTextField.getText();
                        String[] checker = new String[6];
                        checker[0] = name;
                        checker[1] = houseN;
                        checker[2] = streetName;
                        checker[3] = city;
                        checker[4] = state;
                        checker[5] = dobString;

                        for (String temp : checker) {
                            if (temp.length() == 0) {
                                JOptionPane temp1 = new JOptionPane();
                                temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }


                        try {
                            String[] dobS = dobString.split("/");

                            int houseNum = Integer.parseInt(houseN);

                            int[] dobNums = new int[3];
                            for (int i = 0; i < dobNums.length; i++) {
                                dobNums[i] = Integer.parseInt(dobS[i]);
                            }
                            Address tempAddress = new Address(houseNum, streetName, city, state);
                            Date tempDate = new Date(dobNums[1], dobNums[0], dobNums[2]);
                            Customer tempCustomer = new Customer(name, tempDate, tempAddress);
                            tempCustomer.addData();
                            System.out.println(tempCustomer + " was added to the database.");

                            inputWindow.dispose();
                        } catch (Exception exception) {
                            JOptionPane temp1 = new JOptionPane();
                            temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Customer Addition cancelled");
                        inputWindow.dispose();
                    }
                });
                break;
            }
            case "Add vehicle": {
                JFrame buttonWindow = new JFrame("Add a Vehicle");
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);
                buttonWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                constraints.weightx = 0.5;
                constraints.weighty = 0.5;


                final JButton busButton = new JButton("Add a Bus");
                Dimension buttonSize = new Dimension(busButton.getPreferredSize().width, busButton.getPreferredSize().height * 4);
                busButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(busButton, constraints);

                final JButton motorcycleButton = new JButton("Add a Motorcycle");
                motorcycleButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(motorcycleButton, constraints);

                final JButton sedanButton = new JButton("Add a Sedan");
                sedanButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 1;
                panel.add(sedanButton, constraints);

                final JButton suvButton = new JButton("Add a SUV");
                suvButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(suvButton, constraints);

                final JButton truckButton = new JButton("Add a Truck");
                truckButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 2;
                panel.add(truckButton, constraints);

                final JButton cancelButton = new JButton("Cancel");

                buttonSize = new Dimension(cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height * 3);
                cancelButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(cancelButton, constraints);
                busButton.addActionListener(f ->
                        {
                            {
                                buttonWindow.dispose();
                                final JFrame inputWindow = new JFrame("Add a Bus");
                                JPanel inputPanel = new JPanel(new GridBagLayout());
                                constraints.fill = GridBagConstraints.HORIZONTAL;
                                constraints.anchor = GridBagConstraints.WEST;
                                constraints.insets = new Insets(5, 5, 5, 5);

                                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                                final JLabel makeLabel = new JLabel("Make: ");
                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                inputPanel.add(makeLabel, constraints);

                                final JTextField makeTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(makeTextField, constraints);

                                final JLabel modelLabel = new JLabel("Model: ");
                                constraints.gridx = 0;
                                constraints.gridy = 1;
                                inputPanel.add(modelLabel, constraints);

                                final JTextField modelTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(modelTextField, constraints);

                                final JLabel yearLabel = new JLabel("Year: ");
                                constraints.gridx = 0;
                                constraints.gridy = 2;
                                inputPanel.add(yearLabel, constraints);

                                final JTextField yearTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(yearTextField, constraints);

                                final JLabel mpgLabel = new JLabel("MPG: ");
                                constraints.gridx = 0;
                                constraints.gridy = 3;
                                inputPanel.add(mpgLabel, constraints);

                                final JTextField mpgTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(mpgTextField, constraints);

                                final JLabel colorLabel = new JLabel("Color: ");
                                constraints.gridx = 0;
                                constraints.gridy = 4;
                                inputPanel.add(colorLabel, constraints);

                                final JTextField colorTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(colorTextField, constraints);

                                final JLabel engineLabel = new JLabel("Engine Type: ");
                                constraints.gridx = 0;
                                constraints.gridy = 5;
                                inputPanel.add(engineLabel, constraints);

                                final JTextField engineTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(engineTextField, constraints);

                                final JLabel seatsLabel = new JLabel("Number of Seats: ");
                                constraints.gridx = 0;
                                constraints.gridy = 6;
                                inputPanel.add(seatsLabel, constraints);

                                final JTextField seatsTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(seatsTextField, constraints);

                                final JLabel floorsLabel = new JLabel("Number of Floors: ");
                                constraints.gridx = 0;
                                constraints.gridy = 7;
                                inputPanel.add(floorsLabel, constraints);

                                final JTextField floorsTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(floorsTextField, constraints);

                                final JButton cancelButton1 = new JButton("Cancel");
                                constraints.gridx = 0;
                                constraints.gridy = 8;
                                inputPanel.add(cancelButton1, constraints);

                                final JButton submitButton1 = new JButton("Submit");
                                constraints.gridx = 1;
                                inputPanel.add(submitButton1, constraints);

                                inputWindow.add(inputPanel);
                                inputWindow.setSize(400, 300);
                                inputWindow.setVisible(true);

                                submitButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String[] out = {makeTextField.getText(), modelTextField.getText(), yearTextField.getText(), mpgTextField.getText(),
                                                colorTextField.getText(), engineTextField.getText(), seatsTextField.getText(), floorsTextField.getText()};

                                        for (String temp : out) {
                                            if (temp.length() == 0) {
                                                JOptionPane temp1 = new JOptionPane();
                                                temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                        }
                                        try {
                                            double tempMPG = Double.parseDouble(out[3]);
                                            int tempYear = Integer.parseInt(out[2]);
                                            int tempSeats = Integer.parseInt(out[6]);
                                            int tempFloors = Integer.parseInt(out[7]);
                                            Bus tempBus = new Bus(out[0], out[1], tempYear, tempMPG, out[4], out[5], tempSeats, tempFloors);
                                            tempBus.addData();
                                            inputWindow.dispose();
                                        } catch (Exception exception) {
                                            JOptionPane temp1 = new JOptionPane();
                                            temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });

                                cancelButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("Bus addition cancelled");
                                        inputWindow.dispose();
                                    }
                                });
                            }
                        }
                );
                motorcycleButton.addActionListener(f ->
                        {
                            buttonWindow.dispose();
                            final JFrame inputWindow = new JFrame("Add a Motorcycle");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel makeLabel = new JLabel("Make: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(makeLabel, constraints);

                            final JTextField makeTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(makeTextField, constraints);

                            final JLabel modelLabel = new JLabel("Model: ");
                            constraints.gridx = 0;
                            constraints.gridy = 1;
                            inputPanel.add(modelLabel, constraints);

                            final JTextField modelTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(modelTextField, constraints);

                            final JLabel yearLabel = new JLabel("Year: ");
                            constraints.gridx = 0;
                            inputPanel.add(yearLabel, constraints);

                            final JTextField yearTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(yearTextField, constraints);

                            final JLabel mpgLabel = new JLabel("MPG: ");
                            constraints.gridx = 0;
                            constraints.gridy = 3;
                            inputPanel.add(mpgLabel, constraints);

                            final JTextField mpgTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(mpgTextField, constraints);

                            final JLabel colorLabel = new JLabel("Color: ");
                            constraints.gridx = 0;
                            constraints.gridy = 4;
                            inputPanel.add(colorLabel, constraints);

                            final JTextField colorTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(colorTextField, constraints);

                            final JLabel engineLabel = new JLabel("Engine Type: ");
                            constraints.gridx = 0;
                            constraints.gridy = 5;
                            inputPanel.add(engineLabel, constraints);

                            final JTextField engineTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(engineTextField, constraints);

                            final JButton cancelButton1 = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancelButton1, constraints);

                            final JButton submitButton1 = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton1, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton1.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String[] out = {makeTextField.getText(), modelTextField.getText(), yearTextField.getText(), mpgTextField.getText(),
                                            colorTextField.getText(), engineTextField.getText()};

                                    for (String temp : out) {
                                        if (temp.length() == 0) {
                                            JOptionPane temp1 = new JOptionPane();
                                            temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                            return;
                                        }
                                    }
                                    try {
                                        double tempMPG = Double.parseDouble(out[3]);
                                        int tempYear = Integer.parseInt(out[2]);
                                        Motorcycle tempCycle = new Motorcycle(out[0], out[1], tempYear, tempMPG, out[4], out[5]);
                                        tempCycle.addData();
                                        inputWindow.dispose();
                                    } catch (Exception exception) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            });

                            cancelButton1.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Motorcycle addition cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                sedanButton.addActionListener(f ->
                        {
                            {
                                buttonWindow.dispose();
                                final JFrame inputWindow = new JFrame("Add a Sedan");
                                JPanel inputPanel = new JPanel(new GridBagLayout());
                                constraints.fill = GridBagConstraints.HORIZONTAL;
                                constraints.anchor = GridBagConstraints.WEST;
                                constraints.insets = new Insets(5, 5, 5, 5);

                                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                                final JLabel makeLabel = new JLabel("Make: ");
                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                inputPanel.add(makeLabel, constraints);

                                final JTextField makeTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(makeTextField, constraints);

                                final JLabel modelLabel = new JLabel("Model: ");
                                constraints.gridx = 0;
                                constraints.gridy = 1;
                                inputPanel.add(modelLabel, constraints);

                                final JTextField modelTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(modelTextField, constraints);

                                final JLabel yearLabel = new JLabel("Year: ");
                                constraints.gridx = 0;
                                constraints.gridy = 2;
                                inputPanel.add(yearLabel, constraints);

                                final JTextField yearTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(yearTextField, constraints);

                                final JLabel mpgLabel = new JLabel("MPG: ");
                                constraints.gridx = 0;
                                constraints.gridy = 3;
                                inputPanel.add(mpgLabel, constraints);

                                final JTextField mpgTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(mpgTextField, constraints);

                                final JLabel colorLabel = new JLabel("Color: ");
                                constraints.gridx = 0;
                                constraints.gridy = 4;
                                inputPanel.add(colorLabel, constraints);

                                final JTextField colorTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(colorTextField, constraints);

                                final JLabel engineLabel = new JLabel("Engine Type: ");
                                constraints.gridx = 0;
                                constraints.gridy = 5;
                                inputPanel.add(engineLabel, constraints);

                                final JTextField engineTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(engineTextField, constraints);

                                final JLabel seatsLabel = new JLabel("Number of Seats: ");
                                constraints.gridx = 0;
                                constraints.gridy = 6;
                                inputPanel.add(seatsLabel, constraints);

                                final JTextField seatsTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(seatsTextField, constraints);

                                final JButton cancelButton1 = new JButton("Cancel");
                                constraints.gridx = 0;
                                constraints.gridy = 7;
                                inputPanel.add(cancelButton1, constraints);

                                final JButton submitButton1 = new JButton("Submit");
                                constraints.gridx = 1;
                                inputPanel.add(submitButton1, constraints);

                                inputWindow.add(inputPanel);
                                inputWindow.setSize(400, 300);
                                inputWindow.setVisible(true);

                                submitButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String[] out = {makeTextField.getText(), modelTextField.getText(), yearTextField.getText(), mpgTextField.getText(),
                                                colorTextField.getText(), engineTextField.getText(), seatsTextField.getText()};

                                        for (String temp : out) {
                                            if (temp.length() == 0) {
                                                JOptionPane temp1 = new JOptionPane();
                                                temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                        }
                                        try {
                                            double tempMPG = Double.parseDouble(out[3]);
                                            int tempYear = Integer.parseInt(out[2]);
                                            int tempSeats = Integer.parseInt(out[6]);
                                            Sedan tempSedan = new Sedan(out[0], out[1], tempYear, tempMPG, out[4], out[5], tempSeats);
                                            tempSedan.addData();
                                            inputWindow.dispose();
                                        } catch (Exception exception) {
                                            JOptionPane temp1 = new JOptionPane();
                                            temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });

                                cancelButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("Sedan addition cancelled");
                                        inputWindow.dispose();
                                    }
                                });
                            }
                        }
                );
                suvButton.addActionListener(f ->
                        {
                            {
                                buttonWindow.dispose();
                                final JFrame inputWindow = new JFrame("Add a SUV");
                                JPanel inputPanel = new JPanel(new GridBagLayout());
                                constraints.fill = GridBagConstraints.HORIZONTAL;
                                constraints.anchor = GridBagConstraints.WEST;
                                constraints.insets = new Insets(5, 5, 5, 5);

                                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                                final JLabel makeLabel = new JLabel("Make: ");
                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                inputPanel.add(makeLabel, constraints);

                                final JTextField makeTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(makeTextField, constraints);

                                final JLabel modelLabel = new JLabel("Model: ");
                                constraints.gridx = 0;
                                constraints.gridy = 1;
                                inputPanel.add(modelLabel, constraints);

                                final JTextField modelTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(modelTextField, constraints);

                                final JLabel yearLabel = new JLabel("Year: ");
                                constraints.gridx = 0;
                                constraints.gridy = 2;
                                inputPanel.add(yearLabel, constraints);

                                final JTextField yearTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(yearTextField, constraints);

                                final JLabel mpgLabel = new JLabel("MPG: ");
                                constraints.gridx = 0;
                                constraints.gridy = 3;
                                inputPanel.add(mpgLabel, constraints);

                                final JTextField mpgTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(mpgTextField, constraints);

                                final JLabel colorLabel = new JLabel("Color: ");
                                constraints.gridx = 0;
                                constraints.gridy = 4;
                                inputPanel.add(colorLabel, constraints);

                                final JTextField colorTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(colorTextField, constraints);

                                final JLabel engineLabel = new JLabel("Engine Type: ");
                                constraints.gridx = 0;
                                constraints.gridy = 5;
                                inputPanel.add(engineLabel, constraints);

                                final JTextField engineTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(engineTextField, constraints);

                                final JLabel seatsLabel = new JLabel("Number of Seats: ");
                                constraints.gridx = 0;
                                constraints.gridy = 6;
                                inputPanel.add(seatsLabel, constraints);

                                final JTextField seatsTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(seatsTextField, constraints);

                                final JButton cancelButton1 = new JButton("Cancel");
                                constraints.gridx = 0;
                                constraints.gridy = 7;
                                inputPanel.add(cancelButton1, constraints);

                                final JButton submitButton1 = new JButton("Submit");
                                constraints.gridx = 1;
                                inputPanel.add(submitButton1, constraints);

                                inputWindow.add(inputPanel);
                                inputWindow.setSize(400, 300);
                                inputWindow.setVisible(true);

                                submitButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String[] out = {makeTextField.getText(), modelTextField.getText(), yearTextField.getText(), mpgTextField.getText(),
                                                colorTextField.getText(), engineTextField.getText(), seatsTextField.getText()};

                                        for (String temp : out) {
                                            if (temp.length() == 0) {
                                                JOptionPane temp1 = new JOptionPane();
                                                temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                        }
                                        try {
                                            double tempMPG = Double.parseDouble(out[3]);
                                            int tempYear = Integer.parseInt(out[2]);
                                            int tempSeats = Integer.parseInt(out[6]);
                                            SUV tempSuv = new SUV(out[0], out[1], tempYear, tempMPG, out[4], out[5], tempSeats);
                                            tempSuv.addData();
                                            inputWindow.dispose();
                                        } catch (Exception exception) {
                                            JOptionPane temp1 = new JOptionPane();
                                            temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });

                                cancelButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("SUV addition cancelled");
                                        inputWindow.dispose();
                                    }
                                });
                            }
                        }
                );
                truckButton.addActionListener(f ->
                        {
                            {
                                buttonWindow.dispose();
                                final JFrame inputWindow = new JFrame("Add a Truck");
                                JPanel inputPanel = new JPanel(new GridBagLayout());
                                constraints.fill = GridBagConstraints.HORIZONTAL;
                                constraints.anchor = GridBagConstraints.WEST;
                                constraints.insets = new Insets(5, 5, 5, 5);

                                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                                final JLabel makeLabel = new JLabel("Make: ");
                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                inputPanel.add(makeLabel, constraints);

                                final JTextField makeTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(makeTextField, constraints);

                                final JLabel modelLabel = new JLabel("Model: ");
                                constraints.gridx = 0;
                                constraints.gridy = 1;
                                inputPanel.add(modelLabel, constraints);

                                final JTextField modelTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(modelTextField, constraints);

                                final JLabel yearLabel = new JLabel("Year: ");
                                constraints.gridx = 0;
                                constraints.gridy = 2;
                                inputPanel.add(yearLabel, constraints);

                                final JTextField yearTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(yearTextField, constraints);

                                final JLabel mpgLabel = new JLabel("MPG: ");
                                constraints.gridx = 0;
                                constraints.gridy = 3;
                                inputPanel.add(mpgLabel, constraints);

                                final JTextField mpgTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(mpgTextField, constraints);

                                final JLabel colorLabel = new JLabel("Color: ");
                                constraints.gridx = 0;
                                constraints.gridy = 4;
                                inputPanel.add(colorLabel, constraints);

                                final JTextField colorTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(colorTextField, constraints);

                                final JLabel engineLabel = new JLabel("Engine Type: ");
                                constraints.gridx = 0;
                                constraints.gridy = 5;
                                inputPanel.add(engineLabel, constraints);

                                final JTextField engineTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(engineTextField, constraints);

                                final JLabel typeLabel = new JLabel("EngineType (1,2,3): ");
                                constraints.gridx = 0;
                                constraints.gridy = 6;
                                inputPanel.add(typeLabel, constraints);

                                final JTextField typeTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(typeTextField, constraints);

                                final JLabel loadLabel = new JLabel("Number of Floors: ");
                                constraints.gridx = 0;
                                constraints.gridy = 7;
                                inputPanel.add(loadLabel, constraints);

                                final JTextField loadTextField = new JTextField(20);
                                constraints.gridx = 1;
                                inputPanel.add(loadTextField, constraints);

                                final JButton cancelButton1 = new JButton("Cancel");
                                constraints.gridx = 0;
                                constraints.gridy = 8;
                                inputPanel.add(cancelButton1, constraints);

                                final JButton submitButton1 = new JButton("Submit");
                                constraints.gridx = 1;
                                inputPanel.add(submitButton1, constraints);

                                inputWindow.add(inputPanel);
                                inputWindow.setSize(400, 300);
                                inputWindow.setVisible(true);

                                submitButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String[] out = {makeTextField.getText(), modelTextField.getText(), yearTextField.getText(), mpgTextField.getText(),
                                                colorTextField.getText(), engineTextField.getText(), typeTextField.getText(), loadTextField.getText()};

                                        for (String temp : out) {
                                            if (temp.length() == 0) {
                                                JOptionPane temp1 = new JOptionPane();
                                                temp1.showMessageDialog(null, "Error: Not all of the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            }
                                        }
                                        try {
                                            double tempMPG = Double.parseDouble(out[3]);
                                            int tempYear = Integer.parseInt(out[2]);
                                            int tempType = Integer.parseInt(out[6]);
                                            int tempLoad = Integer.parseInt(out[7]);
                                            Truck tempTruck = new Truck(out[0], out[1], tempYear, tempMPG, out[4], out[5], tempType, tempLoad);
                                            tempTruck.addData();
                                            inputWindow.dispose();
                                        } catch (Exception exception) {
                                            JOptionPane temp1 = new JOptionPane();
                                            temp1.showMessageDialog(null, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });

                                cancelButton1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("Truck addition cancelled");
                                        inputWindow.dispose();
                                    }
                                });
                            }
                        }
                );
                cancelButton.addActionListener(f -> buttonWindow.dispose());

                buttonWindow.add(panel);
                buttonWindow.setSize(420, 420 - 69 / 2);
                buttonWindow.setVisible(true);
                break;
            }
            case "Rent vehicle": {
                JFrame buttonWindow = new JFrame("Rent a Vehicle");
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);
                buttonWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                constraints.weightx = 0.5;
                constraints.weighty = 0.5;


                final JButton busButton = new JButton("Rent a Bus");
                Dimension buttonSize = new Dimension(busButton.getPreferredSize().width, busButton.getPreferredSize().height * 4);
                busButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(busButton, constraints);

                final JButton motorcycleButton = new JButton("Rent a Motorcycle");
                motorcycleButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(motorcycleButton, constraints);

                final JButton sedanButton = new JButton("Rent a Sedan");
                sedanButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 1;
                panel.add(sedanButton, constraints);

                final JButton suvButton = new JButton("Rent a SUV");
                suvButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(suvButton, constraints);

                final JButton truckButton = new JButton("Rent a Truck");
                truckButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 2;
                panel.add(truckButton, constraints);

                final JButton cancelButton = new JButton("Cancel");

                buttonSize = new Dimension(cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height * 3);
                cancelButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(cancelButton, constraints);
                busButton.addActionListener(f ->
                        {
                            {
                                String[] options = Bus.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one bus to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Bus.rentV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                motorcycleButton.addActionListener(f ->
                        {
                            {
                                String[] options = Motorcycle.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one motorcycle to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Motorcycle.rentV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                sedanButton.addActionListener(f ->
                        {
                            {
                                String[] options = Sedan.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one sedan to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Sedan.rentV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                suvButton.addActionListener(f ->
                        {
                            {
                                String[] options = SUV.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one SUV to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        SUV.rentV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                truckButton.addActionListener(f ->
                        {
                            {
                                String[] options = Truck.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one truck to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Truck.rentV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                cancelButton.addActionListener(f -> buttonWindow.dispose());

                buttonWindow.add(panel);
                buttonWindow.setSize(420, 420 - 69 / 2);
                buttonWindow.setVisible(true);
                break;
            }
            case "Return vehicle": {
                JFrame buttonWindow = new JFrame("Rent a Vehicle");
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);
                buttonWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                constraints.weightx = 0.5;
                constraints.weighty = 0.5;


                final JButton busButton = new JButton("Rent a Bus");
                Dimension buttonSize = new Dimension(busButton.getPreferredSize().width, busButton.getPreferredSize().height * 4);
                busButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(busButton, constraints);

                final JButton motorcycleButton = new JButton("Rent a Motorcycle");
                motorcycleButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(motorcycleButton, constraints);

                final JButton sedanButton = new JButton("Rent a Sedan");
                sedanButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 1;
                panel.add(sedanButton, constraints);

                final JButton suvButton = new JButton("Rent a SUV");
                suvButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(suvButton, constraints);

                final JButton truckButton = new JButton("Rent a Truck");
                truckButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 2;
                panel.add(truckButton, constraints);

                final JButton cancelButton = new JButton("Cancel");

                buttonSize = new Dimension(cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height * 3);
                cancelButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(cancelButton, constraints);
                busButton.addActionListener(f ->
                        {
                            {
                                String[] options = Bus.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one bus to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Bus.returnV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                motorcycleButton.addActionListener(f ->
                        {
                            {
                                String[] options = Motorcycle.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one motorcycle to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Motorcycle.returnV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                sedanButton.addActionListener(f ->
                        {
                            {
                                String[] options = Sedan.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one sedan to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Sedan.returnV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                suvButton.addActionListener(f ->
                        {
                            {
                                String[] options = SUV.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one SUV to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        SUV.returnV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                truckButton.addActionListener(f ->
                        {
                            {
                                String[] options = Truck.read();
                                if (options.length == 0) {
                                    JOptionPane temp1 = new JOptionPane();
                                    temp1.showMessageDialog(null, "Error: You need at least one truck to rent one out", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    final JFrame frame = new JFrame("Selector");
                                    JPanel optionPanel = new JPanel(new GridBagLayout());
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.anchor = GridBagConstraints.WEST;
                                    constraints.insets = new Insets(5, 5, 5, 5);

                                    JLabel label = new JLabel("Select an option:");
                                    constraints.gridx = 0;
                                    constraints.gridy = 0;
                                    optionPanel.add(label, constraints);

                                    JComboBox<String> comboBox = new JComboBox<>(options);
                                    comboBox.setPreferredSize(new Dimension(300, 20)); // set the size of the JComboBox
                                    constraints.gridx = 1;
                                    optionPanel.add(comboBox, constraints);

                                    JButton cancelButton1 = new JButton("Cancel");
                                    constraints.gridx = 0;
                                    constraints.gridy = 1;
                                    optionPanel.add(cancelButton1, constraints);
                                    cancelButton1.addActionListener(g -> frame.dispose());

                                    JButton okButton = new JButton("OK");
                                    constraints.gridx = 1;
                                    optionPanel.add(okButton, constraints);

                                    okButton.addActionListener(g -> {
                                        String selectedOption = (String) comboBox.getSelectedItem();
                                        System.out.println("You selected: " + selectedOption);
                                        Truck.returnV(selectedOption);
                                        frame.dispose();
                                    });

                                    frame.add(optionPanel);
                                    frame.setSize(420, 120);
                                    frame.setLocationRelativeTo(null);
                                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    frame.setVisible(true);
                                }
                            }
                        }
                );
                cancelButton.addActionListener(f -> buttonWindow.dispose());

                buttonWindow.add(panel);
                buttonWindow.setSize(420, 420 - 69 / 2);
                buttonWindow.setVisible(true);
                break;
            }
            case "Search for a customer": {
                final JFrame inputWindow = new JFrame("Search for Customer");
                JPanel inputPanel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);

                //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                final JLabel nameLabel = new JLabel("Search: ");
                constraints.gridx = 0;
                constraints.gridy = 0;
                inputPanel.add(nameLabel, constraints);

                final JTextField nameTextField = new JTextField(20);
                constraints.gridx = 1;
                inputPanel.add(nameTextField, constraints);

                final JButton cancelButton = new JButton("Cancel");
                constraints.gridx = 0;
                constraints.gridy = 6;
                inputPanel.add(cancelButton, constraints);

                final JButton submitButton = new JButton("Submit");
                constraints.gridx = 1;
                inputPanel.add(submitButton, constraints);

                inputWindow.add(inputPanel);
                inputWindow.setSize(400, 300);
                inputWindow.setVisible(true);

                submitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String query = nameTextField.getText();
                        String result = searchForString(query, "Customer.txt");
                        if (result == null) {
                            JOptionPane temp1 = new JOptionPane();
                            temp1.showMessageDialog(null, "Error: No Customer was found.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            final JFrame frame = new JFrame("Search results:");
                            JPanel panel = new JPanel(new GridBagLayout());
                            GridBagConstraints constraints = new GridBagConstraints();
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            final JLabel nameLabel = new JLabel(query);
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            panel.add(frame, constraints);
                            frame.pack();
                            frame.setVisible(true);
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Customer search cancelled");
                        inputWindow.dispose();
                    }
                });
                break;
            }
            case "Search for a vehicle": {
                JFrame buttonWindow = new JFrame("Search for a Vehicle");
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(5, 5, 5, 5);
                buttonWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                constraints.weightx = 0.5;
                constraints.weighty = 0.5;


                final JButton busButton = new JButton("Rent a Bus");
                Dimension buttonSize = new Dimension(busButton.getPreferredSize().width, busButton.getPreferredSize().height * 4);
                busButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(busButton, constraints);

                final JButton motorcycleButton = new JButton("Rent a Motorcycle");
                motorcycleButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(motorcycleButton, constraints);

                final JButton sedanButton = new JButton("Rent a Sedan");
                sedanButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 1;
                panel.add(sedanButton, constraints);

                final JButton suvButton = new JButton("Rent a SUV");
                suvButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(suvButton, constraints);

                final JButton truckButton = new JButton("Rent a Truck");
                truckButton.setPreferredSize(buttonSize);
                constraints.gridx = 0;
                constraints.gridy = 2;
                panel.add(truckButton, constraints);

                final JButton cancelButton = new JButton("Cancel");

                buttonSize = new Dimension(cancelButton.getPreferredSize().width, cancelButton.getPreferredSize().height * 3);
                cancelButton.setPreferredSize(buttonSize);
                constraints.gridx = 1;
                panel.add(cancelButton, constraints);
                busButton.addActionListener(f ->
                        {
                            final JFrame inputWindow = new JFrame("Search for Bus");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel nameLabel = new JLabel("Search: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(nameLabel, constraints);

                            final JTextField nameTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(nameTextField, constraints);

                            final JButton cancellButton = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancellButton, constraints);

                            final JButton submitButton = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String query = nameTextField.getText();
                                    combineTextFiles("Bus.txt", "RBus.txt");
                                    String result = searchForString(query, "combined.txt");
                                    if (result == null) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: No Bus was found based on your query.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        final JFrame frame = new JFrame("Search results:");
                                        JPanel panel = new JPanel(new GridBagLayout());
                                        GridBagConstraints constraints = new GridBagConstraints();
                                        constraints.fill = GridBagConstraints.HORIZONTAL;
                                        constraints.anchor = GridBagConstraints.WEST;
                                        constraints.insets = new Insets(5, 5, 5, 5);

                                        final JLabel nameLabel = new JLabel(query);
                                        constraints.gridx = 0;
                                        constraints.gridy = 0;
                                        panel.add(nameLabel, constraints);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }
                                    deleteCombined();
                                }
                            });

                            cancellButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Bus search cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                motorcycleButton.addActionListener(f ->
                        {
                            final JFrame inputWindow = new JFrame("Search for Motorcycle");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel nameLabel = new JLabel("Search: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(nameLabel, constraints);

                            final JTextField nameTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(nameTextField, constraints);

                            final JButton cancellButton = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancellButton, constraints);

                            final JButton submitButton = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String query = nameTextField.getText();
                                    combineTextFiles("Motorcycle.txt", "RMotorcycle.txt");
                                    String result = searchForString(query, "combined.txt");
                                    if (result == null) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: No Motorcycle was found based on your query.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        final JFrame frame = new JFrame("Search results:");
                                        JPanel panel = new JPanel(new GridBagLayout());
                                        GridBagConstraints constraints = new GridBagConstraints();
                                        constraints.fill = GridBagConstraints.HORIZONTAL;
                                        constraints.anchor = GridBagConstraints.WEST;
                                        constraints.insets = new Insets(5, 5, 5, 5);

                                        final JLabel nameLabel = new JLabel(query);
                                        constraints.gridx = 0;
                                        constraints.gridy = 0;
                                        panel.add(nameLabel, constraints);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }
                                    deleteCombined();
                                }
                            });

                            cancellButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Motorcycle search cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                sedanButton.addActionListener(f ->
                        {
                            final JFrame inputWindow = new JFrame("Search for Sedan");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel nameLabel = new JLabel("Search: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(nameLabel, constraints);

                            final JTextField nameTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(nameTextField, constraints);

                            final JButton cancellButton = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancellButton, constraints);

                            final JButton submitButton = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String query = nameTextField.getText();
                                    combineTextFiles("Sedan.txt", "RSedan.txt");
                                    String result = searchForString(query, "combined.txt");
                                    if (result == null) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: No Sedan was found based on your query.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        final JFrame frame = new JFrame("Search results:");
                                        JPanel panel = new JPanel(new GridBagLayout());
                                        GridBagConstraints constraints = new GridBagConstraints();
                                        constraints.fill = GridBagConstraints.HORIZONTAL;
                                        constraints.anchor = GridBagConstraints.WEST;
                                        constraints.insets = new Insets(5, 5, 5, 5);

                                        final JLabel nameLabel = new JLabel(query);
                                        constraints.gridx = 0;
                                        constraints.gridy = 0;
                                        panel.add(nameLabel, constraints);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }
                                    deleteCombined();
                                }
                            });

                            cancellButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Sedan search cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                suvButton.addActionListener(f ->
                        {
                            final JFrame inputWindow = new JFrame("Search for SUV");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel nameLabel = new JLabel("Search: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(nameLabel, constraints);

                            final JTextField nameTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(nameTextField, constraints);

                            final JButton cancellButton = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancellButton, constraints);

                            final JButton submitButton = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String query = nameTextField.getText();
                                    combineTextFiles("SUV.txt", "RSUV.txt");
                                    String result = searchForString(query, "combined.txt");
                                    if (result == null) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: No SUV was found based on your query.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        final JFrame frame = new JFrame("Search results:");
                                        JPanel panel = new JPanel(new GridBagLayout());
                                        GridBagConstraints constraints = new GridBagConstraints();
                                        constraints.fill = GridBagConstraints.HORIZONTAL;
                                        constraints.anchor = GridBagConstraints.WEST;
                                        constraints.insets = new Insets(5, 5, 5, 5);

                                        final JLabel nameLabel = new JLabel(query);
                                        constraints.gridx = 0;
                                        constraints.gridy = 0;
                                        panel.add(nameLabel, constraints);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }
                                    deleteCombined();
                                }
                            });

                            cancellButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("SUV search cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                truckButton.addActionListener(f ->
                        {
                            final JFrame inputWindow = new JFrame("Search for Truck");
                            JPanel inputPanel = new JPanel(new GridBagLayout());
                            constraints.fill = GridBagConstraints.HORIZONTAL;
                            constraints.anchor = GridBagConstraints.WEST;
                            constraints.insets = new Insets(5, 5, 5, 5);

                            //Might be a faster or smaller way to do this with a loop, but I like ctrl+c/ctrl+v
                            final JLabel nameLabel = new JLabel("Search: ");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            inputPanel.add(nameLabel, constraints);

                            final JTextField nameTextField = new JTextField(20);
                            constraints.gridx = 1;
                            inputPanel.add(nameTextField, constraints);

                            final JButton cancellButton = new JButton("Cancel");
                            constraints.gridx = 0;
                            constraints.gridy = 6;
                            inputPanel.add(cancellButton, constraints);

                            final JButton submitButton = new JButton("Submit");
                            constraints.gridx = 1;
                            inputPanel.add(submitButton, constraints);

                            inputWindow.add(inputPanel);
                            inputWindow.setSize(400, 300);
                            inputWindow.setVisible(true);

                            submitButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String query = nameTextField.getText();
                                    combineTextFiles("Truck.txt", "RTruck.txt");
                                    String result = searchForString(query, "combined.txt");
                                    if (result == null) {
                                        JOptionPane temp1 = new JOptionPane();
                                        temp1.showMessageDialog(null, "Error: No Truck was found based on your query.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        final JFrame frame = new JFrame("Search results:");
                                        JPanel panel = new JPanel(new GridBagLayout());
                                        GridBagConstraints constraints = new GridBagConstraints();
                                        constraints.fill = GridBagConstraints.HORIZONTAL;
                                        constraints.anchor = GridBagConstraints.WEST;
                                        constraints.insets = new Insets(5, 5, 5, 5);

                                        final JLabel nameLabel = new JLabel(query);
                                        constraints.gridx = 0;
                                        constraints.gridy = 0;
                                        panel.add(nameLabel, constraints);
                                        frame.add(panel);
                                        frame.pack();
                                        frame.setVisible(true);
                                    }
                                    deleteCombined();
                                }
                            });

                            cancellButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println("Customer Addition cancelled");
                                    inputWindow.dispose();
                                }
                            });
                        }
                );
                cancelButton.addActionListener(f -> buttonWindow.dispose());

                buttonWindow.add(panel);
                buttonWindow.setSize(420, 380);
                buttonWindow.setVisible(true);
                break;
            }
            case "Generate a report for available vehicles": {
                combineTextFiles("Bus.txt", "Motorcycle.txt", "Sedan.txt", "SUV.txt", "Truck.txt");
                try {
                    JFrame frame = new JFrame("Text File Viewer");
                    JTextArea textArea = new JTextArea();
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(scrollPane, BorderLayout.CENTER);
                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(600, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"));
                    String line = reader.readLine();
                    while (line != null) {
                        textArea.append(line + "\n");
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException exc) {
                    System.out.println("An error occurred: " + exc.getMessage());
                    exc.printStackTrace();
                }
                deleteCombined();
                break;
            }
            case "Generate a report for rented vehicles": {
                combineTextFiles("RBus.txt", "RMotorcycle.txt", "RSedan.txt", "RSUV.txt", "RTruck.txt");
                try {
                    JFrame frame = new JFrame("Text File Viewer");
                    JTextArea textArea = new JTextArea();
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(scrollPane, BorderLayout.CENTER);
                    frame.setContentPane(panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(600, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"));
                    String line = reader.readLine();
                    while (line != null) {
                        textArea.append(line + "\n");
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException exc) {
                    System.out.println("An error occurred: " + exc.getMessage());
                    exc.printStackTrace();
                }
                deleteCombined();
                break;
            }
        }
    }
}
