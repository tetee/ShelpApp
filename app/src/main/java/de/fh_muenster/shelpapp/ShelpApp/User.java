package de.fh_muenster.shelpapp.ShelpApp;

/**
 * Created by user on 05.05.15.
 */
public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;

    }

    public String getUserName() { return this.userName; }

    @Override
    public String toString() {
        String retString = "User: " + this.userName;
        return retString;
    }
}
