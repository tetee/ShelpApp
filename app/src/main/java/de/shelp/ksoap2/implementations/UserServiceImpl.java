package de.shelp.ksoap2.implementations;

import android.util.Log;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;
import de.shelp.ksoap2.exceptions.NoSessionException;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.User;



/**
 * Diese Klasse enthaelt die Verbindung der App mit dem Xbank-Webservice.
 */
public class UserServiceImpl implements UserService {

    private static final String URL = ServiceUtils.URL + "UserIntegration";
    private static final String URL2 = ServiceUtils.URL + "StateIntegration";

    private static final String TAG = UserServiceImpl.class.getName();

    //Methoden Klasse User

    //Login eines Benutzers / Übergabe als ShelpSession
    @Override
    public ShelpSession login(String username, String password) throws InvalidLoginException {
        String METHOD_NAME = "login";
        SoapObject response = null;
        try {
            response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, username, password);
           // response = ServiceUtils.executeSoapAction("getAllLists", URL2);
            Log.d(TAG, response.toString());
            String login = (response.getPrimitivePropertySafelyAsString("returnCode"));

            if(login.equals("OK")) {
                return SoapAssembler.getInstance().soapToSession(response);
            } else {
                throw new InvalidLoginException(response.getPrimitivePropertyAsString("message"));
            }
        } catch (SoapFault e) {
            throw new InvalidLoginException(e.getMessage());
        }
    }

    //registrieren eines Benutzers / Übergabe als ShelpSession
    @Override
    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException {
        String METHOD_NAME = "regUser";
        SoapObject response = null;
        try {
            response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, eMail, hash);
            Log.d(TAG, response.toString());
            String reg = (response.getPrimitivePropertySafelyAsString("returnCode"));

            if(reg.equals(ReturnCode.ERROR.toString())) {
                throw new InvalidRegistrationException(response.getPrimitivePropertyAsString("message"));
            }

            return SoapAssembler.getInstance().soapToSession(response);
        } catch (SoapFault e) {
            throw new InvalidRegistrationException(e.getMessage());
        }
    }

    //ausloggen eines Benutzers
    @Override
    public void logout(ShelpApplication shelpApplication) throws NoSessionException {
        Log.d(TAG, "logout called.");
        String METHOD_NAME = "logout";
        try {

            SoapObject response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, shelpApplication.getSession().getId());
            Log.d(TAG, response.toString());
        } catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }
    }


    //Benutzer suchen / Übergabe der Liste <User> an den Server
    @Override
    public List<User> searchUsers(String searchText) throws SoapFault, InvalidUsersException{
        String METHOD_NAME = "searchUsers";
        SoapObject response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, searchText);

        if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new InvalidUsersException(response.getPrimitivePropertyAsString("message"));
        }

        List<User> users = new ArrayList<>();
        for(int i = 1; i < response.getPropertyCount(); i++) {
           users.add( SoapAssembler.getInstance().soapToUser((SoapObject) response.getProperty(i)));
        }

        return users;
    }
}
