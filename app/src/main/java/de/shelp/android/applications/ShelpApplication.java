package de.shelp.android.applications;

import android.app.Application;

import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.implementations.FriendServiceImpl;
import de.shelp.ksoap2.implementations.OwnRequestServiceImpl;
import de.shelp.ksoap2.implementations.RatingServiceImpl;
import de.shelp.ksoap2.implementations.RequestServiceImpl;
import de.shelp.ksoap2.implementations.StateServiceImpl;
import de.shelp.ksoap2.implementations.TourServiceImpl;
import de.shelp.ksoap2.implementations.UserServiceImpl;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;

public class ShelpApplication extends Application {

    private ShelpSession session;
    private UserService userService;
    private AllLists allLists;
    private StateServiceImpl shelpAppService;
    private TourServiceImpl tourService;
    private RatingServiceImpl ratingService;
    private FriendServiceImpl friendService;
    private RequestServiceImpl requestService;
    private OwnRequestServiceImpl ownRequestService;


    public ShelpApplication() {
        this.userService = new UserServiceImpl();
        this.shelpAppService = new StateServiceImpl();
        this.tourService = new TourServiceImpl();
        this.ratingService = new RatingServiceImpl();
        this.friendService = new FriendServiceImpl();
        this.requestService = new RequestServiceImpl();
        this.ownRequestService = new OwnRequestServiceImpl();
    }

    public StateServiceImpl getShelpAppService() {return this.shelpAppService; }

    public TourServiceImpl getTourService() {return this.tourService; }

    public RequestServiceImpl getRequestService() {return this.requestService; }

    public RatingServiceImpl getRatingService() {return this.ratingService;}

    public UserService getUserService() {return this.userService; }

    public FriendServiceImpl getFriendService() { return this.friendService; }

    public OwnRequestServiceImpl getOwnRequestService() { return this.ownRequestService; }

    public ShelpSession getSession() { return this.session; }

    public void setSession(ShelpSession session) {this.session = session; }


    public AllLists getAllLists() { return this.allLists; }

    public void setAllLists(AllLists allLists) {this.allLists = allLists; }

}
