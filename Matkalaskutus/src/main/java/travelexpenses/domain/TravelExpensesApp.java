/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelexpenses.dao.BillDao;
import travelexpenses.dao.DatabaseBillDao;
import travelexpenses.dao.DatabaseUserDao;
import travelexpenses.dao.UserDao;

/**
 *
 * @author Hilla
 */
public class TravelExpensesApp {

    //tänne käyttöliittymälogiikka
    private UserDao userdao;
    private BillDao billdao;
    private User currentUser;
    protected Connection connection;

    public TravelExpensesApp(UserDao userdao, BillDao billdao) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
        this.userdao = userdao;
        this.billdao = billdao;
    }

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

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean createUser(User user) throws SQLException {
        try {
            this.userdao.create(user, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean addBill(Bill bill) {
        try {
            this.billdao.create(bill, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean deleteUser(User user) {
        try {
            this.userdao.delete(user, connection);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TravelExpensesApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void logout() {
        currentUser = null;
    }

    public void closeDatabaseConnection() throws SQLException {
        this.connection.close();
    }
}
