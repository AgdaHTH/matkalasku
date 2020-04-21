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
 *
 * @author Hilla
 */
public class DatabaseBillDao implements BillDao {

    public DatabaseBillDao() {

    }

    @Override
    public void create(Bill bill, Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Bill"
                + " (destination, begindate, enddate)"
                + " VALUES (?, ?, ?)");
        stmt.setString(1, bill.getDestination());
        java.sql.Date sqlbeginDate = java.sql.Date.valueOf(bill.getBeginningDate());
        stmt.setDate(2, sqlbeginDate);
        java.sql.Date sqlendDate = java.sql.Date.valueOf(bill.getEndDate());
        stmt.setDate(3, sqlendDate);

        stmt.executeUpdate();
        stmt.close();
        //kannattaako sulkea täällä?
        connection.close();
    }

}
