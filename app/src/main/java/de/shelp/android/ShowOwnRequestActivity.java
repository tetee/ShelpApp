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

//Activity, die eigene Anfragen an fremde Touren anzeigt
public class ShowOwnRequestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_request_activity);
        //TODO
        //if status == "Erledigt" ermögliche Bewertung
/*
        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();
        */
        //Fahrten über AsyncTask vom Server laden
        GetOwnRequestTask getOwnRequestTask = new GetOwnRequestTask(this.getApplicationContext(), (ShelpApplication) getApplication(), this);
        getOwnRequestTask.execute();

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


    //Aufruf der RatingActivitiy um einene Benutzer zu bewerten (bei bestimmtem Status sichtbar)
    public void rate(View view, User user) {
        Intent i = new Intent(this, RatingActivity.class);
        //Intent den zu bewertenden User mitgeben!
        i.putExtra("RatingUser", user);
        startActivity(i);
    }
}
