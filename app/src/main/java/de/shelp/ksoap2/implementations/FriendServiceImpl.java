package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Friendship;

/**
 * Die Klasse übergibt alle Daten die bezüglich der Freundesliste erstellt werden.
 * Die Daten werden über die Schnittstelle "FriendIntegration" übertragen.
 * {@link #getFriends(int)}
 * {@link #addFriend(int, String)}
 * {@link #changeFriendship(int, Friendship, int)}
 *
 * @author
 *
 */
public class FriendServiceImpl {

    private static final String URL = ServiceUtils.URL + "FriendIntegration";

    private static final String TAG = FriendServiceImpl.class.getName();

    /**
     * Mit der Methode können die aktuellen Freunde abgefragt werden.
     * @param sessionId - ID der aktuellen Session
     * @return - gibt die Liste der gespeicherten Freunde zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     *
     */
    public List<Friendship> getFriends(int sessionId) throws SoapFault {
        String METHOD_NAME = "getFriends";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId);

        List<Friendship> friendships = new ArrayList<>();

        for(int i = 1; i < response.getPropertyCount(); i++) {
            friendships.add( SoapAssembler.getInstance().soapToFriendship((SoapObject) response.getProperty(i)));
        }

        return friendships;
    }

    /**
     * Methode um Freunde zur Freundesliste hinzuzufügen
     * @param sessionId - ID der aktuellen Session
     * @param friendId - ID des Benutzer mit dem die Freundschaft abgeschlossen werden soll
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
   public SoapObject addFriend(int sessionId, String friendId) throws SoapFault {
       String METHOD_NAME = "addFriend";
       SoapObject response = null;

       response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId, friendId);

       return response;
   }

    /**
     * Methode zum ändern des Freundschaftsstatus
     * @param sessionId - ID der aktuellen Session
     * @param friendship - Übergabe der Freundschaft
     * @param changeType - akzeptieren / ablehnen / löschen
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject changeFriendship(int sessionId, Friendship friendship, int changeType) throws SoapFault {
        String METHOD_NAME;
        switch (changeType) {
            case 0:
                METHOD_NAME  = "acceptFriendship";
                break;
            case 1:
                METHOD_NAME  = "deniedFriendship";
                break;
            default:
                METHOD_NAME  = "deleteFriendship";
                break;
        }
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, friendship.getId(), sessionId);

        return response;
    }
}
