package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * The main Application JFrame
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Social Media");
        this.setMenuBar(this.getMenuBar());
    }

    public void run(JPanel panel) {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.setVisible(true);
    }

    public MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Home");

        MenuItem addPersonMenuItem = new MenuItem("Add a new Person");
        addPersonMenuItem.addActionListener(actionEvent -> {

        });

        menu.add(addPersonMenuItem);
        menuBar.add(menu);
        return menuBar;
    }

}
