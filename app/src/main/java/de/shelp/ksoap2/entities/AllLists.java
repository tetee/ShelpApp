package de.shelp.ksoap2.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse, die alle Listen respraesentiert. Umfasst eine Liste Kapazitaeten, Lieferbedingungen, Zahlungsbedingungen, Freigabestatus und Orte.
 *
 * @author Theresa Sollert
 *
 */
public class AllLists {

    private List<Capacity> capacities;
    private List<DeliveryCondition> deliveryConditions;
    private List<PaymentCondition> paymentConditions;
    private List<ApprovalStatus> states;
    private List<Location> locations;

    public AllLists(List<Capacity> capacities, List<DeliveryCondition> deliveryConditions,
                    List<PaymentCondition> paymentConditions, List<ApprovalStatus> states, List<Location> locations){
        this.capacities = capacities;
        this.deliveryConditions = deliveryConditions;
        this.paymentConditions = paymentConditions;
        this.states = states;
        this.locations = locations;
    }

    public List<Capacity> getCapacities(){
        return this.capacities;
    }

    public List<ApprovalStatus> getStates() {
        return states;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<PaymentCondition> getPaymentConditions() {
        return paymentConditions;
    }

    public List<DeliveryCondition> getDeliveryConditions() {
        return deliveryConditions;
    }
}
