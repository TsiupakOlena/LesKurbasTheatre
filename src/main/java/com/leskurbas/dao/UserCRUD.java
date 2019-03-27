package com.leskurbas.dao;

import com.leskurbas.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCRUD {

    public User getUserByID(long id) {
        User user = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                "SELECT Login, Password, IsAdmin, Picture FROM user WHERE ID=?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        user = new User(id,
                                rs.getString("Login"),
                                rs.getString("Password"),
                                rs.getByte("IsAdmin"));
                        user.setPicture(rs.getString("Picture"));
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return user;
        }
    }

    public int insertUser(User user) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO user(Login, Password, isAdmin) VALUES "
                        + "(?, ?, ?)")){
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, String.valueOf(user.isAdmin()));

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

    public int updateUser(User user) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user SET Login=?, Password=? WHERE ID=?")){
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, String.valueOf(user.getId()));

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

    public int updateUserPicture(long id, String picture) {
        int status = 0;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE user SET Picture=? WHERE ID=?")){
                ps.setString(1, picture);
                ps.setString(2, String.valueOf(id));

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

    public int deleteUser(long id) {
        int status = 0;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(
                    "DELETE FROM user WHERE ID=?");
            ps.setLong(1, id);
            status = ps.executeUpdate();

            connection.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
            return status;
        }
    }

    public User getUserByLoginAndPassword(String login,
                                          String password) {
        User user = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                "SELECT ID IsAdmin FROM user WHERE Login=? "
                    + "AND Password=?")) {
                ps.setString(1, login);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new User(
                                rs.getLong(1),
                                login, password,
                                rs.getByte("IsAdmin"));
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.commit();
            }
        } finally {
            return user;
        }
    }

    public long getUserIDByLogin(String login) {
        long id = -1;

        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT ID FROM user WHERE Login=?")) {
                ps.setString(1, login);
                try (ResultSet rs = ps.executeQuery()){

                    if (rs.next()) {
                        id = rs.getLong("ID");
                    }
                }
                connection.commit();
            } catch (SQLException se) {
                System.out.println(se);
                connection.rollback();
            }
        } finally {
            return id;
        }
    }
}