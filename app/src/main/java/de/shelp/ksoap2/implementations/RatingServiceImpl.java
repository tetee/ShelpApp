package de.shelp.ksoap2.implementations;

import android.content.Context;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidRatingException;

/**
 * Die Klasse übergibt alle Daten die bezüglich der Bewertungen erstellt werden.
 * Die Daten werden über die Schnittstelle "RatingIntegration" übertragen.
 * {@link #createRating(User, int, String, int)}
 * {@link #getRatings(User, Context)}
 *
 * @author
 *
 */
public class RatingServiceImpl {

    private static final String URL = ServiceUtils.URL + "RatingIntegration";

    private static final String TAG = RatingServiceImpl.class.getName();

    /**
     * Die Methode erstellt eine Bewertung
     * @param targetUser - Benutzer der bewertet wird
     * @param rating - Bewertung der Fahrt
     * @param notice - Kurztext der Bewertung
     * @param sessionId - ID der aktuellen Session
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject createRating(User targetUser, int rating, String notice, int sessionId) throws SoapFault{
        String METHOD_NAME = "createRating";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, targetUser.getUserName(), rating, notice,  sessionId);

        return response;
    }

    /**
     *Mit der Methode können die Bewertungen abgefragt werden
     * @param user - Benutzer zu dem die Bewertung abgefragt wird
     * @param context - Kontext der Activity
     * @return gibt die Liste der gespeicherten Bewertungen zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws InvalidRatingException - die Bewertung konnte nicht verarbeitet werden
     */
    public List<Rating> getRatings(User user, Context context)throws SoapFault, InvalidRatingException{
        String METHOD_NAME = "getRatings";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, user.getUserName());
        List<Rating> ratings = new ArrayList<Rating>();

        if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new InvalidRatingException(response.getPrimitivePropertyAsString("message"));
        } else {
            for(int i = 1; i <= response.getPropertyCount()-1; i++) {
                ratings.add(SoapAssembler.getInstance().soapToRating((SoapObject)response.getProperty(i)));
            }
            return ratings;
        }
    }
}
