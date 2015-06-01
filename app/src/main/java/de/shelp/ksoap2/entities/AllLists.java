package de.shelp.ksoap2.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.05.15.
 */
public class AllLists {

    private List<String> capacities;
    private List<String> deliveryConditions;
    private List<String> paymentConditions;
    private List<String> states;
    private List<Location> locations;

    public AllLists(List<String> capacities, List<String> deliveryConditions,
                    List<String> paymentConditions, List<String> states, List<Location> locations){
        this.capacities = capacities;
        this.deliveryConditions = deliveryConditions;
        this.paymentConditions = paymentConditions;
        this.states = states;
        this.locations = locations;
    }

    public List<String> getCapacities(){
        return this.capacities;
    }

    public List<String> getStates() {
        return states;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<String> getPaymentConditions() {
        return paymentConditions;
    }

    public List<String> getDeliveryConditions() {
        return deliveryConditions;
    }
}
