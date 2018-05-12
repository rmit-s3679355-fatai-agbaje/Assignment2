package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private List<BasePerson> people;

    public SchoolClass() {
        this.people = new ArrayList<BasePerson>();
    }

    public void addPeople(BasePerson person) {
        if (this.people != null)
            this.people.add(person);
    }

    public List<BasePerson> getPeople() {
        return people;
    }

    public void setPeople(List<BasePerson> people) {
        this.people = people;
    }
}
