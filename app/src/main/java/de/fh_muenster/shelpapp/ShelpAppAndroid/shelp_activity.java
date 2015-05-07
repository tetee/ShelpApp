package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;

public class shelp_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelp_activity);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shelp_activity, menu);
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

    public void create(View view) {
        Intent i = new Intent(this, create_activity.class);
        startActivity(i);
    }

    public void search(View view) {
        Intent i = new Intent(this, search_activity.class);
        startActivity(i);}

    public void request(View view) {
        Intent i = new Intent(this, own_request_activity.class);
        startActivity(i);
    }

    public void ownTours(View view) {
        Intent i = new Intent(this, tour_activity.class);
        startActivity(i);
    }

    public void friends(View view) {
        Intent i = new Intent(this, friends_activity.class);
        startActivity(i);
    }
}
