package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.NoSessionException;

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

        return ServiceUtils.executeSoapAction(METHOD_NAME, URL, tourId, notice, sessionId, wishesString);

    }

    //Anfragen löschen / Übergabe der Parameter als SoapObject
    public SoapObject deleteRequest(int sessionId, long requestId) throws SoapFault{
        String METHOD_Name = "deleteRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_Name, URL, requestId, sessionId);

        return response;
    }


    public List<Request> getUpdatedRequests(int sessionId) throws  SoapFault, NoSessionException {
        String METHOD_NAME = "getUpdatedRequests";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId);
        List<Request> requests = new ArrayList<Request>();

        if (response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new NoSessionException(response.getPrimitivePropertyAsString("message"));
        } else {
            for (int i = 1; i <= response.getPropertyCount() - 1; i++) {
                requests.add(SoapAssembler.getInstance().soapToRequest((SoapObject) response.getProperty(i)));
            }
        }
        return requests;
    }

    public SoapObject acceptRequest(long requestId, int sessionId, String idChecked) throws SoapFault{
        String METHOD_NAME = "acceptRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, requestId, idChecked, sessionId);

        return response;

    }
}
