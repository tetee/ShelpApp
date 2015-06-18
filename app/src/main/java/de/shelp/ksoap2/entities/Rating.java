package de.shelp.ksoap2.entities;

/**
 * Created by Rome on 02.06.2015.
 */
import java.io.Serializable;

import de.shelp.ksoap2.entities.User;

public class Rating implements Serializable{

    private long id;
    private User sourceUser;
    private User targetUser;
    private float rating;
    private String notice;

    public Rating(long id, User sourceUser, User targetUser, float rating, String notice){
        this.id=id;
        this.sourceUser=sourceUser;
        this.targetUser=targetUser;
        this.rating=rating;
        this.notice=notice;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public User getSourceUser() {
        return sourceUser;
    }
    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }
    public User getTargetUser() {
        return targetUser;
    }
    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getNotice() {
        return notice;
    }
    public void setNotice(String notice) {
        this.notice = notice;
    }


}

