package com.company;

import com.company.model.BasePerson;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PersonDetailsView extends JPanel {

    private BasePerson person;

    public PersonDetailsView(BasePerson person) {
        super();
        this.person = person;

        JLabel nameLabel = new JLabel("First Name:\n" + person.getName());
        JLabel ageLabel = new JLabel("Age:\n" + person.getAge());
        JLabel statusLabel = new JLabel("Status:\n" + person.getStatus());
        JLabel sexLabel = new JLabel("Sex:\n" + person.getSex());

        String imageUrl = person.getImage() != null ? person.getImage() : "sample_image.jpeg";
        File f = new File(imageUrl);
        JLabel imgLabel = new JLabel(new ImageIcon(f.getName()));
        imgLabel.setSize(200, 200);

        this.add(nameLabel);
        this.add(ageLabel);
        this.add(statusLabel);
        this.add(sexLabel);
        this.add(imgLabel);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

    }

}
