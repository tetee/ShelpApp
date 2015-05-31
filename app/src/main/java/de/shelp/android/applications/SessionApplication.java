package de.shelp.android.applications;

import android.app.Application;

import de.shelp.ksoap2.implementations.UserServiceImpl;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;

public class SessionApplication extends Application {

    private ShelpSession session;
    private UserService userService;

    public SessionApplication() {this.userService = new UserServiceImpl();}

    public ShelpSession getSession() { return this.session; }

    public void setSession(ShelpSession session) {this.session = session; }

    public UserService getUserService() {return this.userService; }



}
