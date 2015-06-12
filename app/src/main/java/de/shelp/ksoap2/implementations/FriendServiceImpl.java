package de.shelp.ksoap2.implementations;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.SoapAssembler;
import de.shelp.ksoap2.entities.Friendship;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 21.05.15.
 */
public class FriendServiceImpl {


    private static final String URL = ServiceUtils.URL + "FriendIntegration";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = FriendServiceImpl.class.getName();

    public List<Friendship> getFriends(int sessionId) throws SoapFault {
        String METHOD_NAME = "getFriends";
        SoapObject response = null;

        response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId);

        List<Friendship> friendships = new ArrayList<>();

        for(int i = 1; i < response.getPropertyCount(); i++) {
            friendships.add( SoapAssembler.getInstance().soapToFriendship((SoapObject) response.getProperty(i)));
        }

        return friendships;
    }

   public boolean addFriend(int sessionId, String friendId) throws SoapFault {
       String METHOD_NAME = "addFriend";
       SoapObject response = null;

       response = ServiceUtils.executeSoapAction(METHOD_NAME, URL, sessionId, friendId);

       return response.getPrimitivePropertyAsString("returnCode").equals("OK");
   }
}
