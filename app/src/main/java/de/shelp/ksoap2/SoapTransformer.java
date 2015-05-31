package de.shelp.ksoap2;

import org.ksoap2.serialization.SoapObject;

import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.User;

/**
 * Created by Jos-Laptop on 31.05.2015.
 */
public class SoapTransformer {

    public static SoapTransformer instance;

    private SoapTransformer() {

    }

    public static SoapTransformer getInstance() {
        if (instance == null) {
            instance = new SoapTransformer();
        }

        return instance;
    }

    public ShelpSession soapToSession(SoapObject response) {
        SoapObject regEntry = (SoapObject) response.getProperty(1);
        SoapObject userEntry = (SoapObject) regEntry.getProperty(2);
        User user = new User(userEntry.getProperty("email").toString());

        ShelpSession session = new ShelpSession(Integer.parseInt(regEntry.getProperty("id").toString()), user);

        return session;
    }
}
