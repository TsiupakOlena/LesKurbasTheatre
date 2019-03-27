package com.leskurbas.dto;

import com.leskurbas.model.User;

import java.util.List;

public class UserConvertor {

    public static UserDTO convertUserToDTO(User user,
                                       List<ReservationsDTO> reservations) {
        UserDTO userDto = new UserDTO();
        userDto = (UserDTO) convertToDTO(userDto, user);
        userDto.setReservations(reservations);

        return userDto;
    }

    public static AdminDTO convertAdminToDTO(User user,
                                           List<PlaysDTO> plays) {
        AdminDTO adminDto = new AdminDTO();
        adminDto = (AdminDTO) convertToDTO(adminDto, user);
        adminDto.setPlays(plays);

        return adminDto;
    }

    private static BaseUserDTO convertToDTO(BaseUserDTO userDto,
                                            User user) {
        userDto.setLogin(user.getLogin());
        userDto.setPicture(user.getPicture());
        return userDto;
    }
}
