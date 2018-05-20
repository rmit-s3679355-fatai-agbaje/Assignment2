package com.company;

import com.company.model.Adult;
import com.company.model.BasePerson;
import com.company.model.Child;
import com.company.model.YoungChild;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is FileManager class responsible for managing the access of data read from the file
 */
public class FileManager {

    BufferedReader br = null;
    FileReader fr = null;
    List<BasePerson> people;
    List<Child> pendingChildren;

    public FileManager(List<BasePerson> people) throws Exception {
        this.people = people;
        this.pendingChildren = new ArrayList<Child>();

        this.people.addAll(this.readPeopleFile());
        this.readRelations();
    }

    /**
     * This method reads through the relation file named "relations.txt",
     * processing line by line the content and adjusting the relations of the users individually
     *
     * @throws Exception thrown from the see processRelationship() method
     */
    public void readRelations() throws Exception {
        String FILENAME = "relations.txt";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String current;

            while ((current = br.readLine()) != null) {
                this.processRelationship(current);
            }

            for (Child child : this.pendingChildren){
                if (child.getParents() != null && child.getParents().size() == 2){
                    this.people.add(child);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method reads the content of the file "people.txt" and adds valid people to a list
     *
     * @return the list of people read from the content of the file.
     * @throws Exception from processing the individual entries
     */
    public List<BasePerson> readPeopleFile() throws Exception {
        List<BasePerson> people = new ArrayList<BasePerson>();
        String FILENAME = "people.txt";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String currentPerson;

            while ((currentPerson = br.readLine()) != null) {
                BasePerson person = this.processSinglePerson(currentPerson);

                if (person != null && person instanceof Adult) {
                    people.add(person);
                } else if (person != null && person instanceof Child) {
                    Child child = (Child) person;
                    pendingChildren.add(child);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return people;
    }

    /**
     * @param personString a single line of content read from the file
     * @return the processed Person based on the contents of the string
     * @throws Exception from potentially inconsistent data coming from the file
     */
    private BasePerson processSinglePerson(String personString) throws Exception {

        String[] splits = personString.split(",");
        for (int i = 0; i < splits.length; i++) {
            String name = splits[0].trim();
            String picture = splits[1];
            String status = splits[2];
            String sex = splits[3];
            int age = Integer.parseInt(splits[4].trim());
            String state = splits[5];

            if (age <= 2) {
                YoungChild youngChild = new YoungChild(name, age, null);
                youngChild.setStatus(status);
                youngChild.setPicture(picture);
                youngChild.setState(state);
                youngChild.setSex(sex);
                return youngChild;
            } else if (age <= 16) {
                Child child = new Child(name, age, null);
                child.setStatus(status);
                child.setPicture(picture);
                child.setState(state);
                child.setSex(sex);
                return child;
            } else {
                Adult person = new Adult(name, age, null);
                person.setPicture(picture);
                person.setStatus(status);
                person.setSex(sex);
                person.setState(state);
                return person;
            }
        }

        return null;
    }

    /**
     * This method searches through the list of people currently available to our file manager
     *
     * @param name the full name of the users
     * @return the user or null if nobody with the @param name exists
     */
    private BasePerson findPerson(String name) {
        if (this.people != null && this.people.size() > 0) {
            for (BasePerson person : people) {
                if (person.getName().equalsIgnoreCase(name))
                    return person;
            }
        }

        if (this.pendingChildren != null && this.pendingChildren.size() > 0) {
            for (BasePerson person : this.pendingChildren) {
                if (person.getName().equalsIgnoreCase(name))
                    return person;
            }
        }

        return null;
    }

    //This checks if a person is a Child or a YoungChild
    private boolean isChild(BasePerson basePerson) {
        return basePerson instanceof Child || basePerson instanceof YoungChild;
    }

    /**
     * @param entry A single individual entry read from the file
     * @return true if the relationship was successfully processed or false, if it wasn't
     * @throws Exception when their an an error
     */
    public boolean processRelationship(String entry) throws Exception {
        String[] entries = entry.split(",");
        if (entries.length < 3) {
            return false;
        }

        String first = entries[0];
        String second = entries[1];
        String relationship = entries[2];

        BasePerson firstPerson = this.findPerson(first);
        BasePerson secondPerson = this.findPerson(second);

        if (relationship == "friends") {
            if (firstPerson != null && firstPerson.canHaveFriends() && secondPerson != null && secondPerson.canHaveFriends()) {
                return firstPerson.addFriend(secondPerson) & secondPerson.addFriend(firstPerson);
            } else {
                return false;
            }
        } else if (relationship == "couple") {
            if (firstPerson instanceof Adult && secondPerson instanceof Adult) {
                Adult firstAdult = (Adult) firstPerson;
                Adult secondAdult = (Adult) secondPerson;

                firstAdult.setPartner(secondAdult);
                secondAdult.setPartner(firstAdult);

                return true;
            }
        } else if (relationship == "parent") {

            Child child = (firstPerson instanceof Child) ? (Child) firstPerson : (secondPerson instanceof Child) ? (Child) secondPerson : null;
            Adult parent = (firstPerson instanceof Adult) ? (Adult) firstPerson : (secondPerson instanceof Adult) ? (Adult) secondPerson : null;

            if (child != null && parent != null) {
                return child.addParent(parent);
            }

        } else if (relationship == "classmates") {
            if (firstPerson != null && firstPerson.canHaveClassmates() && secondPerson != null && secondPerson.canHaveClassmates()) {
                return firstPerson.addClassmates(secondPerson) & secondPerson.addClassmates(firstPerson);
            }
        } else if (relationship == "colleagues") {
            if (firstPerson != null && firstPerson.canHaveColleagues() && secondPerson != null && secondPerson.canHaveColleagues()) {
                return firstPerson.addColleague(secondPerson) & secondPerson.addColleague(firstPerson);
            }
        }

        return false;
    }


}
