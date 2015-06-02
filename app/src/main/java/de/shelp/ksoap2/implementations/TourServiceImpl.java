package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

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

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, tour, sessionId);

        return response.getPrimitivePropertyAsString("returnCode");
    }


}
