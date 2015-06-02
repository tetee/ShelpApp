package de.shelp.ksoap2.entities;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Tour implements KvmSerializable {

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

    @Override
    public Object getProperty(int arg0) {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        // TODO Auto-generated method stub
        arg2.name = "string";
        arg2.type = PropertyInfo.STRING_CLASS;
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getInnerText() {
        return null;
    }

    @Override
    public void setInnerText(String s) {

    }
}
