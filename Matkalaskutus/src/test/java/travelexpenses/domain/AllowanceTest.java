/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
public class AllowanceTest {
    Allowance allowance;
    public AllowanceTest() {
    }
    
    
    
    @Before
    public void setUp() {
        allowance = new Allowance(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 5));
    }
    
    @Test
    public void countDaysCountsDaysCorrectly(){       
        long days = ChronoUnit.DAYS.between(allowance.getBeginningDate(), allowance.getEndDate());
        assertEquals(4, days); //NB tämä nyt niin että loppupäivä ei ole mukana   
    }
    
    @Test
    public void countAllowanceCountsAllowanceCorrectlyHome(){
        assertEquals(138.0, allowance.countAllowance(), 0.001);
    }               
    
}
