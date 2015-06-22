package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.shelp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.DeleteTourTask;
import de.shelp.android.tasks.OwnToursTask;
import de.shelp.ksoap2.entities.Tour;

/**
 * Activity, die eigens angelegte Fahrten anzeigt.
 * {@link #details(View, Tour)}
 * {@link #delete(View, Tour)}
 * In der onCreate() Methode wird der AsyncTask zum anzeigen der eigenen Fahrten aufgerufen {@link de.shelp.android.tasks.OwnToursTask}.
 *
 * @author Roman Busch
 *
 */
public class ShowOwnTourActivity extends ActionBarActivity {

    Tour tour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_activity);

        ShelpApplication application = (ShelpApplication) getApplication();

        //Fahrten über AsyncTask vom Server laden
        OwnToursTask ownToursTask = new OwnToursTask(getApplicationContext(),application.getSession().getId(), this);
        ownToursTask.execute();

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
     * Durch klicken des Button "Details" werden die Details zur eigenen Fahrt angezeigt.
     * Um das Anfragen einer eigenen Fahrt zu vermeiden wird der Besitzer gleich true gesetzt und in der ShowTourActivity
     * der Button zum Anfragen ausgeblendet.
     * @param view - Die aktuell sichtbare View
     * @param tour - Fahrt wird an eine andere Activity übergeben
     */
    public void details(View view, Tour tour){
        Intent i = new Intent(this, ShowTourActivity.class);
        i.putExtra("Tour", tour);
        i.putExtra("Owner", true);
        startActivity(i);
    }

    /**
     * Tour löschen bzw. auf CANCELLED setzen
     * @param view - Die aktuell sichtbare View
     * @param tour - Fahrt wird an den AsyncTaks übergeben
     * Zum löschen der eigenen Fahrt wird der AsyncTask aufgerufen {@link de.shelp.android.tasks.DeleteTourTask}
     */
    public void delete(View view, Tour tour){
        ShelpApplication application = (ShelpApplication) getApplication();
        DeleteTourTask deleteToursTask = new DeleteTourTask(getApplicationContext(), tour,application.getSession().getId(), this);
        deleteToursTask.execute();
        finish();
        startActivity(getIntent());
    }
}
