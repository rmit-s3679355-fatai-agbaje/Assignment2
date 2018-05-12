package com.company.model;

import com.company.UserType;
import com.company.exception.NoParentException;
import com.company.exception.TooYoungException;

public class YoungChild extends BasePerson {

    private BasePerson[] parents;

    public YoungChild(String name, int age, Adult... parents) throws Exception {
        super(name, age);
        setUserType(UserType.YOUNG_CHILD);
        this.setParents(parents);
    }

    public boolean setParents(Adult... parents) throws Exception {
        if (parents.length == 2) {
            for (BasePerson parent : parents) {
                if (parent.getAge() < 16) {
                    return false;
                }
            }

            parents[0].setPartner(parents[1]);
            parents[1].setPartner(parents[0]);
            this.parents = parents;
            return true;
        }
        else {
            throw new NoParentException();
        }
    }

    public BasePerson[] getParents() {
        return parents;
    }

    @Override
    public boolean canHaveFriends() {
        return false;
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age > 2){
            throw new Exception("A child cannot have his/her age greater than 16");
        }
        super.setAge(age);
    }

    @Override
    public boolean addFriend(BasePerson person) throws Exception {
        throw new TooYoungException();
    }

}