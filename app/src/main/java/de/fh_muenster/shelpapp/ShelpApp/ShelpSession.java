package de.fh_muenster.shelpapp.ShelpApp;

import java.util.Calendar;

public class ShelpSession {

    private int id;
    private User user;

    public ShelpSession(int id, User user) {
        this.id = id;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
