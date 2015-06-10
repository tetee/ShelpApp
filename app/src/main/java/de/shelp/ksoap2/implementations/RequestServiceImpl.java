package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.WishlistItem;

/**
 * Created by user on 10.06.15.
 */
public class RequestServiceImpl {


    private static final String URL = ServiceUtils.URL + "RequestIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = RequestServiceImpl.class.getName();


    public String createRequest(String targedUserId,Long tourId, List<String> wishes, String notice, int sessionId) throws SoapFault{
        String METHOD_NAME = "createRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL,wishes, targedUserId, tourId, notice, sessionId);

        return response.getPrimitivePropertyAsString("returnCode");
    }
}
