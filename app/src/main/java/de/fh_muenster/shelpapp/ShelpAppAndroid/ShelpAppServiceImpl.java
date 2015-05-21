package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Calendar;
import java.util.List;

import de.fh_muenster.shelpapp.ShelpApp.ApprovalStatus;
import de.fh_muenster.shelpapp.ShelpApp.Capacity;
import de.fh_muenster.shelpapp.ShelpApp.DeliveryCondition;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidRegistrationException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidTourException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;
import de.fh_muenster.shelpapp.ShelpApp.Location;
import de.fh_muenster.shelpapp.ShelpApp.PaymentCondition;
import de.fh_muenster.shelpapp.ShelpApp.Request;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.Tour;
import de.fh_muenster.shelpapp.ShelpApp.TourStatus;
import de.fh_muenster.shelpapp.ShelpApp.User;



/**
 * Diese Klasse enthaelt die Verbindung der App mit dem Xbank-Webservice.
 */
public class ShelpAppServiceImpl implements ShelpAppService {

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
    private static final String URL = "http://10.70.50.172:8080/shelp/UserIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = ShelpAppServiceImpl.class.getName();

    /**
     * sessionId contains the session id delivered from the server.
     */
    private int sessionId;

    //Methoden Klasse User
    @Override
    public User login(String username, String password) throws InvalidLoginException {
        User result = null;
        String METHOD_NAME = "login";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME, username, password);
            Log.d(TAG, response.toString());
            String test = (response.getPrimitivePropertySafelyAsString("returnCode"));
            System.out.println(test);
            return null;
         //   if (sessionId != 0) {
         //       result = new User(username, password);
           //     return result;
           // }
           // else {
            //    throw new InvalidLoginException("Login not successful!");
           // }
        } catch (SoapFault e) {
            throw new InvalidLoginException(e.getMessage());
        }
    }

    @Override
    public User registration(String eMail, String hash) throws InvalidRegistrationException {
        User result = null;
        String METHOD_NAME = "regUser";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME, eMail, hash);
            Log.d(TAG, response.toString());
            String reg = (response.getPrimitivePropertySafelyAsString("returnCode"));
            System.out.println(reg);
            return null;
         //   if (sessionId != 0) {
        //        result = new User(eMail, hash);
        //        return result;
        //    }
        //    else {
        //        throw new InvalidRegistrationException("Registration not successful!");
         //   }
        } catch (SoapFault e) {
            throw new InvalidRegistrationException(e.getMessage());
        }
    }


    @Override
    public void logout() throws NoSessionException {
        Log.d(TAG, "logout called.");
        String METHOD_NAME = "logout";
        try {
            SoapObject response = executeSoapAction(METHOD_NAME, sessionId);
            Log.d(TAG, response.toString());
        } catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }
    }


    @Override
    public Tour newTour(long id, ApprovalStatus approval, Location location,Capacity capacity, PaymentCondition payCondition, DeliveryCondition delCondition, Calendar date, List<Request> request, User owner,Calendar updatedOn, TourStatus status) throws InvalidTourException{
        return null;
    }


    //Methoden Klasse Tour


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
