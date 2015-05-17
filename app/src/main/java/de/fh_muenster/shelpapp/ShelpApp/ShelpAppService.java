package de.fh_muenster.shelpapp.ShelpApp;

import java.util.Calendar;
import java.util.List;

import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidRegistrationException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidTourException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;

/**
 * Created by user on 05.05.15.
 */
public interface ShelpAppService {

    //Methoden User Klasse
    public User login(String userName, String hash) throws InvalidLoginException;

    public void logout() throws NoSessionException;

    public User registration(String eMail, String hash) throws InvalidRegistrationException;

    public Tour newTour(long id, User owner, ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Calendar date) throws InvalidTourException;


}
