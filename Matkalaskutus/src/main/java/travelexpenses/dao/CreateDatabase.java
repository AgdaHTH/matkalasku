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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hilla
 */
public class CreateDatabase {

    private Connection connection;
    private Statement stmt;

    public CreateDatabase() {

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
            this.stmt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Connection to database failed.");
        }

        try {
            connection.prepareStatement("CREATE TABLE User (id integer AUTO_INCREMENT PRIMARY KEY,"
                    + " surname varchar(20), forename varchar(15), username varchar(15));").executeUpdate();
            connection.prepareStatement("CREATE TABLE Bill (id integer AUTO_INCREMENT PRIMARY KEY,"
                    + " destination varchar(50), begindate date, enddate date, expenses float,"
                    + " user_id integer, FOREIGN KEY (user_id) REFERENCES User(id));").executeUpdate();
                      
        } catch (SQLException ex2) {
        }
        
    }
       
}
    
    


