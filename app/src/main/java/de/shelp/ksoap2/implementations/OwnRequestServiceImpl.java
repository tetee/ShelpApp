package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Created by user on 21.05.15.
 */
public class OwnRequestServiceImpl {


    private static final String URL = ServiceUtils.URL + "RequestIntegration";

    private static final String TAG = OwnRequestServiceImpl.class.getName();

    //Übergabe der Liste <Request> an den Server / Abfragen der Anfragen
    public List<Request> getRequest(int sessionId) throws SoapFault, InvalidRequestException {
        String METHOD_NAME = "getRequests";
        SoapObject response = null;

        try {
            response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId);
            if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
                throw new InvalidRequestException(response.getPrimitivePropertyAsString("message"));
            }

            List<Request> requests = new ArrayList<>();

            for (int i = 1; i < response.getPropertyCount(); i++) {
                requests.add(SoapAssembler.getInstance().soapToRequest((SoapObject) response.getProperty(i)));
            }

            return requests;

        } catch (SoapFault e) {
            throw new InvalidRequestException(e.getMessage());
        }
    }
}
