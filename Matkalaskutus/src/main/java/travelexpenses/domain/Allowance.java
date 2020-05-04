/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Luokka sisältää päivärahan laskemiseen tarvittavat tiedot ja metodit.
 *
 */
public class Allowance {

    private LocalDate beginning;
    private LocalDate end;
    private boolean abroad;

    public Allowance(LocalDate beginning, LocalDate end, boolean abroad) {

        this.abroad = abroad;
        this.beginning = beginning;
        this.end = end;
    }

    public LocalDate getBeginningDate() {
        return this.beginning;
    }

    public LocalDate getEndDate() {
        return this.end;
    }

    /**
     * Metodi laskee päivien määrän loppu- ja alkupäivän erotuksena.
     *
     * @return Palauttaa päivien määrän long-tyypin lukuna.
     */
    public long countDays() {
        long days = ChronoUnit.DAYS.between(this.beginning, this.end);      
        return days;
    }

    /**
     * Metodi laskee päivärahan kokonaismäärän matkan keston perusteella.
     *
     * @return Palauttaa päivärahan kokonaismäärän.
     */
    public double countAllowance() {
        long days = this.countDays();
        double sumAllowance = days * this.getAllowance();
        
        return sumAllowance;
    }
    
    /**
     * Metodi palauttaa päivärahan päiväkohtaisen määrän riippuen siitä, onko matkakohde 
     * kotimaassa vai ulkomailla.
     * @return Päivärahan määrä päivässä.
     */
    public double getAllowance() {
        double allowance = 34.5;
        if (this.abroad == true) {
            allowance = 45.2;
        }
        
        return allowance;
    }
    
    

}
