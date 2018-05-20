package com.company.model;

import com.company.UserType;
import com.company.exception.NoAvailableException;
import com.company.exception.NotToBeClassmatesException;
import com.company.exception.NotToBeColleaguesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Adult extends BasePerson {

    private List<Child> children;
    private Adult partner;

    public Adult(String name, int age, Child... children) throws Exception {
        super(name, age);
        this.setUserType(UserType.ADULT);
        if (children != null) {
            this.children = Arrays.asList(children);
        } else {
            this.children = new ArrayList<>();
        }
    }

    @Override
    public boolean canHaveFriends() {
        return true;
    }

    public List<Child> getChildren() {
        return children;
    }

    public boolean removeChild(Child child) {
        if (this.children != null && this.children.size() > 0) {
            return this.children.remove(child);
        }
        return false;
    }

    public boolean addChild(Child child){
        if (this.children == null){
            this.children.add(child);
        }
        if (!this.children.contains(child)){
            return this.children.add(child);
        }
        return false;
    }

    public Adult getPartner() {
        return partner;
    }

    public void setPartner(Adult partner) throws Exception {
        if (this.partner != null && partner != partner) {
            throw new NoAvailableException();
        } else {
            this.addFriend(partner);
            this.partner = partner;
        }
    }

    @Override
    public boolean addFriend(BasePerson person) {
        if (person.getAge() < 16) {
            this.friends.add(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean canHaveColleagues() {
        return true;
    }

    @Override
    public boolean canHaveClassmates() {
        return true;
    }

}
