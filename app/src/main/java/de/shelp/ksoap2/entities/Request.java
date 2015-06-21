package de.shelp.ksoap2.entities;

import java.io.Serializable;
import java.util.List;

import de.shelp.android.WishlistActivity;

/**
 * Klasse, die eine Anfrage respraesentiert. Umfasst eine Id, den Ersteller (
 * {@link User}), den Empfaenger ({@link User}), eine Fahrt ({@link Tour}), eine
 * Liste mit Wuenschen {@link WishlistItem}, eine Beschreibung und einen Status.
 *
 * @author Roman Busch
 *
 */
public class Request implements Serializable{

    private long id;
    private User sourceUser;
    private User targedUser;
    private Tour tour;
    private List<WishlistItem> wishes;
    private String notice;
    private String status;

    public Request(long id, User sourceUser, User targedUser, Tour tour, List<WishlistItem> wishes, String notice, String status) {
        this.id = id;
        this.sourceUser = sourceUser;
        this.targedUser = targedUser;
        this.tour = tour;
        this.wishes = wishes;
        this.notice = notice;
        this.status = status;
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

    public User getTargedUser() {
        return targedUser;
    }

    public void setTargedUser(User targedUser) {
        this.targedUser = targedUser;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<WishlistItem> getWishes() {
        return wishes;
    }

    public void setWishes(List<WishlistItem> wishes) {
        this.wishes = wishes;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

