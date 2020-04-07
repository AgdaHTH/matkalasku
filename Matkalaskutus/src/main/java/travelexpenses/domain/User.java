/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

/**
 *
 * @author Hilla
 */
public class User {
    private String surname;
    private String forename;
    private String username;
    
    public User(String surname, String forename, String username) {
        this.surname = surname;
        this.forename = forename;
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public String toString() {
        return this.surname + " " + this.forename + ", " + this.username;
    }
    
}
