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
    
    @Before
    public void setUp() {
        bill = new Bill(1, "Oxford", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5), 
                157.8, 138.0);
    }
    
    @Test
    public void createdBillExists() {
        assertTrue(bill!=null);  
    }
    
       
    @Test
    public void beginningDateIsCorrect() {
        assertEquals(LocalDate.of(2020, 1, 1), bill.getBeginningDate()); 
    }
    
    @Test
    public void endDateIsCorrect(){
        assertEquals( LocalDate.of(2020, 1, 5), bill.getEndDate());
    }                     
    
}
