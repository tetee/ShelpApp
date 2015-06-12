package de.shelp.ksoap2.entities;

import java.util.Calendar;

public class Friendship {

    private int id;
    private User initiatorUser;
    private User recipientUser;
    private String status;
    private long changedOn;

    public Friendship(int id, User initiatorUser, User recipientUser, String status, long changeDate) {
        this.id = id;
        this.initiatorUser = initiatorUser;
        this.recipientUser = recipientUser;
        this.status = status;
        this.changedOn = changeDate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getInitiatorUser() {
	return initiatorUser;
    }

    public void setInitiatorUser(User initiatorUser) {
	this.initiatorUser = initiatorUser;
    }

    public User getRecipientUser() {
	return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
	this.recipientUser = recipientUser;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public long getChangedOn() {
	return changedOn;
    }

    public void setChangedOn(long changedOn) {
	this.changedOn = changedOn;
    }

    @Override
    public String toString() {
        if(status.equals("ACCEPT")) {
            return "Befreundet mit " + recipientUser.getUserName();
        } else if(status.equals("ASKED")) {
            return "Anfrage von " + initiatorUser.getUserName() + " zu " + recipientUser.getUserName();
        } else if(status.equals("DENIED")) {
            return "Anfrage wurde von " + recipientUser.getUserName() + " abgelehnt.";
        }
        return "Keine Freundschaft";
    }

}
