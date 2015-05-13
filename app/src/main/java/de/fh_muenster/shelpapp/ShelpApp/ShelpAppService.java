package de.fh_muenster.shelpapp.ShelpApp;

import java.util.Calendar;
import java.util.List;

import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;

/**
 * Created by user on 05.05.15.
 */
public interface ShelpAppService {

    //Methoden User Klasse
    public User login(String userName, String password) throws InvalidLoginException;

    public void logout() throws NoSessionException;
/**
    //Methoden Tour Klasse
    public long getId();

    public void setId(long id);

    public ApprovalStatus getApprovalStatus();

    public void setApprovalStatus(ApprovalStatus approvalStatus);

    public Location getLocation();

    public void setLocation(Location location);

    public Capacity getCapacity();

    public void setCapacity(Capacity capacity);

    public PaymentCondition getPaymentConditions();

    public void setPaymentConditions(PaymentCondition paymentConditions);

    public DeliveryCondition getDeliveryConditions();

    public void setDeliveryConditions(DeliveryCondition deliveryConditions);

    public Calendar getTime();

    public void setTime(Calendar time);

    public User getOwner();

    public void setOwner(User owner);

    public Calendar getUpdatedOn();

    public void setUpdatedOn(Calendar updatedOn);

    public List<Request> getRequest();

    public void setRequest(List<Request> request);

    public TourStatus getStatus();

    public void setStatus(TourStatus status);

    public boolean isValid();*/

}
