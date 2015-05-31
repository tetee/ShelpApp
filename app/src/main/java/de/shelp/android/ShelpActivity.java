package de.shelp.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.tasks.LogoutTask;

public class ShelpActivity extends ActionBarActivity {

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

    //Wechsel zur CreateTourActivity
    public void create(View view) {
        Intent i = new Intent(this, CreateTourActivity.class);
        startActivity(i);
    }

    //Wechsel zur SearchTourActivity
    public void search(View view) {
        Intent i = new Intent(this, SearchTourActivity.class);
        startActivity(i);}

    //Wechsel zur ShowOwnRequestActivity
    public void request(View view) {
        Intent i = new Intent(this, ShowOwnRequestActivity.class);
        startActivity(i);
    }

    //Wechsel zur ShowOwnTourActivity
    public void ownTours(View view) {
        Intent i = new Intent(this, ShowOwnTourActivity.class);
        startActivity(i);
    }

    //Wechsel zur FriendsActivity
    public void friends(View view) {
        Intent i = new Intent(this, FriendsActivity.class);
        startActivity(i);
    }


        public void logout(View ausloeser) {
            //Logout asynchron ausfuehren:
            LogoutTask logoutTask = new LogoutTask(ausloeser.getContext(),(ShelpAppApplication) getApplication());
            logoutTask.execute();
        }
    }

