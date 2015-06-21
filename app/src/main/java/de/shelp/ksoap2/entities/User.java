package de.shelp.ksoap2.entities;

import java.io.Serializable;

/**
 * Klasse, die einen Benutzer respraesentiert. Umfasst eine E-Mailadresse (Id)
 *
 * @author Roman Busch
 *
 */
public class User implements Serializable{
    private String userName;

    public User(String userName) {
        this.userName = userName;

    }

    public String getUserName() { return this.userName; }

    @Override
    public String toString() {
        String retString = this.userName;
        return retString;
    }
}
