package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 21.05.15.
 */
public class TourServiceImpl {


    private static final String URL = ServiceUtils.URL + "TourIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = TourServiceImpl.class.getName();

    public String createTour(Tour tour, int sessionId) throws SoapFault{
        String METHOD_NAME = "createTour";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL,null, tour.getApprovalStatus().getId(), tour.getLocation().getId(), tour.getCapacity().getId(), tour.getPaymentConditions().getId(),
                tour.getDeliveryConditions().getId(), tour.getTime(), sessionId);

        return response.getPrimitivePropertyAsString("returnCode");
    }


    public List<Tour> searchTour(int approvalStatus,long location, int capacity,long timeStart,long timeEnd,boolean directSearch,int sessionId) throws SoapFault{
        String METHOD_NAME = "searchTours";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL,null, approvalStatus, location, capacity, timeStart, timeEnd, directSearch, sessionId);
        List<Tour> tours = new ArrayList<Tour>();
        for(int i = 1; i<=response.getPropertyCount()-1; i++){
            tours.add(SoapAssembler.getInstance().soapToTour((SoapObject)response.getProperty(i)));
        }

        return tours;
    }
}
