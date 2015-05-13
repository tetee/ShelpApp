package de.fh_muenster.shelpapp.ShelpApp;

import java.util.Calendar;
import java.util.List;

public class Tour {

    private long id;
    private ApprovalStatus approvalStatus;
    private Location location;
    private Capacity capacity;
    private PaymentCondition paymentConditions;
    private DeliveryCondition deliveryConditions;
    private Calendar time;
    private List<Request> request;
    private User owner;
    private Calendar updatedOn;
    private TourStatus status;

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

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Calendar getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Calendar updatedOn) {
        this.updatedOn = updatedOn;
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
        return approvalStatus != null && location != null && capacity != null && paymentConditions != null && deliveryConditions != null && time != null;
    }

}
