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
        this.testbill = new Bill("Oxford", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5));
    }

    @Test
    public void userIsCreated() throws SQLException {
        User user = new User("Kepa", "Keppinen", "keppi");
        boolean success = application.createUser(user);
        assertEquals(true, success);
        application.deleteUser(user);
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

}
