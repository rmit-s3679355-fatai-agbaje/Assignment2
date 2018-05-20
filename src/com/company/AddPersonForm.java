package com.company;

import com.company.model.Adult;
import com.company.model.BasePerson;
import com.company.model.YoungChild;

import javax.swing.*;

public class AddPersonForm {

    private Driver driver;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton saveButton;
    private JTextField ageField;
    private JPanel panel;
    private JTextField statusField;
    private JComboBox genderCombox;
    private JTextField stateField;

    public AddPersonForm(Driver driver) {
        this.driver = driver;
        saveButton.addActionListener(actionEvent -> this.addNewPerson());
    }

    private void addNewPerson() {
        try {
            String firstName = this.firstNameField.getText();
            String lastName = this.lastNameField.getText();

            String name = String.format("%s %s", firstName, lastName);

            int age = Integer.parseInt(this.ageField.getText());

            if (age > 2) {
                BasePerson person = new Adult(name, age, null);
                person.setStatus(statusField.getText().toString());
                person.setSex(genderCombox.getSelectedItem().toString());
                person.setStatus(stateField.getText());
                this.driver.addPerson(person);
            }

        }
        catch (Exception ex){
            JOptionPane.showConfirmDialog(this.panel, "An error has occurred");
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
