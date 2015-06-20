package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;

/**
 * Created by user on 21.05.15.
 */
public class StateServiceImpl {


    private static final String URL = ServiceUtils.URL + "StateIntegration";

    private static final String TAG = StateServiceImpl.class.getName();

    //Übergabe von AllLists (enthält Listen: Capacity, DeliveryConditions, PaymentConditions, ApprovalStatus, Location)

    public AllLists getLists() throws SoapFault{
        String METHOD_NAME = "getAllLists";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL);

        return SoapAssembler.getInstance().soapToAllLists(response);
    }
}
