package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import de.fh_muenster.shelpapp.R;
import de.fh_muenster.shelpapp.ShelpApp.ShelpAppService;
import de.fh_muenster.shelpapp.ShelpApp.User;

public class ShelpAppApplication extends Application {

    private User user;
    private ShelpAppService shelpAppService;

    public ShelpAppApplication() {this.shelpAppService = new ShelpAppServiceImplMock();}

    public User getUser() { return this.user; }

    public void setUser(User user) {this.user = user; }

    public ShelpAppService getShelpAppService() {return this.shelpAppService; }

}
