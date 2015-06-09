package de.shelp.ksoap2.entities;

import java.io.Serializable;

/**
 * Created by user on 05.05.15.
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
