/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelexpenses.domain;

/**
 * Luokka sisältää käyttäjää koskevan tiedon käsittelemiseen tarvittavat metodit.
 * 
 */
public class User {

    private String surname;
    private String forename;
    private String username;
    private int id;

    public User(int id, String surname, String forename, String username) {
        this.surname = surname;
        this.forename = forename;
        this.username = username;
        this.id = id;
    }

    public User(String surname, String forename, String username) {
        this.surname = surname;
        this.forename = forename;
        this.username = username;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getForename() {
        return this.forename;
    }

}
