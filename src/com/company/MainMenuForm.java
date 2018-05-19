package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A Custom form helper created a GUI Form control with the InteliJ IDEA
 */
public class MainMenuForm {

    private JPanel mainPanel;
    private JLabel homeLabel;
    private JLabel addPersonLabel;
    private JLabel viewAllLabel;
    OnMenuClickListener listener;
    private OnPersonSelectedListener personSelectedListener;

    private Driver driver;

    public MainMenuForm(Driver driver){

        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                listener.onMenuClicked(new HomePanel());
            }
        });
        addPersonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                listener.onMenuClicked(new AddPersonForm(driver).getPanel());
            }
        });
        viewAllLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ViewAllForm form = new ViewAllForm(driver);
                form.attachListener(personSelectedListener);
                listener.onMenuClicked(form);
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void attachListener(OnMenuClickListener listener){
        this.listener = listener;
    }

    public void attachPersonSelectedListener(OnPersonSelectedListener personSelectedListener) {
        this.personSelectedListener = personSelectedListener;
    }
}
