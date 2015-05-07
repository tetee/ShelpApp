package de.fh_muenster.shelpapp.ShelpApp;

/**
 * Created by user on 05.05.15.
 */
public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() { return this.userName; }

    @Override
    public String toString() {
        String retString = "User: " + this.userName;
        return retString;
    }
}
