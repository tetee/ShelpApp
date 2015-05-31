package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.app.Application;

import de.fh_muenster.shelpapp.ShelpApp.AllLists;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.ShelpSession;
import de.fh_muenster.shelpapp.ShelpAppAndroid.tasks.ShelpAppServiceStateImpl;

public class ShelpAppApplicationState extends Application {

    private AllLists allLists;
    private ShelpAppServiceStateImpl shelpAppService;

    public ShelpAppApplicationState() {this.shelpAppService = new ShelpAppServiceStateImpl();}

    public AllLists getAllLists() { return this.allLists; }

    public void setAllLists(AllLists allLists) {this.allLists = allLists; }

    public ShelpAppServiceStateImpl getShelpAppService() {return this.shelpAppService; }

}
