package com.company.model;

import com.company.UserType;
import com.company.exception.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BasePerson {

    private String id;
    protected List<BasePerson> friends;
    private UserType userType;
    private List<BasePerson> colleagues;
    private List<BasePerson> classmates;
    private int age;

    private String name;
    private String image;
    private String status;
    private String sex;
    private String state;
    private String picture;

    public BasePerson(String name, int age) throws Exception {
        this.name = name;
        this.setAge(age);
        this.friends = new ArrayList<BasePerson>();
    }

    public abstract boolean canHaveFriends();

    public boolean hasFriend(BasePerson person) {
        return this.friends.contains(person);
    }

    public abstract boolean addFriend(BasePerson person) throws Exception;

    public abstract boolean canHaveColleagues();

    public abstract boolean canHaveClassmates();

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getId() {
        return this.id;
    }

    public List<BasePerson> getFriends() {
        return this.friends;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAge(int age) throws Exception {
        if (age < 0 && age > 150)
            throw new NoSuchAgeException();
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getState() {
        return state;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setState(String state) throws InvalidStateException {
        String[] validEntries = new String[]{"ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA"};
        if (Arrays.asList(validEntries).indexOf(state) > -1) {
            this.state = state;
        } else {
            throw new InvalidStateException(state);
        }
    }

    public List<BasePerson> getColleagues() {
        return colleagues;
    }

    public boolean addColleague(BasePerson person) throws NotToBeColleaguesException {
        if (this.canHaveColleagues()) {
            if (this.colleagues == null)
                this.colleagues = new ArrayList<>();
            this.colleagues.add(person);
            return true;
        } else {
            throw new NotToBeColleaguesException();
        }
    }

    public List<BasePerson> getClassmates() {
        return classmates;
    }

    public boolean addClassmates(BasePerson person) throws NotToBeClassmatesException {
        if (this.canHaveClassmates()) {
            if (this.classmates == null)
                this.classmates = new ArrayList<BasePerson>();
            this.classmates.add(person);
            return true;
        }
        throw new NotToBeClassmatesException();
    }

    public UserType getUserType() {
        return this.userType;
    }

    @Override
    public String toString() {
        return String.format("%s \n%s %d \n%s %s\n", "Name: " + name, "Age: ", age, "Status: ", this.status != null ? this.status : "N/A");
    }
}