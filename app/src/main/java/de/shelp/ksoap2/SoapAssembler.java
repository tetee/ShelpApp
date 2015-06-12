package de.shelp.ksoap2;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Friendship;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.entities.WishlistItem;

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

    public Tour soapToTour(SoapObject response) {
        //Annahme der Daten aus dem response SoapObject
        //Freigabe
        SoapObject approvalStatus = (SoapObject) response.getProperty("approvalStatus");
        ApprovalStatus app = new ApprovalStatus(Integer.valueOf(approvalStatus.getPropertyAsString("id")),approvalStatus.getPropertyAsString("description"));
        //Kapazität
        SoapObject capacity =(SoapObject) response.getProperty("capacity");
        Capacity cap = new Capacity(Integer.valueOf(capacity.getPropertyAsString("id")), capacity.getPropertyAsString("description"));
        //Lieferbedingung
        SoapObject deliveryCondition =(SoapObject) response.getProperty("deliveryCondition");
        DeliveryCondition delCon = new DeliveryCondition(Integer.valueOf(deliveryCondition.getPropertyAsString("id")), deliveryCondition.getPropertyAsString("description"));
        //ID
        long id = Long.valueOf(response.getPropertyAsString("id"));
        //Ort
        SoapObject location =(SoapObject) response.getProperty("location");
        Location loc = new Location(Integer.valueOf(location.getPropertyAsString("id")), location.getPropertyAsString("description"), location.getPrimitivePropertyAsString("zipcode"));
        //Liste der Anfragen
        //TODO SoapObject list
        List<Request> list = null;
        //Ersteller er Fahrt
        User ownerTour = soapToUser((SoapObject) response.getProperty("owner"));
        //Zahlungsbedingungen
        SoapObject paymentCondition =(SoapObject) response.getProperty("paymentCondition");
        PaymentCondition payCon = new PaymentCondition(Integer.valueOf(paymentCondition.getPropertyAsString("id")), paymentCondition.getPropertyAsString("description"));
        //Status der Tour
        TourStatus status = TourStatus.valueOf(response.getPropertyAsString("status"));
        //Datum der Tour
        long time = Long.valueOf(response.getPropertyAsString("time"));


        Tour tour = new Tour(id, app, loc, cap, payCon, delCon,time,list, ownerTour,  status);

        return tour;
    }

    public AllLists soapToAllLists(SoapObject response) {
        List<Capacity> capacities = new ArrayList<Capacity>();
        List<DeliveryCondition> deliveryConditions = new ArrayList<DeliveryCondition>();
        List<PaymentCondition> paymentConditions = new ArrayList<PaymentCondition>();
        List<ApprovalStatus> states = new ArrayList<ApprovalStatus>();
        List<Location> locations = new ArrayList<Location>();

        for (int i = 0; i < response.getPropertyCount(); i++) {
            PropertyInfo info = new PropertyInfo();
            response.getPropertyInfo(i, info);
            if("capacities".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                capacities.add(new Capacity(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description")));
            } else if("deliveryConditions".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                deliveryConditions.add(new DeliveryCondition(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description")));
            }else if("paymentConditions".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                paymentConditions.add(new PaymentCondition(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description")));
            }else if("states".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                states.add(new ApprovalStatus(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description")));
            } else if("locations".equals(info.getName())) {
                SoapObject responseChild = (SoapObject) response.getProperty(i);
                locations.add(new Location(Integer.valueOf(responseChild.getPrimitivePropertyAsString("id")),
                        responseChild.getPrimitivePropertyAsString("description"),
                        responseChild.getPrimitivePropertyAsString("zipcode")));
            }
        }

        return new AllLists(capacities,deliveryConditions,paymentConditions,states,locations);
    }

    public Friendship soapToFriendship(SoapObject response) {
        int id = Integer.valueOf(response.getPropertyAsString("id"));
        long changedOn = Long.valueOf(response.getPropertyAsString("changedOn"));
        String friendshipStatus = response.getPropertyAsString("status");

        User initiatorUser = soapToUser((SoapObject) response.getProperty("initiatorUser"));

        User recipientUser = soapToUser((SoapObject) response.getProperty("recipientUser"));

       return new Friendship(id, initiatorUser,recipientUser,friendshipStatus,changedOn);
    }

    public User soapToUser(SoapObject response) {
        return new User(response.getPropertyAsString("email"));
    }

    public Rating soapToRating(SoapObject response){
        Long id = Long.valueOf(response.getPropertyAsString("id"));
        User sourceUser = soapToUser((SoapObject) response.getProperty("sourceUser"));
        User targetUser = soapToUser((SoapObject) response.getProperty("targetUser"));
        int rating = Integer.valueOf(response.getPropertyAsString("rating"));
        String notice = response.getPropertyAsString("notice");

        return new Rating(id, sourceUser,targetUser,rating,notice);
    }

    public Request soapToRequest(SoapObject response) {
    SoapObject wishResponse = (SoapObject) response.getProperty("wishes");
        List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();
        for (int i = 0; i < wishResponse.getPropertyCount(); i++) {
            wishlistItems.add(soapToWishlistItem((SoapObject) wishResponse.getProperty(i)));
        }
        Long id = Long.valueOf(response.getPropertyAsString("id"));
        User sourceUser = soapToUser((SoapObject) response.getProperty("sourceUser"));
        User targetUser = soapToUser((SoapObject) response.getProperty("targetUser"));
        Tour tour = soapToTour((SoapObject) response.getProperty("tour"));
        String notice = response.getPropertyAsString("notice");
        String status = response.getPropertyAsString("status");


        return new Request(id, sourceUser, targetUser, tour, wishlistItems, notice, status );
    }

    public WishlistItem soapToWishlistItem(SoapObject response) {
        int id = Integer.valueOf(response.getPropertyAsString("id"));
        String text = response.getPropertyAsString("text");
        Boolean checked = Boolean.valueOf(response.getPropertyAsString("checked"));

        return new WishlistItem(id, text, checked);
    }

}
