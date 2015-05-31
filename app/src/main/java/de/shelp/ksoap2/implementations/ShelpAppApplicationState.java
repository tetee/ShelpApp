package de.shelp.ksoap2.implementations;

import android.app.Application;

import de.shelp.ksoap2.entities.AllLists;

public class ShelpAppApplicationState extends Application {

    private AllLists allLists;
    private ShelpAppServiceStateImpl shelpAppService;

    public ShelpAppApplicationState() {this.shelpAppService = new ShelpAppServiceStateImpl();}

    public AllLists getAllLists() { return this.allLists; }

    public void setAllLists(AllLists allLists) {this.allLists = allLists; }

    public ShelpAppServiceStateImpl getShelpAppService() {return this.shelpAppService; }

}
