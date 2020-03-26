/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matkalaskutus;
import java.util.*;
/**
 *
 * @author Hilla
 */
public class TextUI {
    private Scanner reader;
    //luodaanko sovellusolio tässä vai pääohjelmassa kuten vinkattiin?
    
    public TextUI(Scanner reader){ // tarvitaanko tätätä konstruktoria ollenkaan?
        this.reader = reader;
    }
    
    public void run (){
    
        while(true){
            System.out.println("Choose one of the following: ");
            System.out.println(" x - exit");
            System.out.println(" 1 - add a new travel expenses statement");
            System.out.println(" 2 - edit an existing travel expenses statement");
            System.out.println(" 3 - list my expenses statements");
            System.out.println("");
            
            String command = reader.nextLine();
            if (command.equals("x")) {
                System.out.println("Auf Wiedersehen!");
                break;
            }
            
            else if (command.equals("1")){
                addBill(reader);                
            }
            
            else if (command.equals("2")){
                editBill(reader);
            }
            
            else if (command.equals("3")){
                System.out.println("Not ready yet :)!");
                System.out.println("");
            }
               
        }
    
    }
    
    private void addBill(Scanner reader){
        System.out.println("Welcome to add a travel expenses statement!");
        System.out.println("Please enter travel destination");
        String destination = reader.nextLine();
        System.out.println(destination);
        
    }
    
    private void editBill(Scanner reader){
        System.out.println("Welcome to edit your travel expenses statements!");
        System.out.println("Please choose statement by entering the travel destination");
        String destination = reader.nextLine();
        System.out.println(destination);
    }
    
    
    
}
