package com.leskurbas.model;

import java.io.Serializable;
import java.util.List;

public class Reservation implements Serializable {
    private long id;
    private float price;
    private long userID;
    private long sessionID;
    private List<Seat> seats;

    public Reservation() {}

    public Reservation(float price, long userID, long sessionID) {
        this.price = price;
        this.userID = userID;
        this.sessionID = sessionID;
    }

    public Reservation(long id, float price, long userID, long sessionID) {
        this(price, userID, sessionID);
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
