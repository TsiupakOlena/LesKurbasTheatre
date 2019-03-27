package com.leskurbas.service;

import com.leskurbas.dao.PersonCRUD;
import com.leskurbas.dao.UserCRUD;
import com.leskurbas.dto.*;
import com.leskurbas.exceptions.CreationException;
import com.leskurbas.exceptions.UpdateException;
import com.leskurbas.model.Person;
import com.leskurbas.model.User;

import java.util.List;

public class UserPersonService {

    private UserCRUD userdao = new UserCRUD();
    private PersonCRUD persondao = new PersonCRUD();
    private ReservationService reservationService =
            new ReservationService();
    private PlaySessionService playSessionService =
            new PlaySessionService();

    public void createUserAndPerson(User user, Person person)
            throws  CreationException {
        long newUserId;
        int state;

        state = userdao.insertUser(user);
        if(state < 0) {
            throw new CreationException("Could not create user!");
        }

        newUserId = userdao.getUserIDByLogin(user.getLogin());
        if(newUserId < 0) {
            throw new CreationException("Could not create user!");
        }

        person.setUserID(newUserId);
        state = persondao.insertPerson(person);
        if(newUserId < 0) {
            throw new CreationException("Could not create person!");
        }
    }

    public long getUserIdByLogin(String login) {
        long result = userdao.getUserIDByLogin(login);
        return result;
    }

    public boolean userLoginExists(String login) {
        long result = userdao.getUserIDByLogin(login);
        if (result < 0) {
            return false;
        }
        return true;
    }

    public boolean personEmailExists(String email) {
        long result = persondao.getPersonIDByEmail(email);
        if (result < 0) {
            return false;
        }
        return true;
    }

    public User getUserByLoginPassword(String login, String password) {
        return userdao.getUserByLoginAndPassword(login,password);
    }

    public User getUserByLogin(String login) {
        long id = getUserIdByLogin(login);
        if (id > 0) {
            return userdao.getUserByID(id);
        }
        return null;
    }

    public UserDTO getUserDtoByLogin(String login) {
        long id = userdao.getUserIDByLogin(login);
        User user = userdao.getUserByID(id);

        List<ReservationsDTO> reservations = reservationService.
                getAllReservationsDto(id);
        UserDTO userDto = UserConvertor.convertUserToDTO(user, reservations);

        return userDto;
    }

    public AdminDTO getAdminDtoByLogin(String login) {
        long id = userdao.getUserIDByLogin(login);
        User user = userdao.getUserByID(id);

        List<PlaysDTO> plays = playSessionService.
                getAllPlaysDTO();
        AdminDTO adminDto = UserConvertor
                .convertAdminToDTO(user, plays);

        return adminDto;
    }

    public PersonDTO getPersonDtoById(long id) {
        PersonDTO personDto;
        User user = userdao.getUserByID(id);
        Person person = persondao.getPersonByUserId(id);
        personDto = PersonDTOConvertor.convertToDto(user,
                person);
        return personDto;
    }

    public void updateUserPerson(User user, Person person)
            throws UpdateException {
        int result;
        result = userdao.updateUser(user);
        if (result < 1) {
            throw new UpdateException("Cannot update user!");
        }

        String picture = user.getPicture();
        if (picture != null) {
            result = userdao.updateUserPicture(user.getId(),
                    picture);
            if (result < 1) {
                throw new UpdateException("Cannot update user"
                        + "profile picture!");
            }
        }

        result = persondao.updatePerson(person,
                user.getId());
        if (result < 1) {
            throw new UpdateException("Cannot update "
                    + "person!");
        }
    }
}