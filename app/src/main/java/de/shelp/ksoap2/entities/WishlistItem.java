package de.shelp.ksoap2.entities;

import java.io.Serializable;

/**
 * Created by user on 16.05.15.
 */
public class WishlistItem implements Serializable {

    private int id;
    private String text;
    private boolean checked;

    public WishlistItem(int id, String text, boolean checked) {
        this.id = id;
        this.text = text;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
