package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.WishesList;
import de.shelp.ksoap2.entities.WishlistItem;

/**
 * Created by user on 10.06.15.
 */
public class RequestServiceImpl {


    private static final String URL = ServiceUtils.URL + "RequestIntegration";

    private static final String TAG = RequestServiceImpl.class.getName();


    //Anfragen erstellen / Übergabe der Parameter als SoapObject
    public SoapObject createRequest(Long tourId, List<String> wishes, String notice, int sessionId) throws SoapFault{
        String METHOD_NAME = "createRequest";
        SoapObject response = null;
        String wishesString = "";
        for(String wish :wishes){
            wishesString += wish + "\n";
        }


        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tourId, notice, sessionId, wishesString);

        return response;
    }

    //Anfragen löschen / Übergabe der Parameter als SoapObject
    public SoapObject deleteRequest(int sessionId, long requestId) throws SoapFault{
        String METHOD_Name = "deleteRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_Name, URL, requestId, sessionId);

        return response;
    }
}
