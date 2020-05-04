/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
/**
 *
 * @author Hilla
 */
public class Bill { 
    private int userid;
    private String destination;
    private LocalDate beginning;
    private LocalDate end;
    private boolean abroad;
    private double expense1;
    private double allowance;
    
    public Bill(int userid, String destination, LocalDate beginning, LocalDate end, 
            Double expense1, Double allowance) {
        this.userid = userid;
        this.destination = destination; 
        this.abroad = false;
        this.beginning = beginning;
        this.end = end;
        this.expense1 = expense1;
        this.allowance = allowance;
    }
    
//    public Bill(String destination, LocalDate beginning, LocalDate end) {
//        this.destination = destination;
//        this.beginning = beginning;
//        this.end = end;        
//    }
    
    public double getAllowance() {
        return this.allowance;
    }
    
    public int getUserid() {
        return this.userid;
    }
    
    public String getDestination() {
        return this.destination;
    }
    
    public LocalDate getBeginningDate() {
        return this.beginning;
    }
    
    public LocalDate getEndDate() {
        return this.end;
    }
    
    public void addExpense(double expense) {
        this.expense1 = expense;              
    }
    
    public double getExpense1() {
        return this.expense1;
    }                
    
//    public double countReimbursement() {
//        return countAllowance() + this.expense1;
//    }
        
    
}
