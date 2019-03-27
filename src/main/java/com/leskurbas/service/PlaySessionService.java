package com.leskurbas.service;

import com.leskurbas.dao.PlayCRUD;
import com.leskurbas.dao.SessionsCRUD;
import com.leskurbas.dto.*;
import com.leskurbas.exceptions.CreationException;
import com.leskurbas.exceptions.DeletionException;
import com.leskurbas.model.Play;
import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaySessionService {
    private PlayCRUD playDao = new PlayCRUD();
    private SessionsCRUD sessionDao = new SessionsCRUD();

    public List<PlaysDTO> getAllPlaysDTO() {
        List<PlaysDTO> playsDtos = new ArrayList<>();
        List<Play> plays = playDao.getAll();

        for (Play play: plays) {
            playsDtos.add(PlaysConvertor.convertToDTO(play));
        }

        return playsDtos;
    }

    public BookingSessionDTO getBookingBySessionId(long id) {
        Map<Session, List<Seat>> booking = sessionDao
                .getSessionSeatsById(id);
        BookingSessionDTO result =null;

        for(Session s: booking.keySet()) {
            result = BookingSessionConvertor
                    .convertAllToDto(booking.get(s), s);
        }
        return result;
    }

    public void createPlay(String name, String time, String image)
            throws CreationException {

        Time playTime = DateTimeConvertor.convertString(time);
        if (time == null) {
            throw new CreationException(" Could not create play. "
                    +  "Wrong time format: " + time);
        }

        Play play = new Play(name, playTime, image);
        int result = playDao.insert(play);

        if (result == 0) {
            throw new CreationException("Could not create play. "
                    + "Error during insert");
        }
    }

    public Session getSessionById(long id) {
        return sessionDao.getSessionByID(id);
    }

    public long getPlayIdByName(String name) {
        return playDao.getPlayIdByName(name);
    }

    public void createSession(long playId, String date, String time,
                              float regularPrice, float vipPrice)
                                    throws CreationException{
        Time sessionTime = DateTimeConvertor.convertString(time);
        if (sessionTime == null) {
            throw new CreationException("Cannot create a session. "
                    + "Wrong time format: " + time);
        }

        Date sessionDate = DateTimeConvertor
                .convertStringToDate(date);
        if (sessionDate == null) {
            throw new CreationException("Cannot create a session. "
                    + "Wrong date format: " + date);
        }

        Session session = new Session(sessionDate, sessionTime,
                regularPrice, vipPrice, playId);
        int result = sessionDao.insert(session);
        if (result < 1) {
            throw new CreationException("Cannot create a session. "
                    + "Error during insert query.");
        }
    }

    public void deletePlay(long id) throws DeletionException {
        int result = playDao.delete(id);

        if(result < 1) {
            throw new DeletionException("Cannot delete play.");
        }
    }

    public void deleteSession(long id) throws DeletionException {
        int result = sessionDao.delete(id);

        if(result < 1) {
            throw new DeletionException("Cannot delete session.");
        }
    }
}