package com.leskurbas.dto;

import java.sql.Date;
import java.sql.Time;

public class SessionsDTO {
    public long id;
    public String date;
    public String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
