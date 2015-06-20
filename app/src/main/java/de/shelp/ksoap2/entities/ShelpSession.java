package de.shelp.ksoap2.entities;

import java.io.Serializable;
/**
 * Klasse die eine Session respr�sentiert. Umfasst eine Id und den Besitzer (
 * {@link User})
 *
 * @author
 *
 */
public class ShelpSession implements Serializable{

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
