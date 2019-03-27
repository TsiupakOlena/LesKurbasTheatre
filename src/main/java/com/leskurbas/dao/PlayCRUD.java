package com.leskurbas.dao;

import com.leskurbas.model.Play;
import com.leskurbas.model.Session;
import com.leskurbas.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class PlayCRUD {

    public List<Play> getAll() {
        List<Play> plays = new ArrayList<>();
        List<Session> sessions = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                "SELECT plays.Name, plays.Duration, plays.Image, sessions.Date, "
                    + "sessions.StartTime, sessions.ID FROM sessions "
                        + "RIGHT JOIN plays ON sessions.PlayID=plays.ID "
                            + "ORDER BY plays.Name")) {

                try (ResultSet rs = ps.executeQuery()) {
                    Play currentPlay;
                    Play previousPlay = null;
                    Date date;
                    int iterationsCounter = 0;
                    boolean noSessionsMark = false;

                    while (rs.next()) {
                        currentPlay = new Play(
                                rs.getString("Name"),
                                rs.getTime("Duration"),
                                rs.getString("Image")
                        );

                        if (iterationsCounter == 0 || noSessionsMark) {
                            previousPlay = currentPlay;
                            noSessionsMark=false;
                        }
                        if (!currentPlay.getName().equals(previousPlay.getName())) {
                            addPlayToList(previousPlay, plays, sessions);
                            sessions = new ArrayList<>();
                        }

                        date = rs.getDate("Date");
                        if (date != null) {
                            addSessionToList(rs, sessions);
                        } else {
                            addPlayToList(currentPlay, plays, sessions);
                            sessions = new ArrayList<>();
                            noSessionsMark = true;
                        }

                        if (rs.isLast() && !noSessionsMark) {
                            addPlayToList(currentPlay, plays, sessions);
                        }
                        previousPlay = currentPlay;
                        iterationsCounter++;
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return plays;
        }
    }

    private void addPlayToList(Play play, List<Play> plays,
                               List<Session> sessions) {
        play.setSessions(sessions);
        plays.add(play);
    }

    private void addSessionToList(ResultSet rs,
            List<Session> sessions)throws SQLException {

        Session session = new Session();
        session.setId(rs.getLong("ID"));
        session.setDate(rs.getDate("Date"));
        session.setStartTime(
                rs.getTime("StartTime")
        );
        sessions.add(session);
    }

    public int insert(Play play) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO plays (Name, Duration, Image) VALUES "
                            + "(?, ?, ?)")){
                ps.setString(1, play.getName());
                ps.setTime(2, play.getDuration());
                ps.setString(3, play.getImage());

                status = ps.executeUpdate();
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return status;
        }
    }

    public int delete(long id) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM plays WHERE ID=?")){
                ps.setLong(1, id);

                status = ps.executeUpdate();
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return status;
        }
    }

    public long getPlayIdByName(String name) {
        long id = -1;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT ID FROM plays WHERE Name=?")) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()){

                    if (rs.next()) {
                        id = rs.getLong("ID");
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return id;
        }
    }
}
