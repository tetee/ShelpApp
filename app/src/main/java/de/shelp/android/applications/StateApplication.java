package de.shelp.android.applications;

import android.app.Application;

import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.implementations.StateServiceImpl;

public class StateApplication extends Application {

    private AllLists allLists;

    private StateServiceImpl shelpAppService;

    public StateApplication() {this.shelpAppService = new StateServiceImpl();}

    public AllLists getAllLists() { return this.allLists; }

    public void setAllLists(AllLists allLists) {this.allLists = allLists; }

    public StateServiceImpl getShelpAppService() {return this.shelpAppService; }

}
