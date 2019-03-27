package com.leskurbas.dao;

import com.leskurbas.model.Seat;
import com.leskurbas.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeatCRUD {

    public Seat getSeatByPosition(long sessionId, byte row, short place) {
        Seat seat = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT seats.ID, IsReserved, IsVIP FROM seats WHERE seats.Row=? "
                            + "AND Number=? AND SessionID=?")){

                ps.setByte(1, row);
                ps.setShort(2, place);
                ps.setLong(3, sessionId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        seat = new Seat();
                        seat.setId(rs.getLong("ID"));
                        seat.setReserved(
                                rs.getByte("IsReserved")
                        );
                        seat.setVIP(rs.getByte("IsVIP"));
                    }
                    connection.commit();
                }
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return seat;
        }
    }

    public int updateSeats(List<Seat> seats, long reservationId) {
        int status = 0;
        String sql = "UPDATE seats SET IsReserved=?, ReservationID=?"
            + " WHERE ID";

        if (seats.size() == 1) {
            sql += "=" + seats.get(0).getId();
        } else {
            String temp=" IN (";
            for(Seat s: seats) {
                temp += s.getId() + ", ";
            }
            temp = temp.substring(0, temp.length() - 2);
            temp += ")";
            sql += temp;
            System.out.println(sql);
        }

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)){

                ps.setByte(1, (byte) 1);
                ps.setLong(2, reservationId);

                status = ps.executeUpdate();
                System.out.println(status);
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return status;
        }
    }
}
