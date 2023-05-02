package com.vrs;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
    private JLabel userLbl;
    private JLabel passLbl;
    private JTextField userTxt;
    private JTextField passTxt;
    private JButton loginBtn;

    public LoginFrame() {
        FlowLayout loginLayout = new FlowLayout();
        setLayout(loginLayout);
        userLbl = new JLabel("Username: ");
        add(userLbl);
        userTxt = new JTextField(10);
        add(userTxt);
        passLbl = new JLabel("Password: ");
        add(passLbl);
        passTxt = new JTextField(10);
        add(passTxt);
        loginBtn = new JButton("Login");
    }

}
