package com.leskurbas.service;

import com.leskurbas.dao.ReservationsCRUD;
import com.leskurbas.dao.SeatCRUD;
import com.leskurbas.dto.ReservationsConvertor;
import com.leskurbas.dto.ReservationsDTO;
import com.leskurbas.exceptions.ReservationException;
import com.leskurbas.model.Play;
import com.leskurbas.model.Reservation;
import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private ReservationsCRUD reservationDao = new ReservationsCRUD();

    public List<ReservationsDTO> getAllReservationsDto(long userId) {
        List<ReservationsDTO> reservationDtos = new ArrayList<>();
        Map<Reservation, Play> reservations = reservationDao.getAll(userId);

        for (Reservation reservation: reservations.keySet()) {
            reservationDtos.add(ReservationsConvertor.convertToDTO(reservation,
                    reservations.get(reservation)));
             for(Seat seat: reservation.getSeats()) {
                 System.out.println("Service: " + seat.getRow()+" "+seat.getNumber());

             }
        }
        return reservationDtos;
    }

    public void reserve(long userId, long sessionId,
                           List<Seat> seats) throws ReservationException {
        PlaySessionService sessionService = new PlaySessionService();
        Session session = sessionService.getSessionById(sessionId);
        if (session == null) {
            throw new ReservationException("Booked session doesn't exist!");
        }

        float standardPrice = session.getStandardTicketPrice();
        float vipPrice = session.getVipTicketPrice();
        SeatCRUD seatDao = new SeatCRUD();
        Seat result;
        float price = 0;

        for (Seat s: seats) {
            result = seatDao.getSeatByPosition(sessionId, s.getRow(),
                    s.getNumber());
            if (result !=null) {

                if(result.isReserved() != 0) {
                    throw new ReservationException("One of the seats "
                            + "is already reserved!");
                } else if (result.isVIP() != 0) {
                    price += vipPrice;
                } else {
                    price += standardPrice;
                }

                s.setId(result.getId());
            } else {
                throw new ReservationException("One of the seats "
                        + "was not found!");
            }
        }

        Reservation reservation = new Reservation(price, userId, sessionId);
        ReservationsCRUD reservationDao = new ReservationsCRUD();
        long reservationId = reservationDao.insertReservation(reservation);

        int resultUpdate = seatDao.updateSeats(seats, reservationId);

        if (resultUpdate < 1) {
            throw new ReservationException("Error while booking seats!");
        }
    }
}
