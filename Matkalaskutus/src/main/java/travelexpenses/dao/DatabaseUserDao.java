/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import travelexpenses.domain.User;

/**
 *
 * @author Hilla
 */
public class DatabaseUserDao implements UserDao {

    public DatabaseUserDao() {

    }

    @Override
    public List<User> read(String username, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User "
                + "WHERE username = ?");
        stmt.setString(1, username);

        ResultSet resultSet = stmt.executeQuery();     
        
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User (resultSet.getInt("id"), resultSet.getString("surname"), 
                    resultSet.getString("forename"), resultSet.getString("username"));
            users.add(user);                    
        }
        return users;
    }

    @Override
    public void create(User user, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User"
                + " (surname, forename, username)"
                + " VALUES (?, ?, ?)");
        stmt.setString(1, user.getSurname());
        stmt.setString(2, user.getForename());
        stmt.setString(3, user.getUsername());

        stmt.executeUpdate();
        stmt.close();
        //connection.close();
        
    }
    
    @Override
    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE"
        + " username = ?");
        stmt.setString(1, user.getUsername());
        
        stmt.executeUpdate();
        stmt.close();
    }

}
