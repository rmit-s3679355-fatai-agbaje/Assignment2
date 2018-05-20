package com.company.model;

import com.company.UserType;
import com.company.exception.NoParentException;
import com.company.exception.NotToBeClassmatesException;
import com.company.exception.NotToBeColleaguesException;
import com.company.exception.TooYoungException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YoungChild extends Child {

//    private List<Adult> parents = new ArrayList<Adult>(2);

    public YoungChild(String name, int age, Adult... parents) throws Exception {
        super(name, age);
        setUserType(UserType.YOUNG_CHILD);
        this.setParents(parents);
    }

    @Override
    public boolean canHaveFriends() {
        return false;
    }

    @Override
    public void setAge(int age) throws Exception {
        if (age > 2) {
            throw new Exception("A child cannot have his/her age greater than 16");
        }
        super.setAge(age);
    }

    @Override
    public boolean addFriend(BasePerson person) throws TooYoungException {
        throw new TooYoungException();
    }

    @Override
    public boolean canHaveColleagues() {
        return false;
    }

    @Override
    public boolean canHaveClassmates() {
        return false;
    }

}