/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import travelexpenses.domain.Bill;

/**
 * Luokka toteuttaa rajapinnan BillDao ja se käsittelee tietokannan
 * Bill-taulussa olevaa tietoa.
 */
public class DatabaseBillDao implements BillDao {

    public DatabaseBillDao() {

    }

    /**
     * Metodi luo tietokannan Bill-tauluun uuden rivin eli uuden matkalaskun
     *
     * @param bill Lasku-olio
     * @param connection JDBC-tietokantayhteys
     * @throws SQLException Heittää poikkeuksen, jos rivin kirjoittaminen
     * tietokantaan ei onnistu
     */
    @Override
    public void create(Bill bill, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Bill"
                + " (destination, begindate, enddate, user_id, expense1, allowance)"
                + " VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, bill.getDestination());
        java.sql.Date sqlbeginDate = java.sql.Date.valueOf(bill.getBeginningDate());
        stmt.setDate(2, sqlbeginDate);
        java.sql.Date sqlendDate = java.sql.Date.valueOf(bill.getEndDate());
        stmt.setDate(3, sqlendDate);
        stmt.setInt(4, bill.getUserid());
        stmt.setDouble(5, bill.getExpense1());
        stmt.setDouble(6, bill.getAllowance());

        stmt.executeUpdate();
        stmt.close();

        connection.close();
    }

}
