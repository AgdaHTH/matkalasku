/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.dao;

import java.sql.Connection;
import java.sql.SQLException;
import travelexpenses.domain.Bill;

/**
 * Dao-rajapinta Bill-luokalle.
 * 
 */
public interface BillDao {
    void create(Bill bill, Connection connection) throws SQLException;
    
}
