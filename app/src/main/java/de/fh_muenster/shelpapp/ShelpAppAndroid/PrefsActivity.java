package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.preference.PreferenceActivity;

import de.fh_muenster.shelpapp.R;


public class PrefsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.shelpprefs);
    }
}