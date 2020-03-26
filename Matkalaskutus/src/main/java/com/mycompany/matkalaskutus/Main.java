/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matkalaskutus;
import java.util.Scanner;
/**
 *
 * @author Hilla
 */
public class Main {
    
    public static void main (String[] args){
        System.out.println("hei");
        Scanner reader = new Scanner(System.in);
        System.out.println("Mitä");
        String koe = reader.nextLine();
        System.out.println(koe);
        TextUI ui = new TextUI(reader);
        ui.run();
    }
    
}
