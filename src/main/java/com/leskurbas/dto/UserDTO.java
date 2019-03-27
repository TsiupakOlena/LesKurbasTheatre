package com.leskurbas.dto;

import java.util.List;

public class UserDTO extends BaseUserDTO {
    public List<ReservationsDTO> reservations;

    public List<ReservationsDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationsDTO> reservations) {
        this.reservations = reservations;
    }
}
