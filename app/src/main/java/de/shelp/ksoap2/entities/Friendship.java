package de.shelp.ksoap2.entities;

/**
 * Entität die einen Freundschaft respräsentiert. Umfasst eine Id, Ersteller (
 * {@link User}), Empfänger ({@link User}), Status
 * und ein Änderungsdatum.
 *
 * @author
 *
 */
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
            return recipientUser.getUserName() + " und " + initiatorUser.getUserName() + " sind Freunde.";
        } else if(status.equals("ASKED")) {
            return "Anfrage von " + initiatorUser.getUserName() + " zu " + recipientUser.getUserName();
        } else if(status.equals("DENIED")) {
            return "Anfrage zwischen " + recipientUser.getUserName() + " und " + initiatorUser.getUserName() + " wurde abgelehnt.";
        }
        return "Keine Freundschaft";
    }

}
