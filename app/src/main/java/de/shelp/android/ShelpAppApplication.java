package de.shelp.android;

import android.app.Application;

import de.shelp.ksoap2.implementations.ShelpAppServiceImpl;
import de.shelp.ksoap2.services.ShelpAppService;
import de.shelp.ksoap2.entities.ShelpSession;

public class ShelpAppApplication extends Application {

    private ShelpSession session;
    private ShelpAppService shelpAppService;

    public ShelpAppApplication() {this.shelpAppService = new ShelpAppServiceImpl();}

    public ShelpSession getSession() { return this.session; }

    public void setSession(ShelpSession session) {this.session = session; }

    public ShelpAppService getShelpAppService() {return this.shelpAppService; }



}
