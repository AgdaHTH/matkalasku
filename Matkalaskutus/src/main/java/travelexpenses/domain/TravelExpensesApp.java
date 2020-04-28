/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelexpenses.dao.BillDao;
import travelexpenses.dao.CreateDatabase;
import travelexpenses.dao.DatabaseBillDao;
import travelexpenses.dao.DatabaseUserDao;
import travelexpenses.dao.UserDao;

/**
 * Luokka sisältää matkalaskujärjestelmän sovelluslogiikan.
 *
 *
 */
public class TravelExpensesApp {

    private UserDao userdao;
    private BillDao billdao;
    private User currentUser;
    protected Connection connection;

    public TravelExpensesApp(UserDao userdao, BillDao billdao) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
        this.userdao = userdao;
        this.billdao = billdao;
        createDatabase();
    }

    /**
     * Metodi alustaa tietokannan luomalla CreateDatabase -olion.
     */
    public void createDatabase() {
        CreateDatabase database = new CreateDatabase();
    }

    /**
     * Metodi tarkastaa, löytyykö käyttäjänimi tietokannasta.
     *
     * @param username Käyttäjän käyttäjänimi
     * @return Onnistuiko kirjautuminen.
     */
    public boolean login(String username) {
        try {
            if (userdao.read(username, connection).size() == 1) {
                currentUser = userdao.read(username, connection).get(0);
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodi palauttaa sisäänkirjautuneen käyttäjän (User).
     *
     * @return Kirjautunut käyttäjä
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Metodi luo tietokantaan uuden käyttäjän.
     *
     * @param user Käyttäjä
     * @return Onnistuiko käyttäjän luominen.
     *
     */
    public boolean createUser(User user) {
        try {
            this.userdao.create(user, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodi luo tietokantaan uuden matkalaskun.
     *
     * @param bill Matkalasku
     * @return Onnistuiko matkalaskun luominen
     */
    public boolean addBill(Bill bill) {
        try {
            this.billdao.create(bill, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Metodi poistaa käyttäjän tietokannasta
     *
     * @param user Poistettava käyttäjä
     * @return Onnnistuiko poistaminen.
     */
    public boolean deleteUser(User user) {
        try {
            this.userdao.delete(user, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodi kirjaa käyttäjän ulos järjestelmästä, eli asettaa
     * currentUser-muuttujan arvoksi null.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Metodi sulkee tietokantayhteyden.
     *
     * @throws SQLException Jos tietokantayhteyden sulkeminen ei onnistu
     */
    public void closeDatabaseConnection() throws SQLException {
        this.connection.close();
    }

    /**
     * Metodi palauttaa päivärahan määrän alku- ja loppupäivämäärien
     * perusteella.
     *
     * @param beginning Matkan alkupäivämäärä
     * @param end Matkan loppupäivämäärä
     * @return Palauttaa päivärahan määrän.
     */
    public double getAllowance(LocalDate beginning, LocalDate end) {
        Allowance allowance = new Allowance(beginning, end);
        return allowance.countAllowance();
    }
}
