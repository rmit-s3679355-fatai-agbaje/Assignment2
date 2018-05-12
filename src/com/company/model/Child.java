package com.company.model;

import com.company.UserType;
import com.company.exception.NotToBeFriendsException;

public class Child extends BasePerson {

    private BasePerson[] parents;

    public Child(String name, int age, BasePerson... parents) throws Exception {
        super(name, age);
        setUserType(UserType.CHILD);
        this.setParents(parents);
    }

    public boolean setParents(BasePerson... parents) {
        if (parents.length == 2) {
            for (BasePerson parent : parents) {
                if (parent.getAge() < 16) {
                    return false;
                }
            }

            parents[0].addFriend(parents[1]);
            parents[1].addFriend(parents[0]);
            this.parents = parents;
            return true;
        }
        else {
            System.out.println("This user must have two parents");
            return false;
        }
    }

    public BasePerson[] getParents() {
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
        super.setAge(age);
    }

    @Override
    public boolean addFriend(BasePerson person) throws NotToBeFriendsException {
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

}