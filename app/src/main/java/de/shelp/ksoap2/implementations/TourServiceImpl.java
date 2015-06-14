package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidRequestException;
import de.shelp.ksoap2.exceptions.InvalidTourException;

/**
 * Created by user on 21.05.15.
 */
public class TourServiceImpl {


    private static final String URL = ServiceUtils.URL + "TourIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = TourServiceImpl.class.getName();

    public SoapObject createTour(Tour tour, int sessionId) throws SoapFault{
        String METHOD_NAME = "createTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tour.getApprovalStatus().getId(), tour.getLocation().getId(), tour.getCapacity().getId(), tour.getPaymentConditions().getId(),
                tour.getDeliveryConditions().getId(), tour.getTime(), sessionId);

        return response;
    }


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

    public SoapObject deleteTour(long tourId, int sessionId) throws SoapFault{
        String METHOD_NAME = "deleteTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tourId, sessionId);

        return response;
    }
}
