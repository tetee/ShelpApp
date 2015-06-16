package de.shelp.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.CreateTask;
import de.shelp.android.tasks.DeleteTourTask;
import de.shelp.android.tasks.GetRatingsTask;
import de.shelp.android.tasks.GetUpdatedRequestsTask;
import de.shelp.android.tasks.GetUpdatedToursTask;
import de.shelp.android.tasks.OwnToursTask;
import de.shelp.android.tasks.SearchTask;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

//Activity, die eigens angelegte Fahrten anzeigt
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    //durch Klicken werden die Details zur eigenen Fahrt angezeigt
    //um das Anfragen einer eigenen Fahrt zu vermeiden wird der Besitzer gleich true gesetzt und in der ShowTourActivity
    //der Button zum Anfragen ausgeblendet
    public void details(View view, Tour tour){
        Intent i = new Intent(this, ShowTourActivity.class);
        i.putExtra("Tour", tour);
        i.putExtra("Owner", true);
        startActivity(i);
    }

    //Tour löschen bzw. auf CANCLED setzen
    public void delete(View view, Tour tour){
        ShelpApplication application = (ShelpApplication) getApplication();
        DeleteTourTask deleteToursTask = new DeleteTourTask(getApplicationContext(), tour,application.getSession().getId(), this);
        deleteToursTask.execute();
        finish();
        startActivity(getIntent());
    }
}
