package de.shelp.android;

import org.ksoap2.SoapFault;

import java.util.Calendar;
import java.util.List;

import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.InvalidUsersException;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 05.05.15.
 */
public class UserServiceImplMock implements UserService {

    private User user;
    private Tour tour;

    public UserServiceImplMock() {}


    @Override
    public ShelpSession login(String username, String hash) throws InvalidLoginException {
        //Testuser anlegen
        if(hash.equals("cc03e747a6afbbcbf8be7668acfebee5")) {
            user = new User("busch.roman20@gmail.com");
        }
        return null;
    }

    @Override
    public void logout(ShelpApplication s) { this.user = null; }


    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException {
        user = new User("busch.roman20@gmail.com");
        return null;
    }

    public List<User> searchUsers(String searchText) throws SoapFault, InvalidUsersException {
        return null;
    }
}
