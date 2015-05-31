package de.shelp.ksoap2.implementations;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceSupporter;
import de.shelp.ksoap2.entities.AllLists;

/**
 * Created by user on 21.05.15.
 */
public class StateServiceImpl {


    private static final String URL = ServiceSupporter.URL + "/StateIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = StateServiceImpl.class.getName();

    public AllLists getLists() throws SoapFault{
        String METHOD_NAME = "getAllLists";
        SoapObject response = null;

            response = ServiceSupporter.executeSoapAction(METHOD_NAME);
            //Log.d(TAG, response.toString());
            String test = (response.getPrimitivePropertySafelyAsString("returnCode"));
            SoapObject listResponse = (SoapObject) response.getProperty(1);
            SoapObject capacity = (SoapObject) listResponse.getProperty(0);
        List<String> capList = new ArrayList<String>();
        for(int i = 0; i <capacity.getPropertyCount(); i++){
            capList.add(capacity.getProperty(i).toString());
            System.out.print(capList.get(i));
        }


        return new AllLists(capList);
    }


}
