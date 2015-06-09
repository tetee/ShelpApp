package de.shelp.ksoap2.services;

import org.ksoap2.SoapFault;

import java.util.Calendar;
import java.util.List;

import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 05.05.15.
 */
public interface UserService {

    //Methoden User Klasse
    public ShelpSession login(String userName, String hash) throws InvalidLoginException;

    public void logout(ShelpApplication shelpApplication) throws NoSessionException;

    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException;

    public List<User> searchUsers(String searchText) throws SoapFault;

}
