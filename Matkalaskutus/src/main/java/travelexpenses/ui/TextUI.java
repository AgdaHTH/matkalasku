/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.ui;
import java.sql.Connection;
import java.sql.SQLException;
import travelexpenses.domain.Bill;
import travelexpenses.domain.ExpenseRegister;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import travelexpenses.dao.BillDao;
import travelexpenses.dao.DatabaseBillDao;
import travelexpenses.dao.DatabaseUserDao;
import travelexpenses.dao.UserDao;
import travelexpenses.domain.TravelExpensesApp;
/**
 *
 * @author Hilla
 */
public class TextUI {
    private Scanner reader;  
    protected Connection connection;
    
    public TextUI(Scanner reader, Connection connection) { 
        this.reader = reader;
        this.connection = connection;
    }
    
    public void run () throws SQLException {         
        DatabaseBillDao billdao = new DatabaseBillDao();
        DatabaseUserDao userdao = new DatabaseUserDao();
        TravelExpensesApp application = new TravelExpensesApp(userdao, billdao, this.connection);
        
        while(true){
            System.out.println("Choose one of the following: ");
            System.out.println(" x - exit");
            System.out.println(" 1 - add a new travel expenses statement");
            System.out.println(" 2 - edit an existing travel expenses statement");
            System.out.println(" 3 - list my expenses statements");
            System.out.println("");
            
            String command = reader.nextLine();
            if (command.equals("x")) {
                System.out.println("Auf Wiedersehen!");
                break;
            }
            
            else if (command.equals("1")){
                addBill(reader, application);                
            }
            
            else if (command.equals("2")){
                editBill(reader);
                System.out.println("Not ready yet!");
            }
            
            else if (command.equals("3")){
                System.out.println("Not ready yet!");
                printBills();
                System.out.println("");
            }
               
        }
    
    }
    
    private void addBill(Scanner reader, TravelExpensesApp application) throws SQLException {
        System.out.println("Welcome to add a travel expenses statement!");
        System.out.println("Please enter travel destination");
        String destination = reader.nextLine();
        
        System.out.println("Please enter beginning date (YYYY-MM-DD)");
        String date1 = reader.nextLine();
        String [] parts1 = date1.split("-");
        int year1 = Integer.valueOf(parts1[0]);
        int month1 = Integer.valueOf(parts1[1]);
        int day1 = Integer.valueOf(parts1[2]);
        LocalDate beginning = LocalDate.of(year1, month1, day1);
        
        System.out.println("Please enter end date (YYYY-MM-DD)");        
        String date2 = reader.nextLine();
        String [] parts2 = date2.split("-");
        int year2 = Integer.valueOf(parts2[0]);
        int month2 = Integer.valueOf(parts1[1]);
        int day2 = Integer.valueOf(parts1[2]);
        LocalDate end = LocalDate.of(year2, month2, day2);
        
        Bill bill = new Bill(destination, beginning, end);
        application.addBill(bill);
    }
    
    private void printBills(){
        //System.out.println(this.register.toString());
    }
    
    private void editBill(Scanner reader){
        System.out.println("Welcome to edit your travel expenses statements!");
        System.out.println("Please choose statement by entering the travel destination");
        String destination = reader.nextLine();
        System.out.println(destination);
    }
    
    
    
}
