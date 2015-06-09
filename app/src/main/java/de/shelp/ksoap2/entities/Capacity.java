package de.shelp.ksoap2.entities;


import java.io.Serializable;

public class Capacity implements Serializable{

    private int id;
    private String description;

    public Capacity(int id, String description) {
	super();
        this.id = id;
	    this.description = description;
    }

    public Capacity() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
