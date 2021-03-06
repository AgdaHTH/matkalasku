/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelexpenses.dao.BillDao;
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
    private String username;
    protected Connection connection;

    public TravelExpensesApp(UserDao userdao, BillDao billdao) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
        this.userdao = userdao;
        this.billdao = billdao;
        this.username = "";
    }

    /**
     * Metodi alustaa tietokannan.
     */
    public void createDatabase() {
        
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
            Statement stmt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Connection to database failed.");
        }

        try {
            connection.prepareStatement("DROP TABLE IF EXISTS Bill;").executeUpdate();
            connection.prepareStatement("DROP TABLE IF EXISTS User;").executeUpdate();
            connection.prepareStatement("CREATE TABLE User (id integer PRIMARY KEY AUTOINCREMENT,"
                    + " surname varchar(20), forename varchar(15), username varchar(15));").executeUpdate();
            connection.prepareStatement("CREATE TABLE Bill (id integer PRIMARY KEY AUTOINCREMENT,"
                    + " destination varchar(50), begindate date, enddate date, expense1 float, expense2 float,"
                    + " expense3 float, allowance float, user_id integer, FOREIGN KEY (user_id) REFERENCES User(id));").executeUpdate();
                      
        } catch (SQLException ex2) {
        }   
    }
    

    /**
     * Metodi tarkastaa, löytyykö käyttäjänimi tietokannasta.
     * Jos löytyy, käyttäjänimeen liittyvä käyttäjä asetetaan
     * oliomuuttujan currentUser arvoksi.
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
            List<User> users = this.userdao.read(user.getUsername(), connection);
            if (users.isEmpty()) {
                this.userdao.create(user, connection);
                return true;
            }
            return false;
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
    public double getAllowance(LocalDate beginning, LocalDate end, boolean abroad) {
        Allowance allowance = new Allowance(beginning, end, abroad);
        return allowance.countAllowance();
    }

    /**
     * Metodi muuntaa String-muodossa olevan parametrin LocalDate-tyypin
     * päivämääräksi.
     *
     * @param date "YYYY-MM-DD" muodossa oleva päivämäärää ilmaiseva merkkijono
     * @return LocalDate-tyyppinen päivämäärä
     */
    public LocalDate convertDate(String date) {
        String[] parts = date.split("-");
        int year = Integer.valueOf(parts[0]);
        int month = Integer.valueOf(parts[1]);
        int day = Integer.valueOf(parts[2]);
        LocalDate convertedDate = LocalDate.of(year, month, day);
        return convertedDate;
    }

    /**
     * Metodi tarkastaa, onko parametrina annettu päivämäärä oikeanmuotoinen.
     * Vuoden tulee olla välillä 1900-kuluva vuosi, kuukausien muodossa 01, 02,
     * ..., 12 ja päivien muodossa 01, 02, ..., 31. Metodi ei tarkasta onko
     * päivämäärä mahdollinen, esimerkiksi tietyn kuukauden päivien lukumäärä tai
     * karkausvuosi. Metodi tarkastaa, että parametrina annettu päivämäärä
     * voidaan pilkkoa "-" merkin kohdalta kolmeen osaan.
     *
     * @param date Muotoa YYYY-MM-DD oleva päivämäärä
     * @return Onko päivämäärä oikeassa muodossa.
     */
    public boolean checkDate(String date) {
        String[] parts = date.split("-");
        int year = Integer.valueOf(parts[0]);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (parts.length != 3) {
            return false;
        }

        if (year < 1900 || year > currentYear) {
            return false;
        }

        String month = parts[1];
        if (!month.matches("0[1-9]{1}|1[012]{1}")) {
            return false;
        }

        String day = parts[2];
        if (!day.matches("0[1-9]{1}|1[0-9]{1}|2[0-9]{1}|30|31")) {
            return false;
        }

        return true;
    }

    /**
     * Metodi tarkastaa, että matkan alkupäivämäärä ei ole myöhempi kuin
     * loppupäivämäärä.
     *
     * @param beginDate Alkupäivämäärä
     * @param endDate Loppupäivämäärä
     * @return false jos ei ole myöhempi eli päivämäärät kelpaavat
     */
    public boolean checkBeginDateIsNotAfterEndDate(String beginDate, String endDate) {
        LocalDate start = this.convertDate(beginDate);
        LocalDate end = this.convertDate(endDate);
        boolean isAfter = start.isAfter(end);
        return isAfter;
    }

}
