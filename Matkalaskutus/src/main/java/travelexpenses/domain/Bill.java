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
public class Bill { //pitäisikö tässäkin olla takana interface?
    private String destination;
    private LocalDate beginning;
    private LocalDate end;
    private boolean abroad;
    private double expenses; //vai joku lista tai mappi?
    
    public Bill(String destination, LocalDate beginning, LocalDate end){
        this.destination = destination; //maa täytyy tietää päivärahan määrää varten
        this.abroad = false;
        this.beginning = beginning;
        this.end = end;
    }
    public Bill(String destination){
        this.destination = destination;        
    }
    
    public void setAbroad(){
        this.abroad = true;
    }   
    
    //public void setBeginningDate(LocalDate beginning){ //vai tuleeko nämä jo konstruktorissa?
    //    this.beginning = beginning;
    //}
    
    //public void setEndDate (LocalDate end){
    //    this.end = end;
    //}
    
    public LocalDate getBeginningDate(){
        return this.beginning;
    }
    
    public LocalDate getEndDate(){
        return this.end;
    }
    
    public void addExpense(double expense){
        
    }
    
    public long countDays(){
        long days = ChronoUnit.DAYS.between(this.beginning, this.end);
        //NB tuossa ei ole mukana loppupäivämäärää mutta ei ehkä haittaa
        //tai sitten return days + 1
        return days;
    }
    
    
    public double countAllowance(){
        long days = this.countDays(); 
        double allowance = 34.5;
        if (this.abroad == true){
            allowance = 45.2;
        }
        double sumAllowance = days * allowance;
        return sumAllowance;
    }
    
    @Override
    public String toString (){
        return this.destination + ", " + this.beginning + ", " + this.end;
    }
    
    
}