package com.leskurbas.dto;

import java.sql.Time;
import java.util.List;

public class PlaysDTO {
    public String name;
    public String image;
    public String duration;
    public List<SessionsDTO> sessions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionsDTO> sessions) {
        this.sessions = sessions;
    }
}
