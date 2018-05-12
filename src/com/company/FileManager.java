package com.company;

import com.company.model.Adult;
import com.company.model.BasePerson;
import com.company.model.Child;
import com.company.model.SchoolClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    BufferedReader br = null;
    FileReader fr = null;
    List<BasePerson> people;

    public FileManager(List<BasePerson> people) {
        this.people = people;
    }

    public List<BasePerson> readPeopleFile() {

        List<BasePerson> people = new ArrayList<BasePerson>();
        String FILENAME = "people.txt";
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String currentPerson;

            while ((currentPerson = br.readLine()) != null) {
                BasePerson person = this.processSinglePerson(currentPerson);
                ;
                if (person != null) {
                    people.add(person);
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

    private BasePerson processSinglePerson(String personString) throws Exception {

        String[] splits = personString.split(",");
        for (int i = 0; i < splits.length; i++) {
            String name = splits[0];
            String picture = splits[1];
            String status = splits[2];
            String sex = splits[3];
            int age = Integer.parseInt(splits[4].trim());
            String state = splits[5];

            if (age < 2) {

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

    private BasePerson findPerson(String name) {
        if (this.people != null && this.people.size() > 0) {
            for (BasePerson person : people) {
                if (person.getName().equalsIgnoreCase(name))
                    return person;
            }
        }
        return null;
    }

    public SchoolClass getClass(String name){

    }

    public boolean processRelationship(String entry) throws Exception {
        String[] entries = entry.split(",");
        if (entries.length < 3) {
            return false;
        }

        String first = entries[0];
        String second = entries[1];
        String relationship = entries[2];


        BasePerson firstPerson = this.findPerson(first);
        BasePerson secondPerson = this.findPerson(first);

        if (relationship == "friends") {
            if (firstPerson != null && firstPerson.canHaveFriends() && secondPerson != null && secondPerson.canHaveFriends()){
                firstPerson.addFriend(secondPerson);
                secondPerson.addFriend(firstPerson);
            }
            else {
                return false;
            }
        } else if (relationship == "couple") {
            if (firstPerson instanceof Adult && secondPerson instanceof Adult){
                Adult firstAdult = (Adult) firstPerson;
                Adult secondAdult = (Adult) secondPerson;

                firstAdult.setPartner(secondAdult);
                secondAdult.setPartner(firstAdult);
            }
        } else if (relationship == "parent") {



        } else if (relationship == "classmates") {

            SchoolClass schoolClass = getClass("")

        } else if (relationship == "colleagues") {

        }
    }


}
