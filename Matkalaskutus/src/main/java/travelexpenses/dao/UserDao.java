/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import travelexpenses.domain.User;

/**
 *
 * @author Hilla
 */
public interface UserDao {
    void create(User user, Connection connection) throws SQLException;
    List<User> read (String user, Connection connection) throws SQLException;
    void delete (User user, Connection connection) throws SQLException;
}
