package com.company;

import com.company.model.BasePerson;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The custom JPanel for that displays all the users in the application
 */
public class ViewAllForm extends JPanel {

    private OnPersonSelectedListener listener;
    private java.util.List<String> itemsString;
    private Driver driver;
    private List list;

    public ViewAllForm(Driver driver) {
        this.driver = driver;
        list = new List();
        itemsString = new ArrayList<>();
        this.list.setSize(this.getSize());

        this.addListContent();

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(boxLayout);
        this.add(list);

        this.list.addItemListener(itemEvent -> {
            int index = (int) itemEvent.getItem();
            if (index > -1 && listener != null) {
                BasePerson selectedPerson = this.driver.getAllPeople().get(index);
                listener.onPersonSelected(selectedPerson);
            }
        });
    }

    /**
     * This is an action listener that is fired when an item is selected from the @java.awt.List
     * @param listener
     */
    public void attachListener(OnPersonSelectedListener listener) {
        this.listener = listener;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    private void addListContent() {
        java.util.List<BasePerson> people = driver.getAllPeople();
        if (people != null && people.size() > 0) {
            list.clear();
            for (BasePerson person : people) {
                String content = person.toString();
                this.itemsString.add(content);
                list.add(content);
            }
        }
    }
}
