package com.company.model;

import com.company.UserType;
import com.company.exception.NotToBeClassmatesException;
import com.company.exception.NotToBeColleaguesException;
import com.company.exception.NotToBeFriendsException;
import com.company.exception.TooYoungException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Child extends BasePerson {

    private List<Adult> parents = new ArrayList<Adult>(2);

    public Child(String name, int age, Adult... parents) throws Exception {
        super(name, age);
        setUserType(UserType.CHILD);
        this.setParents(parents);
    }

    public boolean setParents(Adult... parents) throws Exception {
        if (parents != null && parents.length == 2) {
            for (BasePerson parent : parents) {
                if (parent.getAge() < 16) {
                    return false;
                }
            }

            parents[0].addFriend(parents[1]);
            parents[1].addFriend(parents[0]);
            this.parents.addAll(Arrays.asList(parents));
            return true;
        }
        else {
            System.out.println("This user must have two parents");
            return false;
        }
    }


    public boolean addParent(Adult adult){
        if (this.parents == null || this.parents.size() < 2){
            return this.parents.add(adult) & adult.addChild(this);
        }
        return false;
    }

    public List<Adult> getParents() {
        return parents;
    }

    @Override
    public boolean canHaveFriends() {
        return true;
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age > 16){
            throw new Exception("A child cannot have his/her age greater than 16");
        }
        else if (age < 2){
            throw new Exception("A child cannot have his/her age less than 2");
        }
        super.setAge(age);
    }

    @Override
    public boolean addFriend(BasePerson person) throws NotToBeFriendsException, TooYoungException {
        if (this.getAge() < 2 || person.getAge() < 2){
            return false;
        }
        else if (person.getAge() < 16){
            int ageDifference = this.getAge() - person.getAge();
            if (ageDifference < 3 || ageDifference > -3){
                this.friends.add(person);
                return true;
            }
            else {
                throw new NotToBeFriendsException();
            }
        }
        return false;
    }

    @Override
    public boolean canHaveColleagues(){
        return false;
    }

    @Override
    public boolean canHaveClassmates(){
        return true;
    }

}