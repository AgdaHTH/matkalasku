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
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import travelexpenses.dao.BillDao;
import travelexpenses.dao.DatabaseBillDao;
import travelexpenses.dao.DatabaseUserDao;
import travelexpenses.dao.UserDao;

/**
 *
 * @author Hilla
 */
public class TravelExpensesAppTest {

    TravelExpensesApp application;
    Bill testbill;
    DatabaseUserDao userdao;
    DatabaseBillDao billdao;

    public TravelExpensesAppTest() {
    }

    @Before
    public void setUp() throws SQLException {
        this.userdao = new DatabaseUserDao();
        this.billdao = new DatabaseBillDao();
        this.application = new TravelExpensesApp(userdao, billdao);
        this.testbill = new Bill(1, "Oxford", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5),
            157.8, 138.0);
    }

    @Test
    public void userIsCreated() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        boolean success = application.createUser(user);
        assertEquals(true, success);
        application.deleteUser(user);
    }
    
    @Test
    public void userIsNotCreated() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        application.createUser(user);
        User anotherUser = new User("Keppo", "Kepponen", "keppi");
        boolean success = application.createUser(anotherUser);
        assertEquals(false, success);
        application.deleteUser(user);
        application.deleteUser(anotherUser);
    }   

    @Test
    public void billIsCreated() throws SQLException {
        boolean success = application.addBill(testbill);
        assertEquals(true, success);
    }

    @Test
    public void loginWhenUserExists() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        application.createUser(user);
        boolean success = application.login("keppi");
        assertEquals(true, success);
        application.deleteUser(user);
    }

    @Test
    public void deleteUserDeletesUser() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        application.createUser(user);
        boolean success = application.deleteUser(user);
        assertEquals(true, success);
    }

    @Test
    public void loginWhenUserDoesNotExist() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        application.createUser(user);
        application.deleteUser(user);
        boolean success = application.login(user.getUsername());
        assertEquals(false, success);
    }
    
    @Test 
    public void checkDateReturnsTrueWhenDateIsOk() {
        String date = "2019-12-05";
        boolean success = application.checkDate(date);
        assertEquals(true, success);
    }
    
    @Test
    public void checkDateReturnsFalseWhenNumberOfPartsIsNotThree() {
        String date = "2019-08";
        boolean success = application.checkDate(date);
        assertEquals(false, success);
    }
    
    @Test 
    public void checkDateReturnsFalseWhenYearIsWrong() {
        String date = "2021-12-05";
        boolean success = application.checkDate(date);
        assertEquals(false, success);
    }
    
    @Test 
    public void checkDateReturnsFalseWhenMonthIsWrong() {
        String date = "2019-13-05";
        boolean success = application.checkDate(date);
        assertEquals(false, success);
    }
    
    @Test 
    public void checkDateReturnsFalseWhenDayIsWrong() {
        String date = "2019-12-32";
        boolean success = application.checkDate(date);
        assertEquals(false, success);
    }
    
    @Test
    public void convertDateReturnsCorrectDate() {
        String dateString = "2020-01-05";
        LocalDate date = application.convertDate("2020-01-05");
        assertEquals(LocalDate.of(2020, 1, 5), date);
    }
    
    @Test
    public void checkBeginDateIsNotAfterEndDateWorks() {
        String beginDate = "2020-01-05";
        String endDate ="2020-01-04";
        boolean success = application.checkBeginDateIsNotAfterEndDate(beginDate, endDate);
        assertEquals(true, success);
    }
    
    @Test
    public void getAllowanceReturnsCorrectAllowanceHome() {
        LocalDate beginDate = LocalDate.of(2020, 1, 5);
        LocalDate endDate = LocalDate.of(2020, 1, 8);
        boolean abroad = false;
        double allowance = application.getAllowance(beginDate, endDate, abroad);
        assertEquals(103.5, allowance, 0.001);
    }
    
    @Test
    public void getAllowanceReturnsCorrectAllowanceAbroad() {
        LocalDate beginDate = LocalDate.of(2020, 1, 5);
        LocalDate endDate = LocalDate.of(2020, 1, 8);
        boolean abroad = true;
        double allowance = application.getAllowance(beginDate, endDate, abroad);
        assertEquals(135.6, allowance, 0.001);
    }

}
