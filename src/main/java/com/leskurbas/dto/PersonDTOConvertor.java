package com.leskurbas.dto;

import com.leskurbas.model.Person;
import com.leskurbas.model.User;

public class PersonDTOConvertor {
    public static PersonDTO convertToDto(User user,
                                         Person person) {
        PersonDTO personDto = new PersonDTO();
        personDto.setId(user.getId());
        personDto.setSurname(person.getSurname());
        personDto.setName(person.getName());
        personDto.setEmail(person.getEmail());
        personDto.setLogin(user.getLogin());
        personDto.setPassword(user.getPassword());
        personDto.setPicture(user.getPicture());
        return personDto;
    }
}
