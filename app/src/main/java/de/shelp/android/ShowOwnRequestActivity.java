package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.GetOwnRequestTask;
import de.shelp.ksoap2.entities.User;

/**
 * Activity, um eigene Anfragen an fremde Touren anzuzeigen über den {@link de.shelp.android.tasks.GetOwnRequestTask}.
 * Bei bestimmtem Tourstatus können Benutzer bewertet {@link #rate(android.view.View, de.shelp.ksoap2.entities.User)} werden
 *
 * @author Roman Busch
 *
 */
public class ShowOwnRequestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_request_activity);
        //Fahrten über AsyncTask vom Server laden
        GetOwnRequestTask getOwnRequestTask = new GetOwnRequestTask(this.getApplicationContext(), (ShelpApplication) getApplication(), this);
        getOwnRequestTask.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methode um in die {@link de.shelp.android.RatingActivity} zu wechseln um einen Benutzer zu bewerten.
     *
     * @param view - Die aktuell sichtbare View.
     * @param user - Der zu bewertenden Benutzer.
     */
    public void rate(View view, User user) {
        Intent i = new Intent(this, RatingActivity.class);
        //Intent den zu bewertenden User mitgeben
        i.putExtra("RatingUser", user);
        startActivity(i);
    }
}
