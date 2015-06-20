package de.shelp.ksoap2.entities;

import java.io.Serializable;

/**
 * Klasse, die einen Benutzer respr�sentiert. Umfasst eine E-Mailadresse (Id)
 *
 * @author
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
