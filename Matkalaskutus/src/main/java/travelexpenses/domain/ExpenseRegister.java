/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

import java.util.ArrayList;

/**
 *
 * @author Hilla
 */
public class ExpenseRegister {
    private ArrayList<Bill> bills;
    
    public ExpenseRegister(){
        this.bills = new ArrayList<>();
    }
    
    public void addBill(Bill bill){
        this.bills.add(bill);
    }
    
    @Override
    public String toString(){
        String list = "";
        for (Bill bill : bills){
            list = list.concat(bill.toString() + "\n");
        }
        
        return list;
    }
}
