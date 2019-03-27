package com.leskurbas.model;

import java.io.Serializable;

public class Person implements Serializable {
    private long id;
    private String email;
    private String surname;
    private String name;
    private long userID;

    public Person() {
    }

    public Person(String email, String surname, String name) {
        this.email = email;
        this.surname = surname;
        this.name = name;
    }

    public Person(String email, String surname, String name, long userID) {
        this(email, surname, name);
        this.userID = userID;
    }

    public Person(long id, String email, String surname, String name, long userID) {
        this(email, surname, name, userID);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}