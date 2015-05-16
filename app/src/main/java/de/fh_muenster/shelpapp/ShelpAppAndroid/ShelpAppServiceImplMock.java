package de.fh_muenster.shelpapp.ShelpAppAndroid;

import java.util.Calendar;

import de.fh_muenster.shelpapp.ShelpApp.ApprovalStatus;
import de.fh_muenster.shelpapp.ShelpApp.Capacity;
import de.fh_muenster.shelpapp.ShelpApp.DeliveryCondition;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidTourException;
import de.fh_muenster.shelpapp.ShelpApp.Location;
import de.fh_muenster.shelpapp.ShelpApp.PaymentCondition;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.Tour;
import de.fh_muenster.shelpapp.ShelpApp.TourStatus;
import de.fh_muenster.shelpapp.ShelpApp.User;

/**
 * Created by user on 05.05.15.
 */
public class ShelpAppServiceImplMock implements ShelpAppService {

    private User user;

    public ShelpAppServiceImplMock() {}

    @Override
    public User login(String username, String password) throws InvalidLoginException {
        //Testuser anlegen
        user = new User("busch.roman20@gmail.com", "test123");
        return this.user;
    }

    @Override
    public void logout() { this.user = null; }

    @Override
    public Tour newTour(long id, User owner,ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Calendar date) throws InvalidTourException{
        return null;
    }


}
