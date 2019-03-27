package com.leskurbas.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Session implements Serializable {
    private long id;
    private Date date;
    private Time startTime;
    private float standardTicketPrice;
    private float vipTicketPrice;
    private long playID;

    public Session() {}

    public Session(Date date, Time startTime, float standardTicketPrice,
                   float vipTicketPrice, long playID) {
        this.date = date;
        this.startTime = startTime;
        this.standardTicketPrice = standardTicketPrice;
        this.vipTicketPrice = vipTicketPrice;
        this.playID = playID;
    }

    public Session(long id, Date date, Time startTime,
                   float standardTicketPrice, float vipTicketPrice,
                   long playID) {
        this(date, startTime, standardTicketPrice, vipTicketPrice, playID);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public float getStandardTicketPrice() {
        return standardTicketPrice;
    }

    public void setStandardTicketPrice(float standardTicketPrice) {
        this.standardTicketPrice = standardTicketPrice;
    }

    public float getVipTicketPrice() {
        return vipTicketPrice;
    }

    public void setVipTicketPrice(float vipTicketPrice) {
        this.vipTicketPrice = vipTicketPrice;
    }

    public long getPlayID() {
        return playID;
    }

    public void setPlayID(long playID) {
        this.playID = playID;
    }
}
