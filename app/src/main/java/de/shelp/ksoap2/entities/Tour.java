package de.shelp.ksoap2.entities;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

public class Tour implements KvmSerializable {

    private static final long serialVersionUID = 4333669660990735973L;

    private long id;
    private String approvalStatus;
    private Location location;
    private String capacity;
    private String paymentConditions;
    private String deliveryConditions;
    private Calendar time;
    private List<Request> request;
    private User owner;
    private Calendar updatedOn;
    private TourStatus status;

    public Tour(long id, String approval, Location location,String capacity, String payCondition, String delCondition, Calendar date, List<Request> request, User owner,Calendar updatedOn, TourStatus status){
        this.id = id;
        this.approvalStatus=approval;
        this.location = location;
        this.capacity=capacity;
        this.paymentConditions=payCondition;
        this.deliveryConditions=delCondition;
        this.time=date;
        this.request= request;
        this.owner=owner;
        this.updatedOn=updatedOn;
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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public String getDeliveryConditions() {
        return deliveryConditions;
    }

    public void setDeliveryConditions(String deliveryConditions) {
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
