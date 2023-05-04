package com.vrs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private JButton button1, button2, button3, button4, button5, button6, button7, button8;

    public GUI() {
        // Window name
        setTitle("VRS");
        setSize(2 * 690, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button names
        button1 = new JButton("Add customer");
        button2 = new JButton("Add vehicle");
        button3 = new JButton("Rent vehicle");
        button4 = new JButton("Return vehicle");
        button5 = new JButton("Search for a customer");
        button6 = new JButton("Search for a vehicle");
        button7 = new JButton("Generate a report for available vehicles");
        button8 = new JButton("Generate a report for rented vehicles");

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
        if (buttonName.equals("Add customer")) {
            final JFrame inputWindow = new JFrame("Add Customer");
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(5, 5, 5, 5);

            // Might be a faster or smaller way to do this with a loop but I like
            // ctrl+c/ctrl+v
            final JLabel nameLabel = new JLabel("Name: ");
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(nameLabel, constraints);

            final JTextField nameTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 0;
            panel.add(nameTextField, constraints);

            final JLabel houseNumLabel = new JLabel("House Number: ");
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(houseNumLabel, constraints);

            final JTextField houseNumTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            panel.add(houseNumTextField, constraints);

            final JLabel streetNameLabel = new JLabel("Street Name: ");
            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(streetNameLabel, constraints);

            final JTextField streetNameTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panel.add(streetNameTextField, constraints);

            final JLabel cityLabel = new JLabel("City: ");
            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(cityLabel, constraints);

            final JTextField cityTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 3;
            panel.add(cityTextField, constraints);

            final JLabel stateLabel = new JLabel("State: ");
            constraints.gridx = 0;
            constraints.gridy = 4;
            panel.add(stateLabel, constraints);

            final JTextField stateTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 4;
            panel.add(stateTextField, constraints);

            final JLabel dobLabel = new JLabel("Date of Birth [mm/dd/yyyy]: ");
            constraints.gridx = 0;
            constraints.gridy = 5;
            panel.add(dobLabel, constraints);

            final JTextField dobTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 5;
            panel.add(dobTextField, constraints);

            final JButton submitButton = new JButton("Submit");
            constraints.gridx = 1;
            constraints.gridy = 6;
            panel.add(submitButton, constraints);

            final JButton cancelButton = new JButton("Cancel");
            constraints.gridx = 0;
            constraints.gridy = 6;
            panel.add(cancelButton, constraints);

            inputWindow.add(panel);
            inputWindow.setSize(400, 300);
            inputWindow.setVisible(true);

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    int houseNum = Integer.parseInt(houseNumTextField.getText());
                    String streetName = streetNameTextField.getText();
                    String city = cityTextField.getText();
                    String state = stateTextField.getText();
                    String[] dob = dobTextField.getText().split("/");

                    int[] dobNums = new int[3];
                    for (int i = 0; i < dobNums.length; i++) {
                        dobNums[i] = Integer.parseInt(dob[i]);
                    }
                    Address tempAddress = new Address(houseNum, streetName, city, state);
                    Date tempDate = new Date(dobNums[1], dobNums[0], dobNums[2]);
                    Customer tempCustomer = new Customer(name, tempDate, tempAddress);
                    // Add this to mySQL somehow
                    System.out.println(tempCustomer + " was added to the database.");

                    inputWindow.dispose();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Customer Addition cancelled");
                    inputWindow.dispose();
                }
            });
        } else if (buttonName.equals("Add vehicle")) {
            final JFrame inputWindow = new JFrame("Add Vehicle");
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(5, 5, 5, 5);

            // Might be a faster or smaller way to do this with a loop, but I like
            // ctrl+c/ctrl+v
            final JLabel nameLabel = new JLabel("Name: ");
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(nameLabel, constraints);

            final JTextField nameTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 0;
            panel.add(nameTextField, constraints);

            final JLabel houseNumLabel = new JLabel("House Number: ");
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(houseNumLabel, constraints);

            final JTextField houseNumTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            panel.add(houseNumTextField, constraints);

            final JLabel streetNameLabel = new JLabel("Street Name: ");
            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(streetNameLabel, constraints);

            final JTextField streetNameTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panel.add(streetNameTextField, constraints);

            final JLabel cityLabel = new JLabel("City: ");
            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(cityLabel, constraints);

            final JTextField cityTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 3;
            panel.add(cityTextField, constraints);

            final JLabel stateLabel = new JLabel("State: ");
            constraints.gridx = 0;
            constraints.gridy = 4;
            panel.add(stateLabel, constraints);

            final JTextField stateTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 4;
            panel.add(stateTextField, constraints);

            final JLabel dobLabel = new JLabel("Date of Birth [mm/dd/yyyy]: ");
            constraints.gridx = 0;
            constraints.gridy = 5;
            panel.add(dobLabel, constraints);

            final JTextField dobTextField = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 5;
            panel.add(dobTextField, constraints);

            final JButton submitButton = new JButton("Submit");
            constraints.gridx = 1;
            constraints.gridy = 6;
            panel.add(submitButton, constraints);

            final JButton cancelButton = new JButton("Cancel");
            constraints.gridx = 0;
            constraints.gridy = 6;
            panel.add(cancelButton, constraints);

            inputWindow.add(panel);
            inputWindow.setSize(400, 300);
            inputWindow.setVisible(true);

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    int houseNum = Integer.parseInt(houseNumTextField.getText());
                    String streetName = streetNameTextField.getText();
                    String city = cityTextField.getText();
                    String state = stateTextField.getText();
                    String[] dob = dobTextField.getText().split("/");

                    int[] dobNums = new int[3];
                    for (int i = 0; i < dobNums.length; i++) {
                        dobNums[i] = Integer.parseInt(dob[i]);
                    }
                    Address tempAddress = new Address(houseNum, streetName, city, state);
                    Date tempDate = new Date(dobNums[1], dobNums[0], dobNums[2]);
                    Customer tempCustomer = new Customer(name, tempDate, tempAddress);
                    // Add this to mySQL somehow
                    System.out.println(tempCustomer + "was added to the database.");
                    // This will only show for console
                    inputWindow.dispose();
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    inputWindow.dispose();
                }
            });
        } else if (buttonName.equals("Rent vehicle")) {
            // .setText("You clicked button 3");
        } else if (buttonName.equals("Return vehicle")) {
            // messageLabel.setText("You clicked button 4");
        } else if (buttonName.equals("Search for a customer")) {
            // messageLabel.setText("You clicked button 5");
        } else if (buttonName.equals("Search for a vehicle")) {
            // messageLabel.setText("You clicked button 6");
        } else if (buttonName.equals("Generate a report for available vehicles")) {
            // messageLabel.setText("You clicked button 7");
        } else if (buttonName.equals("Generate a report for rented vehicles")) {
            // messageLabel.setText("You clicked button 8");
        }
    }

    public static void main(String[] args) {
    }
}
