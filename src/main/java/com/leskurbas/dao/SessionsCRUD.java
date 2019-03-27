package com.leskurbas.dao;

import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;
import com.leskurbas.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionsCRUD {

    public Session getSessionByID(long id) {
        Session session = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT StandardTicketPrice, VIPTicketPrice "
                            + "FROM sessions WHERE ID=?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        session = new Session();
                        session.setStandardTicketPrice(rs.getFloat(
                                "StandardTicketPrice"
                        ));
                        session.setVipTicketPrice(rs.getFloat(
                                "VIPTicketPrice"
                        ));
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return session;
        }
    }

    public int insert(Session session) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO sessions(sessions.Date, StartTime, "
                            + "StandardTicketPrice, VIPTicketPrice, "
                                + "PlayID) VALUES (?, ?, ?, ?, ?)")){
                ps.setDate(1, session.getDate());
                ps.setTime(2, session.getStartTime());
                ps.setFloat(3,
                        session.getStandardTicketPrice()
                );
                ps.setFloat(4, session.getVipTicketPrice());
                ps.setLong(5, session.getPlayID());

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
                    "DELETE FROM sessions WHERE ID=?")){
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

    public Map<Session, List<Seat>> getSessionSeatsById (long id) {
        Map<Session, List<Seat>> sessionSeats = new HashMap<>();
        List<Seat> seats = new ArrayList<>();
        Session session = new Session();
        Seat seat;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT sessions.StandardTicketPrice, "
                            + "sessions.VipTicketPrice, seats.Row, seats.Number,"
                            + " seats.IsVIP, seats.IsReserved FROM sessions "
                            + "RIGHT JOIN seats ON sessions.ID=seats.SessionID "
                            + " WHERE sessions.ID=?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    long prevId = -1;
                    while (rs.next()) {
                        seat = new Seat();
                        seat.setRow(rs.getByte("Row"));
                        seat.setNumber(rs.getByte("Number"));
                        seat.setVIP(rs.getByte("IsVIP"));
                        seat.setReserved(rs.getByte("IsReserved"));
                        seats.add(seat);

                        if (prevId != id) {
                            session.setId(id);
                            session.setStandardTicketPrice(
                                rs.getFloat("StandardTicketPrice")
                            );
                            session.setVipTicketPrice(
                                rs.getFloat("VIPTicketPrice")
                            );
                        }
                    }
                    sessionSeats.put(session, seats);
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return sessionSeats;
        }
    }
}
