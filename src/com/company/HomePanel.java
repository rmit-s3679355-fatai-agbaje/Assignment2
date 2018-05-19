package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * The custom JPanel that gives a short description of the app on startup
 */
public class HomePanel extends JPanel {

    public HomePanel(){
        JLabel label = new JLabel();
        label.setText("Welcome to My Social Media app. \n Please select a menu from the above");

        label.setSize(this.getSize());

        this.add(label);
    }

}
