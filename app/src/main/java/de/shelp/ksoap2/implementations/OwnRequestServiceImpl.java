package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Friendship;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Die Klasse übergibt alle Daten zu den eigenen Anfragen.
 * Die Daten werden über die Schnittstelle "RequestIntegration" übertragen.
 * {@link #getRequest(int)} (int)}
 *
 * @author Theresa Sollert
 *
 */
public class OwnRequestServiceImpl {


    private static final String URL = ServiceUtils.URL + "RequestIntegration";

    private static final String TAG = OwnRequestServiceImpl.class.getName();

    /**
     * Mit der Methode können alle Anfragen abgefragt werden.
     * @param sessionId - ID der aktuellen Session
     * @return - gibt die Liste der gespeicherten Requests zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws InvalidRequestException - die Anfrage konnte nicht verarbeitet werden
     *
     */

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
