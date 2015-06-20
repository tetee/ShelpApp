package de.shelp.ksoap2.implementations;

import android.util.Log;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidLoginException;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;
import de.shelp.ksoap2.exceptions.NoSessionException;
import de.shelp.ksoap2.services.UserService;

/**
 * Die Klasse übergibt alle Daten die bezüglich der Benutzer erstellt werden.
 * Die Daten werden über die Schnittstelle "UserIntegration" übertragen.
 * {@link #login(String, String)}
 * {@link #logout(de.shelp.android.applications.ShelpApplication)} )}
 * {@link #searchUsers(String)}
 * {@link #logout(de.shelp.android.applications.ShelpApplication)}
 *
 * @author
 *
 */
public class UserServiceImpl implements UserService {

    private static final String URL = ServiceUtils.URL + "UserIntegration";

    private static final String TAG = UserServiceImpl.class.getName();

    /**
     * Login eines Benutzers / Übergabe als ShelpSession
     *
     * @param username - Die E-Mail Adresse des Nutzers
     * @param password - Das Passwort des Nutzers
     * @return - Rückgabe einer ShelpSession
     * @throws InvalidLoginException - es kann keine Verbindung zum Server aufgebaut werden
     */
    @Override
    public ShelpSession login(String username, String password) throws InvalidLoginException {
        String METHOD_NAME = "login";
        SoapObject response = null;
        try {
            response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, username, password);
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

    /**
     * Registrieren eines Benutzers / Übergabe als ShelpSession.
     *
     * @param eMail - Die E-Mail Adresse des Benutzers
     * @param hash - Das gehashte Passwort
     * @return - Rückgabe einer ShelpSession
     * @throws InvalidRegistrationException - es kann keine Registrierung durchgeführt werden
     */
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

    /**
     * Ausloggen eines Benutzers
     *
     * @param shelpApplication - Das Application Objekt
     * @throws NoSessionException - es kann keine Session gefunden werden
     */
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

    /**
     * Benutzer suchen / Übergabe der Liste <User> an den Server
     *
     * @param searchText - Such Text für die Suche nach einem Benutzer
     * @return - gibt eine Liste von Benutzern zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws InvalidUsersException - Benutzer ist nicht valide
     */
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
