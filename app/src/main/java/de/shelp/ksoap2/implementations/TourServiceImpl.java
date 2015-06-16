package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidRatingException;
import de.shelp.ksoap2.exceptions.InvalidRequestException;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 21.05.15.
 */
public class TourServiceImpl {


    private static final String URL = ServiceUtils.URL + "TourIntegration";

    private static final String TAG = TourServiceImpl.class.getName();

    //erstellen einer Fahrt / Übergabe als SoapObject
    public SoapObject createTour(Tour tour, int sessionId) throws SoapFault{
        String METHOD_NAME = "createTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tour.getApprovalStatus().getId(), tour.getLocation().getId(), tour.getCapacity().getId(), tour.getPaymentConditions().getId(),
                tour.getDeliveryConditions().getId(), tour.getTime(), sessionId);

        return response;
    }


    //suchen einer Fahrt / Übergabe  der Liste <Tour> an den Server
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

    //suchen der eigenen Fahrten / Übergabe der Liste <Tour> an den Server
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


    //löschen einer Tour / Übergabe als SoapObject
    public SoapObject deleteTour(long tourId, int sessionId) throws SoapFault{
        String METHOD_NAME = "deleteTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tourId, sessionId);

        return response;
    }

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
