package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 21.05.15.
 */
public class RatingServiceImpl {


    private static final String URL = ServiceUtils.URL + "RatingIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = RatingServiceImpl.class.getName();

    public String createRating(User targetUser, int rating, String notice, int sessionId) throws SoapFault{
        String METHOD_NAME = "createRating";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, targetUser.getUserName(), rating, notice,  sessionId);

        return response.getPrimitivePropertyAsString("returnCode");
    }
}
