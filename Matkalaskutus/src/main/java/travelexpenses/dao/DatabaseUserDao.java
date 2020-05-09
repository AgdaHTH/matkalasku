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
 * Luokka toteuttaa rajapinnan UserDao ja se tarjoaa metodit käyttäjää koskevan 
 * tietokannassa olevan tiedon luomiseen, lukemiseen ja poistamiseen.
 * 
 */
public class DatabaseUserDao implements UserDao {

    public DatabaseUserDao() {

    }

    /**
     * Metodi lukee tietokannan User-taulusta käyttäjän tiedot.
     *
     * @param username Käyttäjän käyttäjänimi
     * @param connection JDBC-tietokantayhteys
     * @return Lista käyttäjistä
     * @throws SQLException Jos käyttäjän tietojen lukeminen ei onnistu
     */
    @Override
    public List<User> read(String username, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User "
                + "WHERE username = ?");
        stmt.setString(1, username);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(resultSet.getInt("id"), resultSet.getString("surname"),
                    resultSet.getString("forename"), resultSet.getString("username"));
            users.add(user);
        }
        return users;
    }

    /**
     * Metodi luo tietokannan User-tauluun uuden rivin.
     *
     * @param user Uusi käyttäjä
     * @param connection JDBC-tietokantayhteys
     * @throws SQLException Heittää poikkeuksen, jos uuden käyttäjän luominen ei
     * onnistu
     */
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
    }

    /**
     * Metodi poistaa tietokannan User-taulusta käyttäjän tiedot.
     *
     * @param user Poistettava käyttäjä
     * @param connection JDBC-tietokantayhteys
     * @throws SQLException Jos käyttäjän tietojen poistaminen ei onnistu
     */
    @Override
    public void delete(User user, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE"
                + " username = ?");
        stmt.setString(1, user.getUsername());

        stmt.executeUpdate();
        stmt.close();
    }

}
