package com.company;

import com.company.model.Adult;
import com.company.model.BasePerson;
import com.company.model.Child;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class MiniNet {

    private static Driver driver;
    private static Scanner input;

    static JPanel currentContentPanel;

    private void processConsole() throws Exception {

        while (true) {
            showOptions();

            int option = input.nextInt();
            if (option == 1) {
                showAll();
            } else if (option == 2) {
                System.out.println("Please provide the name of the person");
                String name = input.next();
                BasePerson person = driver.findPersonByName(name);
                if (person != null) {
                    driver.displayProfile(person);
                } else {
                    System.out.println("No person with the name " + name + " was found");
                }
            } else if (option == 3) {
                System.out.println("Please what is the name of the user that you would like to add");
                String name = input.next();
                System.out.println("Please what is this person's age");
                int age = input.nextInt();
                if (age < 16) {
                    BasePerson person1 = getParentForChild(name, "Please provide the name of the first parent\nPlease enter '.' to abort");
                    if (person1 != null) {
                        BasePerson person2 = getParentForChild(name, "Please provide the name of the second parent\nPlease enter '.' to abort");
                        if (person2 != null) {
                            Child child = new Child(name, age, person1, person2);
                            driver.addPerson(child);
                        }
                    }
                } else {
                    try {
                        Adult adult = new Adult(name, age);
                        driver.addPerson(adult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (option == 4) {
                System.out.println("Please provide the name of the person");
                String name = input.next();
                BasePerson person = driver.findPersonByName(name);
                if (person != null) {
                    System.out.println("Please provide the new status");
                    input.nextLine();
                    String status = input.nextLine();
                    person.setStatus(status);
                    System.out.println("Status updated successfully :)");
                } else {
                    System.out.println("User not found");
                }
            } else if (option == 5) {
                System.out.println("Please provide the name of the person to delete");
                String name = input.next();
                BasePerson person = driver.findPersonByName(name);
                if (person != null) {
                    boolean result = driver.deletePerson(person);
                    if (result) {
                        System.out.println("The person was deleted successfully");
                    } else {
                        System.out.println("User could not be deleted");
                    }
                }
            } else if (option == 6) {
                System.out.println("Please provide the name of the first person");
                String firstName = input.next();
                BasePerson firstPerson = driver.findPersonByName(firstName);
                if (firstPerson != null) {
                    System.out.println("Please provide the name of the second person");
                    String secondName = input.next();
                    BasePerson secondPerson = driver.findPersonByName(secondName);
                    boolean isFriends = driver.areFriend(firstPerson, secondPerson);
                    if (isFriends) {
                        System.out.println(String.format("%s %s %s %s", firstName, "and ", secondName, " are friends"));
                    } else {
                        System.out.println("These two people are not connected");
                    }
                }
            } else if (option == 0) {
                System.out.println("Thank you for using my social network");
                System.exit(0);
            } else {
                System.out.println("Invalid Option. Please select a valid option from 1 - 6");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        input = new Scanner(System.in);
        driver = new Driver();

        currentContentPanel = new HomePanel();

        MainMenuForm menuPanel = new MainMenuForm(driver);
        menuPanel.attachPersonSelectedListener(person -> {
            PersonDetailsView view = new PersonDetailsView(person);
            replacePanel(view);
        });
        menuPanel.getMainPanel().setSize(600, 100);
        menuPanel.attachListener(panel -> {
            if (panel != null) {
                panel.setSize(currentContentPanel.getSize());
                replacePanel(panel);
            }
        });

        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);

        mainPanel.add(menuPanel.getMainPanel());
        mainPanel.add(currentContentPanel);

        MainFrame frame = new MainFrame();
        frame.run(mainPanel);
    }

    public static void replacePanel(JPanel newJPanel) {
        Container parent = currentContentPanel.getParent();
        int index = parent.getComponentZOrder(currentContentPanel);
        // remove the old edition of the panel
        parent.remove(currentContentPanel);
        // generate the replacement panel
        currentContentPanel = newJPanel;
        // place the replacement panel in the same relative location as the one it is replacing
        parent.add(currentContentPanel, index);

        // must call both of these, in the correct order
        parent.validate();
        parent.repaint();
    }


    public static BasePerson getParentForChild(String name, String dialog) {
        while (true) {
            System.out.println(dialog);
            String personName = input.next();

            if (personName.equals(".")) {
                return null;
            }

            BasePerson person = driver.findPersonByName(personName);
            if (person == null) {
                System.out.println("No parent was found with that name. Please try again later");
            } else if (person.getAge() < 16) {
                System.out.println("You cannot add " + person.getName() + " as a parent for " + name);
            } else {
                return person;
            }
        }
    }

    public static void showAll() {
        if (driver != null && driver.getAllPeople() != null && driver.getAllPeople().size() > 0) {
            List<BasePerson> people = driver.getAllPeople();
            for (BasePerson person : people) {
                System.out.println(person.toString());
            }
        } else {
            System.out.println("Sadly, their isn't anyone in the social network at the moment \n");
        }
    }


    public static void showOptions() {
        StringBuilder optionBuilder = new StringBuilder();
        optionBuilder.append("\n======================================\n");
        optionBuilder.append("MiniNet Menu\n");
        optionBuilder.append("======================================\n");
        optionBuilder.append("1. List everyone\n");
        optionBuilder.append("2. Find a person\n");
        optionBuilder.append("3. Add a new person\n");
        optionBuilder.append("4. Update a status\n");
        optionBuilder.append("5. Delete a person\n");
        optionBuilder.append("6. Are these two direct friends?\n");
        optionBuilder.append("0. To Quit\n\n");
        optionBuilder.append(">> Please input an option to continue.\n");
        System.out.println(optionBuilder.toString());
    }
}
