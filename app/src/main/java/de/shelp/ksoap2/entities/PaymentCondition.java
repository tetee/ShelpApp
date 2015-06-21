package de.shelp.ksoap2.entities;


import java.io.Serializable;

/**
 * Klasse, die eine Bezahlmethode respraesentiert. Umfasst eine Id und eine
 * Beschreibung.
 *
 * @author Theresa Sollert
 *
 */
public class PaymentCondition implements Serializable{

    private int id;
    private String description;

    public PaymentCondition() {
    }

    public PaymentCondition(int id, String description) {
	    super();
        this.id = id;
	    this.description = description;
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
