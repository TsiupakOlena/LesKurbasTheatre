package com.leskurbas.dto;

import com.leskurbas.model.Seat;
import com.leskurbas.model.Session;

import java.util.ArrayList;
import java.util.List;

public class BookingSessionConvertor {

    public static BookingSessionDTO convertAllToDto(
            List<Seat> seats, Session session) {

        BookingSessionDTO bsdto = new BookingSessionDTO();
        List<SeatsDTO> seatsDto = new ArrayList<>();
        SeatsDTO seatDto;
        String standardPrice = MoneyConvertor.
                convertMoneyToString(session.
                        getStandardTicketPrice()
                );
        String vipPrice = MoneyConvertor.
                convertMoneyToString(
                    session.getVipTicketPrice()
                );

        for (Seat s: seats) {
            seatDto = new SeatsDTO();
            boolean reserved = (s.isReserved() == 1);
            boolean vip = (s.isVIP() == 1);

            seatDto.setRow(s.getRow());
            seatDto.setPlace(s.getNumber());

            if (reserved) {
                seatDto.setSeatClass("reserved");
                seatDto.setPrice("0");
            } else if (vip) {
                seatDto.setSeatClass("vip");
                seatDto.setPrice(vipPrice);
            } else {
                seatDto.setSeatClass("standard");
                seatDto.setPrice(standardPrice);
            }
            seatsDto.add(seatDto);
        }

        bsdto.setId(session.getId());
        bsdto.setSeats(seatsDto);

        return bsdto;
    }
}
