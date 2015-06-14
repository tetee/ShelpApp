package de.shelp.ksoap2.implementations;

import android.content.Context;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidRatingException;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Created by user on 21.05.15.
 */
public class RatingServiceImpl {


    private static final String URL = ServiceUtils.URL + "RatingIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = RatingServiceImpl.class.getName();

    public SoapObject createRating(User targetUser, int rating, String notice, int sessionId) throws SoapFault{
        String METHOD_NAME = "createRating";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, targetUser.getUserName(), rating, notice,  sessionId);

        return response;
    }

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
