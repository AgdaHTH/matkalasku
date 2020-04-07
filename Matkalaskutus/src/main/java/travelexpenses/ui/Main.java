/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.ui;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelexpenses.domain.ExpenseRegister;
import travelexpenses.dao.CreateDatabase;
/**
 *
 * @author Hilla
 */
public class Main {
    
    static Connection conn;
    //lopulta pitää olla niin, että pakettien ulkopuolella oleva main 
    //kutsuu GUI:ssa olevaa mainia, jolloin allaoleva siirretään sinne 
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome!");
        Scanner reader = new Scanner(System.in);
        //tietokanta luodaan
        //CreateDatabase database = new CreateDatabase();
        //jos tietokanta on jo olemassa, luodaan vain yhteys siihen
        getConnection();
        TextUI ui = new TextUI(reader, conn);       
        ui.run();
        closeConnection(); //missä tämän pitää olla?       
    }
    
    public static void closeConnection() throws SQLException {
        conn.close();
    }
    public static void getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:travelexpenses.db");
    }
    
}
