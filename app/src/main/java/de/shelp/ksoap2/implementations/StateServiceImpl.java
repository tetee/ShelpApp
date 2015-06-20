package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.AllLists;

/**
 * Die Klasse übergibt alle Listen.
 * Die Listen werden über die Schnittstelle "StateIntegration" übertragen.
 * {@link #getLists()}
 *
 * @author
 *
 */
public class StateServiceImpl {


    private static final String URL = ServiceUtils.URL + "StateIntegration";

    private static final String TAG = StateServiceImpl.class.getName();

    //Übergabe von AllLists (enthält Listen: Capacity, DeliveryConditions, PaymentConditions, ApprovalStatus, Location)

    /**
     * Abfrage aller Listen
     * @return Rückgabe des Objekts AllLists
     * @throws SoapFault - es kann keine Verbindung zum Server aufgebaut werden
     */
    public AllLists getLists() throws SoapFault{
        String METHOD_NAME = "getAllLists";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL);

        return SoapAssembler.getInstance().soapToAllLists(response);
    }
}
