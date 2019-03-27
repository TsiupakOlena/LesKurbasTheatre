package com.leskurbas.dao;

import com.leskurbas.model.Play;
import com.leskurbas.model.Reservation;
import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReservationsCRUD {
    public Map<Reservation,Play> getAll(long userId) {
        Map<Reservation,Play> reservationsMap = new LinkedHashMap<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT reservation.ID as id, reservation.Price, sessions.Date, sessions.StartTime, "
                            + "plays.Name, plays.Image, seats.Row, seats.Number "
                            + "FROM reservation RIGHT JOIN sessions "
                            + "ON reservation.SessionID=sessions.ID "
                            + "RIGHT JOIN plays ON sessions.PlayID=plays.ID "
                            + "RIGHT JOIN seats ON reservation.ID = seats.ReservationID "
                            + "WHERE reservation.UserID=" + userId)) {
                try (ResultSet rs = ps.executeQuery()) {

                    long prevReservationId = -1;
                    long reservationId;
                    int iterator = 0;
                    List<Seat> seats = new ArrayList<>();

                    while (rs.next()) {
                        reservationId = rs.getLong("id");
                        if (iterator == 0){
                            prevReservationId = reservationId;
                        }
                        if (reservationId == prevReservationId) {
                            Seat seat = new Seat();
                            seat.setRow(rs.getByte("Row"));
                            seat.setNumber(rs.getShort("Number"));
                            System.out.println("CRUD: " + seat.getRow() + " " + seat.getNumber());
                            seats.add(seat);
                        } else {
                            Reservation reservation = new Reservation();
                            reservation.setPrice(rs.getFloat("Price"));
                            reservation.setSeats(seats);

                            List<Session> sessions = new ArrayList<>();
                            Session session = new Session();
                            session.setDate(rs.getDate("Date"));
                            session.setStartTime(rs.getTime("StartTime"));
                            sessions.add(session);

                            Play play = new Play();
                            play.setName(rs.getString("Name"));
                            play.setImage(rs.getString("Image"));
                            play.setSessions(sessions);
                            reservationsMap.put(reservation,play);
                            seats = new ArrayList<>();
                            prevReservationId = reservationId;

                            Seat seat = new Seat();
                            seat.setRow(rs.getByte("Row"));
                            seat.setNumber(rs.getShort("Number"));
                            System.out.println("CRUD: " + seat.getRow() + " " + seat.getNumber());
                            seats.add(seat);
                        }

                        if (rs.isLast()) {
                            Reservation reservation = new Reservation();
                            reservation.setPrice(rs.getFloat("Price"));
                            reservation.setSeats(seats);

                            List<Session> sessions = new ArrayList<>();
                            Session session = new Session();
                            session.setDate(rs.getDate("Date"));
                            session.setStartTime(rs.getTime("StartTime"));
                            sessions.add(session);

                            Play play = new Play();
                            play.setName(rs.getString("Name"));
                            play.setImage(rs.getString("Image"));
                            play.setSessions(sessions);
                            reservationsMap.put(reservation,play);
                        }
                        iterator++;
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return reservationsMap;
        }
    }

    public long insertReservation(Reservation reservation) {
        long id = -1;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO reservation (Price, UserID, SessionID) "
                    + "VALUES (?, ?, ?)")){

                ps.setFloat(1, reservation.getPrice());
                ps.setLong(2, reservation.getUserID());
                ps.setLong(3, reservation.getSessionID());

                ps.executeUpdate();
                connection.commit();

            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }

            id = lastInsertId();

        } finally {
            return id;
        }
    }

    private long lastInsertId() {
        long id = -1;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (Statement statement = connection.createStatement()) {

                String sql = "SELECT MAX(ID) AS id FROM reservation";
                try(ResultSet rs = statement.executeQuery(sql)) {

                    while (rs.next()) {
                        id = rs.getLong("id");
                    }
                    System.out.println(id);
                    connection.commit();
                }
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } finally {
            return id;
        }
    }

}
