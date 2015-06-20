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
 * Die Klasse übergibt alle Daten die bezüglich der Anfragen erstellt werden.
 * Die Daten werden über die Schnittstelle "RequestIntegration" übertragen.
 * {@link #createRequest(Long, List, String, int)}
 * {@link #deleteRequest(int, long)}
 * {@link #getUpdatedRequests(int)}
 * {@link #acceptRequest(long, int, String)}
 *
 * @author
 *
 */
public class RequestServiceImpl {


    private static final String URL = ServiceUtils.URL + "RequestIntegration";

    private static final String TAG = RequestServiceImpl.class.getName();

    /**
     * Mit der Methode können neue Anfragen gestellt werden.
     * @param tourId - ID zu der aktuell angefragten Fahrt
     * @param wishes - aktuelle Liste der Wünsche
     * @param notice - Kurztext zu den aktuellen Wünschen
     * @param sessionId - ID der aktuellen Session
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
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

    /**
     * Mit der Methode können Anfragen gelöscht werden.
     * @param sessionId - ID der aktuellen Session
     * @param requestId - ID der aktuell zu löschenden Anfrage
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject deleteRequest(int sessionId, long requestId) throws SoapFault{
        String METHOD_Name = "deleteRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_Name, URL, requestId, sessionId);

        return response;
    }


    /**
     * Mit der Methode können die aktualisierten Anfragen abgefragt werden.
     * @param sessionId - ID der aktuellen Session
     * @return - gibt eine Liste der Anfragen zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws NoSessionException - Keine gültige Session
     */
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

    /**
     * Methode um Anfragen zu akzeptieren
     * @param requestId - ID der akzeptierten Anfrage
     * @param sessionId - ID der aktuellen Session
     * @param idChecked - ID der angenommenen Wünsche
     * @return - gibt den response als SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject acceptRequest(long requestId, int sessionId, String idChecked) throws SoapFault{
        String METHOD_NAME = "acceptRequest";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, requestId, idChecked, sessionId);

        return response;

    }
}
