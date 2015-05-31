package de.shelp.ksoap2.implementations;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Calendar;
import java.util.List;

import de.shelp.android.applications.SessionApplication;
import de.shelp.ksoap2.ServiceSupporter;
import de.shelp.ksoap2.SoapTransformer;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
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

    private static final String URL = ServiceSupporter.URL + "/UserIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = UserServiceImpl.class.getName();

    //Methoden Klasse User
    @Override
    public ShelpSession login(String username, String password) throws InvalidLoginException {
        String METHOD_NAME = "login";
        SoapObject response = null;
        try {
            response = ServiceSupporter.executeSoapAction(METHOD_NAME, username, password);
            Log.d(TAG, response.toString());
            String login = (response.getPrimitivePropertySafelyAsString("returnCode"));
            if(login.equals(ReturnCode.ERROR.toString())) {
               throw new InvalidLoginException("Login invalid!");
            }

            return SoapTransformer.getInstance().soapToSession(response);
        } catch (SoapFault e) {
            throw new InvalidLoginException(e.getMessage());
        }
    }

    @Override
    public ShelpSession registration(String eMail, String hash) throws InvalidRegistrationException {
        User user = null;
        ShelpSession session = null;
        String METHOD_NAME = "regUser";
        SoapObject response = null;
        try {
            response = ServiceSupporter.executeSoapAction(METHOD_NAME, eMail, hash);
            Log.d(TAG, response.toString());
            String reg = (response.getPrimitivePropertySafelyAsString("returnCode"));

            if(reg.equals(ReturnCode.ERROR.toString())) {
                throw new InvalidRegistrationException("Registration invalid!");
            }

            return SoapTransformer.getInstance().soapToSession(response);
        } catch (SoapFault e) {
            throw new InvalidRegistrationException(e.getMessage());
        }
    }


    @Override
    public void logout(SessionApplication sessionApplication) throws NoSessionException {
        Log.d(TAG, "logout called.");
        String METHOD_NAME = "logout";
        try {

            SoapObject response = ServiceSupporter.executeSoapAction(METHOD_NAME, sessionApplication.getSession().getId());
            Log.d(TAG, response.toString());
        } catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }
    }


    @Override
    public Tour newTour(long id, ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Calendar date, List<Request> request, User owner,Calendar updatedOn, TourStatus status) throws InvalidTourException{
        return null;
    }
}
