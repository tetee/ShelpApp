package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Location;

/**
 * Created by user on 21.05.15.
 */
public class StateServiceImpl {


    private static final String URL = ServiceUtils.URL + "StateIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = StateServiceImpl.class.getName();

    public AllLists getLists() throws SoapFault{
        String METHOD_NAME = "getAllLists";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, null);

        return SoapAssembler.getInstance().soapToAllLists(response);
    }


}
