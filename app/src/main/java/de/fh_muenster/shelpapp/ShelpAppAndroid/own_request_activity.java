package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;

public class own_request_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_request_activity);
        //TODO
        //if status == "Erledigt" ermögliche Bewertung
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_own_request_activity, menu);
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

        return super.onOptionsItemSelected(item);
    }

    //Aufruf der wishlist_activitiy (bei bestimmtem STatus nicht mehr klickbar)
    public void editWishlist(View view) {
        Intent i = new Intent(this, whishlist_activity.class);
        startActivity(i);
    }

    //TODO
    //angefragte Fahrt wieder löschen
    //public void delete(){

    //}

    //Aufruf der rating_activitiy (bei bestimmtem Status sichtbar)
    public void rate(View view) {
        Intent i = new Intent(this, rating_activity.class);
        startActivity(i);
    }
}
