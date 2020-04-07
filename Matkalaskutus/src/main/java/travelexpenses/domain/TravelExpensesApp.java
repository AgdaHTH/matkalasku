/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.sql.Connection;
import java.sql.SQLException;
import travelexpenses.dao.BillDao;
import travelexpenses.dao.UserDao;

/**
 *
 * @author Hilla
 */
public class TravelExpensesApp {
    //tänne käyttöliittymälogiikka
    private UserDao userdao;
    private BillDao billdao;
    protected Connection connection;
    
    public TravelExpensesApp(UserDao userdao, BillDao billdao, Connection connection) { //ehkä nuo daot annetaan konstruktorissa?
        this.connection = connection;
        this.userdao = userdao;
        this.billdao = billdao;
    
    }
    
    public void addBill(Bill bill) throws SQLException {
        this.billdao.create(bill, connection);
    }
}
