package com.leskurbas.dto;

import com.leskurbas.model.Play;
import com.leskurbas.model.Reservation;
import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;

import java.util.ArrayList;
import java.util.List;

public class ReservationsConvertor {

    public static ReservationsDTO convertToDTO(Reservation reservation,
                                               Play play) {
        ReservationsDTO reservationsDto = new ReservationsDTO();
        reservationsDto.setName(play.getName());
        reservationsDto.setPicture(play.getImage());
        reservationsDto.setPrice(MoneyConvertor.
                convertMoneyToString(reservation.getPrice())
        );

        Session session = play.getSessions().get(0);
        reservationsDto.setDate(DateTimeConvertor.
                convertDate(session.getDate()));
        reservationsDto.setTime(DateTimeConvertor.
                convertTime(session.getStartTime()));

        List<SeatsDTO> seatDtos = new ArrayList<>();
        SeatsDTO seatDto;
        for(Seat seat: reservation.getSeats()) {
            seatDto = new SeatsDTO();
            seatDto.setPlace(seat.getNumber());
            seatDto.setRow(seat.getRow());
            System.out.println("Convertor: " + seatDto.getRow()+" "+seatDto.getPlace());
            seatDtos.add(seatDto);
        }
        reservationsDto.setSeats(seatDtos);

        return reservationsDto;
    }
}
