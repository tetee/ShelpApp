package de.fh_muenster.shelpapp.ShelpAppAndroid.tasks;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import de.fh_muenster.shelpapp.ShelpApp.AllLists;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.ReturnCode;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.ShelpSession;
import de.fh_muenster.shelpapp.ShelpApp.User;

/**
 * Created by user on 21.05.15.
 */
public class ShelpAppServiceStateImpl {

    /**
     * Namespace is the targetNamespace in the WSDL.
     */
    private static final String NAMESPACE = "http://integration.shelp.de/";

    /**
     * The WSDL URL. Its value is the location attribute of the soap:address element for a port
     * element in a WSDL. Unless the web service is also hosted on the Android device, the hostname
     * should not be specified as localhost, because the application runs on the Android device while
     * the web service is hosted on the localhost server. Specify hostname as the IP address of the
     * server hosting the web service (or "10.0.2.15 instead of 'localhost' when running in the emulator).
     */
    private static final String URL = "http://10.70.50.172:8080/shelp/StateIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = ShelpAppServiceStateImpl.class.getName();

    public AllLists getLists() throws SoapFault{
        String METHOD_NAME = "getAllLists";
        SoapObject response = null;

            response = executeSoapAction(METHOD_NAME);
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

    /**
     * Diese Methode delegiert einen Methodenaufruf an den hinterlegten WebService.
     * @param methodName
     * @return
     */
    private SoapObject executeSoapAction(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            @SuppressWarnings({"unused", "unchecked"})
            //List<HeaderProperty> respHeaders = androidHttpTransport.call(NAMESPACE + methodName, envelope, reqHeaders);
                    //fuehrt zu CXF-Fehler! neue Version ohne SOAP-Action funktioniert:
                    List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return (SoapObject) result;
    }
}
