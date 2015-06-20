package de.shelp.ksoap2.entities;


import java.io.Serializable;

/**
 * Klasse, die einen Freigabestatus respr�sentiert. Umfasst eine Id und eine
 * Beschreibung.
 *
 * @author Theresa Sollert
 *
 */
public class ApprovalStatus implements Serializable{
    private int id;
    private String description;

    public ApprovalStatus( int id, String description) {
		this.id = id;
		this.description = description;
    }

    public ApprovalStatus() {
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
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ApprovalStatus other = (ApprovalStatus) obj;
	if (description == null) {
	    if (other.getDescription() != null)
		return false;
	} else if (!description.equals(other.getDescription()))
	    return false;
	return true;
    }

	@Override
	public String toString() {
		return description;
	}
}
