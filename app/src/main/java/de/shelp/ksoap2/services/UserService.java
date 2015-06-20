package de.shelp.ksoap2.services;

import org.ksoap2.SoapFault;

import java.util.List;

import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 05.05.15.
 */
public interface UserService {

    //Methoden User Klasse
    public ShelpSession login(String userName, String hash) throws InvalidLoginException;

    public void logout(ShelpApplication shelpApplication) throws NoSessionException;

    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException;

    public List<User> searchUsers(String searchText) throws SoapFault, InvalidUsersException;

}
