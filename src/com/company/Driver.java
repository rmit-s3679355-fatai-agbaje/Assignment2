package com.company;

import com.company.model.Adult;
import com.company.model.BasePerson;
import com.company.model.Child;

import java.util.ArrayList;
import java.util.List;

class Driver {

    private List<BasePerson> allPeople;

    public Driver() {
        allPeople = new ArrayList<BasePerson>();

        this.addDefaultPeople();

    }

    public void addDefaultPeople() {
        try {
            Adult aliysa = new Adult("Alysa", 24);
            Adult roland = new Adult("Roland", 45);
            allPeople.add(aliysa);
            allPeople.add(roland);
            allPeople.add(new Child("David", 12, aliysa, roland));

            allPeople.add(new Adult("Daniel", 28));
            allPeople.add(new Adult("Cody", 20));
            allPeople.add(new Adult("Sherlock", 29));
            allPeople.add(new Adult("Mane", 20));
            allPeople.add(new Adult("Faith", 30));
            allPeople.add(new Adult("Daniel", 21));
            allPeople.add(new Adult("Fatai", 28));
            allPeople.add(new Adult("Caleb", 20));
            allPeople.add(new Adult("Daniel", 45));
            allPeople.add(new Adult("Desmond", 80));
            allPeople.add(new Adult("Daniel", 35));
            allPeople.add(new Adult("Tutu", 39));
        }
        catch (Exception ex){
            System.out.println("An error occurred.");
        }
    }

    public void addPerson(BasePerson person) {
        this.allPeople.add(person);
    }

    public boolean makeFriends(BasePerson person1, BasePerson person2) {
        return person1.addFriend(person2) && person2.addFriend(person1);
    }

    public boolean addParents(Child child, BasePerson parent1, BasePerson parent2) {
        return child.setParents(parent1, parent2);
    }

    public BasePerson findPersonByName(String name) {
        List<BasePerson> people = this.allPeople;
        if (people != null & people.size() > 0) {
            for (BasePerson person : people) {
                if (person.getName().equalsIgnoreCase(name)) {
                    return person;
                }
            }
        }
        return null;
    }

    public boolean areFriend(BasePerson firstPerson, BasePerson secondPerson) {
        return firstPerson.hasFriend(secondPerson) && secondPerson.hasFriend(firstPerson);
    }

    public void displayProfile(BasePerson person) {
        String profileInformation = person.toString();
        System.out.println(profileInformation);
    }


    public boolean deletePerson(BasePerson person) {
        int index = this.allPeople.indexOf(person);
        if (index > -1) {

            if (person instanceof Child) {
                Child child = (Child) person;
                Adult[] adults = (Adult[]) child.getParents();
                for (Adult adult : adults) {
                    adult.removeChild(child);
                }
            } else if (person instanceof Adult) {
                Adult adult = (Adult) person;
                List<Child> children = adult.getChildren();
                if (children != null && children.size() > 0) {
                    System.out.println("You cannot delete a adult that has children. Please delete the child first");
                    return false;
                }
            }

            this.allPeople.remove(index);
            return true;
        }
        return false;
    }

    public List<BasePerson> getAllPeople() {
        return allPeople;
    }
}