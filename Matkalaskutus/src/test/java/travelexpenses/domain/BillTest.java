package travelexpenses.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hilla
 */
public class BillTest {
    Bill bill; 
    
    //public BillTest() {
    //}
    
    @Before
    public void setUp() {
        bill = new Bill("Oxford", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5));
    }
    
    @Test
    public void createdBillExists() {
        assertTrue(bill!=null);  
    }
    
    @Test
    public void billIsPrintedCorrectly() {
    
    }
    
    @Test
    public void setAbroadFunctionsCorrectly() {
        bill.setAbroad();
        assertEquals(true, bill.getAbroad());
    }
    
    @Test
    public void beginningDateIsCorrect() {
        assertEquals(LocalDate.of(2020, 1, 1), bill.getBeginningDate()); 
    }
    
    @Test
    public void endDateIsCorrect(){
        assertEquals( LocalDate.of(2020, 1, 5), bill.getEndDate());
    }       
    
    @Test
    public void countDaysCountsDaysCorrectly(){       
        long days = ChronoUnit.DAYS.between(bill.getBeginningDate(), bill.getEndDate());
        assertEquals(4, days); //NB tämä nyt niin että loppupäivä ei ole mukana   
    }
    
    @Test
    public void countAllowanceCountsAllowanceCorrectlyHome(){
        assertEquals(138.0, bill.countAllowance(), 0.001);
    }   
    
    @Test
    public void countReimbursementCountsReimbursementCorrectly() {
        bill.addExpense(105.30);
        assertEquals(243.3, bill.countReimbursement(), 0.001);
    }
}
