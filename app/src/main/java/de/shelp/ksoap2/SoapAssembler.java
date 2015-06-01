package de.shelp.ksoap2;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.User;

/**
 * Created by Jos-Laptop on 31.05.2015.
 */
public class SoapAssembler {

    public static SoapAssembler instance;

    private SoapAssembler() {

    }

    public static SoapAssembler getInstance() {
        if (instance == null) {
            instance = new SoapAssembler();
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

    public AllLists soapToAllLists(SoapObject response) {
        List<String> capacities = new ArrayList<String>();
        List<String> deliveryConditions = new ArrayList<String>();
        List<String> paymentConditions = new ArrayList<String>();
        List<String> states = new ArrayList<String>();
        List<Location> locations = new ArrayList<Location>();

        for (int i = 0; i < response.getPropertyCount(); i++) {
            PropertyInfo info = new PropertyInfo();
            response.getPropertyInfo(i, info);
            if("capacities".equals(info.getName())) {
                capacities.add(response.getPropertyAsString(i));
            } else if("deliveryConditions".equals(info.getName())) {
                deliveryConditions.add(response.getPropertyAsString(i));
            }else if("paymentConditions".equals(info.getName())) {
                paymentConditions.add(response.getPropertyAsString(i));
            }else if("states".equals(info.getName())) {
                states.add(response.getPropertyAsString(i));
            } else if("locations".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                locations.add(new Location(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description"),
                        responseChild.getPrimitivePropertyAsString("zipcode")));
            }
        }

        return new AllLists(capacities,deliveryConditions,paymentConditions,states,locations);
    }
}
