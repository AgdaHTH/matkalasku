/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.ui;
import java.util.Scanner;
import travelexpenses.domain.ExpenseRegister;
/**
 *
 * @author Hilla
 */
public class Main {
    
    public static void main (String[] args){
        System.out.println("Welcome!");
        Scanner reader = new Scanner(System.in);
        ExpenseRegister register = new ExpenseRegister();
        TextUI ui = new TextUI(reader, register);
        
        ui.run();
    }
    
}
