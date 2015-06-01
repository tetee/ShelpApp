package de.shelp.android.applications;

import android.app.Application;

import de.shelp.ksoap2.implementations.UserServiceImpl;
import de.shelp.ksoap2.services.UserService;
import de.shelp.ksoap2.entities.ShelpSession;

public class SessionApplication extends Application {

    private ShelpSession session;
    private UserService userService;

    public SessionApplication() {this.userService = new UserServiceImpl();}

    public ShelpSession getSession() {
       // System.out.println(session.getId());
        return this.session; }

    public void setSession(ShelpSession session) {

       // System.out.println(session.getId());
        this.session = session; }

    public UserService getUserService() {return this.userService; }



}
