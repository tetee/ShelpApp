package de.shelp.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import de.shelp.R;

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