package com.leskurbas.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

public class Play implements Serializable {
    private long id;
    private String name;
    private Time duration;
    private String image;
    private List<Session> sessions;

    public Play() {
    }

    public Play(String name, Time duration, String image) {
        this.name = name;
        this.duration = duration;
        this.image = image;
    }

    public Play(long id, String name, Time duration,
                String image) {
        this(name, duration, image);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}