package com.leskurbas.dto;

import java.util.List;

public class BookingSessionDTO {
    public long id;
    public List<SeatsDTO> seats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SeatsDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatsDTO> seats) {
        this.seats = seats;
    }
}
