package com.company;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(){
        JLabel label = new JLabel();
        label.setText("Welcome to My Social Media app");

        label.setSize(this.getSize());

        this.add(label);
    }

}
