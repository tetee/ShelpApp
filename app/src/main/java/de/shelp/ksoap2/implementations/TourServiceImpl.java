package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Die Klasse übergibt alle Daten die bezüglich der Fahrten erstellt werden.
 * Die Daten werden über die Schnittstelle "TourIntegration" übertragen.
 * {@link #createTour(de.shelp.ksoap2.entities.Tour, int)}
 * {@link #searchTour(int, long, int, long, long, boolean, int)}
 * {@link #deleteTour(long, int)}
 * {@link #getUpdatedTours(int)}
 * {@link #searchOwnTour(int)}
 *
 * @author
 *
 */
public class TourServiceImpl {

    private static final String URL = ServiceUtils.URL + "TourIntegration";

    private static final String TAG = TourServiceImpl.class.getName();

    /**
     *Erstellen einer Fahrt / Übergabe als SoapObject
     *
     * @param tour - Tour, die erstellt werden soll
     * @param sessionId - ID der aktuellen Session
     * @return - gibt ein SoapObject zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject createTour(Tour tour, int sessionId) throws SoapFault{
        String METHOD_NAME = "createTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tour.getApprovalStatus().getId(), tour.getLocation().getId(), tour.getCapacity().getId(), tour.getPaymentConditions().getId(),
                tour.getDeliveryConditions().getId(), tour.getTime(), sessionId);

        return response;
    }

    /**
     * Suchen einer Fahrt / Übergabe der Fahrtenliste an den Server
     *
     * @param approvalStatus - Freigabestatus der gesuchten Fahrt
     * @param location - Zielort der gesuchten Fahrt
     * @param capacity - Kapazität der gesuchten Fahrt
     * @param timeStart - Startzeit für die Fahrtensuche
     * @param timeEnd - Endzeit für die Fahrtensuche
     * @param directSearch - Auswahl ob in der Nähe gesucht werden soll
     * @param sessionId - ID der aktuellen Session
     * @return - gibt eine Liste der gesuchten Fahrten zurück
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws InvalidTourException - Fahrt ist nicht valide
     */
    public List<Tour> searchTour(int approvalStatus,long location, int capacity,long timeStart,long timeEnd,boolean directSearch,int sessionId) throws SoapFault, InvalidTourException{
        String METHOD_NAME = "searchTours";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, approvalStatus, location, capacity, timeStart, timeEnd, directSearch, sessionId);
        if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new InvalidTourException(response.getPrimitivePropertyAsString("message"));
        }
        List<Tour> tours = new ArrayList<Tour>();
        for(int i = 1; i<=response.getPropertyCount()-1; i++){
            tours.add(SoapAssembler.getInstance().soapToTour((SoapObject)response.getProperty(i)));
        }

        return tours;
    }

    /**
     * Suchen der eigenen Fahrten / Übergabe der Fahrtenliste an den Server
     *
     * @param sessionId - ID der aktuellen Session
     * @return - gibt eine Liste derangefragten Fahrten aus
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws InvalidTourException - Tour ist nicht valide
     */
    public List<Tour> searchOwnTour(int sessionId) throws SoapFault, InvalidTourException{
        String METHOD_NAME = "getTours";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL,sessionId);
        if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new InvalidTourException(response.getPrimitivePropertyAsString("message"));
        }
        List<Tour> tours = new ArrayList<Tour>();
        for(int i = 1; i<=response.getPropertyCount()-1; i++){
            tours.add(SoapAssembler.getInstance().soapToTour((SoapObject)response.getProperty(i)));
        }

        return tours;
    }

    /**
     * Löschen einer Fahrt / Übergabe als SoapObject
     *
     * @param tourId - ID der zu löschenden Fahrt
     * @param sessionId - ID der aktuellen Session
     * @return - Rückgabe eines SoapObjects
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public SoapObject deleteTour(long tourId, int sessionId) throws SoapFault{
        String METHOD_NAME = "deleteTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tourId, sessionId);

        return response;
    }

    /**
     * Abfrage ob es Änderungen bei einer Fahrt gibt
     *
     * @param sessionId - ID der aktuellen Session
     * @return - Rückgabe einer Liste der angefragten Fahrten
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     * @throws NoSessionException -
     */
    public List<Tour> getUpdatedTours(int sessionId) throws  SoapFault, NoSessionException {
        String METHOD_NAME = "getUpdatedTours";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId);
        List<Tour> tours = new ArrayList<Tour>();

        if(response.getPrimitivePropertyAsString("returnCode").equals("ERROR")) {
            throw new NoSessionException(response.getPrimitivePropertyAsString("message"));
        } else {
            for(int i = 1; i <= response.getPropertyCount()-1; i++) {
                tours.add(SoapAssembler.getInstance().soapToTour((SoapObject) response.getProperty(i)));
            }
        }
        return tours;
    }



}
