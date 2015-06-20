package de.shelp.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import de.fh_muenster.shelpapp.R;

/**
 * @author Roman Busch
 */

public class PrefsActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.shelpprefs);
    }
}