package de.shelp.android.applications;

import android.app.Application;

import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.implementations.StateServiceImpl;
import de.shelp.ksoap2.implementations.UserServiceImpl;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;

public class ShelpApplication extends Application {

    private ShelpSession session;
    private UserService userService;
    private AllLists allLists;
    private StateServiceImpl shelpAppService;

    public ShelpApplication() {
        this.userService = new UserServiceImpl();
        this.shelpAppService = new StateServiceImpl();
    }

    public StateServiceImpl getShelpAppService() {return this.shelpAppService; }

    public ShelpSession getSession() { return this.session; }

    public void setSession(ShelpSession session) {this.session = session; }

    public UserService getUserService() {return this.userService; }

    public AllLists getAllLists() { return this.allLists; }

    public void setAllLists(AllLists allLists) {this.allLists = allLists; }

}
