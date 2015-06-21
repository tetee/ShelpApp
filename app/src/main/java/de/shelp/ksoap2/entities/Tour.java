package de.shelp.ksoap2.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Klasse, die eine Fahrt respraesentiert. Umfasst eine Id, den Ersteller (
 * {@link User}), einen Freigabestatus ({@link ApprovalStatus}), eine Kapazitaet
 * ({@link Capacity}), eine Bezahlmethode ({@link PaymentCondition}), eine
 * Liefermethode ({@link DeliveryCondition}), einen Status ({@link TourStatus}),
 * eine Liste von Anfragen ({@link Request}), einen Ort ({@link Location}), ein
 * Flag ob die Fahrt veraendert wurde und ein Zeitpunkt.
 *
 * @author Roman Busch
 *
 */
public class Tour implements Serializable {

    private static final long serialVersionUID = 4333669660990735973L;

    private long id;
    private ApprovalStatus approvalStatus;
    private Location location;
    private Capacity capacity;
    private PaymentCondition paymentConditions;
    private DeliveryCondition deliveryConditions;
    private long time;
    private List<Request> request;
    private User owner;
    private TourStatus status;

    public Tour(long id, ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Long date, List<Request> request, User owner, TourStatus status){
        this.id = id;
        this.approvalStatus=approval;
        this.location = location;
        this.capacity=capacity;
        this.paymentConditions=payCondition;
        this.deliveryConditions=delCondition;
        this.time=date;
        this.request= request;
        this.owner=owner;
        this.status=status;
    }
    public Tour(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public PaymentCondition getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(PaymentCondition paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public DeliveryCondition getDeliveryConditions() {
        return deliveryConditions;
    }

    public void setDeliveryConditions(DeliveryCondition deliveryConditions) {
        this.deliveryConditions = deliveryConditions;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public TourStatus getStatus() {
        return status;
    }

    public void setStatus(TourStatus status) {
        this.status = status;
    }

    public boolean isValid() {
        return approvalStatus != null && location != null && capacity != null && paymentConditions != null && deliveryConditions != null;
    }

}
