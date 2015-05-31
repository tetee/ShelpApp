package de.shelp.ksoap2.entities;


public class Location {

    private long id;
    private String description;
    private String zipcode;

    public Location(long id, String description, String zipcode){
        this.id = id;
        this.description= description;
        this.zipcode=zipcode;
    }

    public Location(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
