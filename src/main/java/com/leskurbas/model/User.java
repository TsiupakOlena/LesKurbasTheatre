package com.leskurbas.model;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String login;
    private String password;
    private byte isAdmin;
    private String picture;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, byte isAdmin) {
        this(login, password);
        this.isAdmin = isAdmin;
    }

    public User(long id, String login, String password, byte isAdmin) {
        this(login, password, isAdmin);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
