package com.leskurbas.dto;

import com.leskurbas.model.Play;
import com.leskurbas.model.Session;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlaysConvertor {

    public static PlaysDTO convertToDTO(Play play) {
        List<SessionsDTO> dtoSessions = new ArrayList<>();
        SessionsDTO dtoSession;
        System.out.println(play.getName());
        if (play.getSessions().size() > 0) {
            for(Session s: play.getSessions()) {
                System.out.println(s.getDate());
                dtoSession = new SessionsDTO();
                dtoSession.setId(s.getId());
                dtoSession.setDate(DateTimeConvertor.
                        convertDate(s.getDate()));
                dtoSession.setTime(DateTimeConvertor.
                        convertTime(s.getStartTime()));
                dtoSessions.add(dtoSession);
            }
        }

        PlaysDTO dtoPlays = new PlaysDTO();
        dtoPlays.setName(play.getName());
        dtoPlays.setImage(play.getImage());
        dtoPlays.setDuration(DateTimeConvertor.
                convertTime(play.getDuration()));
        dtoPlays.setSessions(dtoSessions);

        return dtoPlays;
    }
}
