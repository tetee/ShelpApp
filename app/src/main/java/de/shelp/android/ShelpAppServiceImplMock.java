package de.shelp.android;

import java.util.Calendar;
import java.util.List;

import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.services.ShelpAppService;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 05.05.15.
 */
public class ShelpAppServiceImplMock implements ShelpAppService {

    private User user;
    private Tour tour;

    public ShelpAppServiceImplMock() {}


    @Override
    public ShelpSession login(String username, String hash) throws InvalidLoginException {
        //Testuser anlegen
        if(hash.equals("cc03e747a6afbbcbf8be7668acfebee5")) {
            user = new User("busch.roman20@gmail.com");
        }
        return null;
    }

    @Override
    public void logout() { this.user = null; }

    @Override
    public Tour newTour(long id, ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Calendar date, List<Request> request, User owner,Calendar updatedOn, TourStatus status) throws InvalidTourException{
        Location loc = new Location(12345, "Münster", "48149");
        tour = new Tour(1234,ApprovalStatus.ALL, loc, Capacity.LARGE_TRUNK, PaymentCondition.BAR, DeliveryCondition.BRING, Calendar.getInstance(), null,  this.user, null, null);
        return this.tour;

    }

    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException {
        user = new User("busch.roman20@gmail.com");
        return null;
    }
}