package com.leskurbas.dao;

import com.leskurbas.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCRUD {

    public int insertPerson(Person person) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
             try (PreparedStatement ps = connection.prepareStatement(
                         "INSERT INTO person(Surname, Name, Email, UserID)"
                                 + " VALUES (?, ?, ?, ?)")) {
                ps.setString(1, person.getSurname());
                ps.setString(2, person.getName());
                ps.setString(3, person.getEmail());
                ps.setString(4, String.valueOf(person.getUserID()));
                status = ps.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                 System.out.println(e);
                 connection.rollback();
            }
        } finally {
            return status;
        }
    }

    public long getPersonIDByEmail(String email) {
        long id = -1;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                "SELECT ID FROM person WHERE Email=?")) {

                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        id = rs.getLong("ID");
                    }
                    connection.commit();
                }
            } catch (SQLException e) {
                System.out.println(e);
                connection.rollback();
            }
        } finally {
            return id;
        }
    }

    public Person getPersonByUserId(long id) {
        Person person = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT Surname, Name, Email FROM person WHERE UserID=?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        person = new Person(
                                rs.getString("Email"),
                                rs.getString("Surname"),
                                rs.getString("Name")
                        );
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return person;
        }
    }

    public int updatePerson(Person person, long userId) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE person SET Surname=?, Name=?, Email=?"
                            + " WHERE UserID=?")){
                ps.setString(1, person.getSurname());
                ps.setString(2, person.getName());
                ps.setString(3, person.getEmail());
                ps.setString(4, String.valueOf(userId));

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
}
