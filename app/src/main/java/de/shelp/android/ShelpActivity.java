package de.shelp.android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.ksoap2.SoapFault;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.GetFriendsTask;
import de.shelp.android.tasks.LogoutTask;

public class ShelpActivity extends ActionBarActivity {

    private ShelpActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelp_activity);

        thisActivity = this;

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

    //Wechsel zur CreateTourActivity
    public void create(View view) {
        CreateTask createTask = new CreateTask(view.getContext(), CreateTourActivity.class);
        createTask.execute();
    }

    //Wechsel zur SearchTourActivity
    public void search(View view) {
        CreateTask createTask = new CreateTask(view.getContext(), SearchTourActivity.class);
        createTask.execute();
    }

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
        Intent i = new Intent(this,FriendsActivity.class);
        startActivity(i);
    }


    public void logout(View ausloeser) {
        //Logout asynchron ausfuehren:
        LogoutTask logoutTask = new LogoutTask(ausloeser.getContext(), (ShelpApplication) getApplication());
        logoutTask.execute();
    }


    private class CreateTask extends AsyncTask<Object, Object, Object> {
        private Context context;
        private Class nextActivity;

        public CreateTask(Context context, Class nextActivity) {
            this.context = context;
            this.nextActivity = nextActivity;
        }

        @Override
        protected Object doInBackground(Object... params) {
            try {
                ShelpApplication app = (ShelpApplication) getApplication();
                if (app.getAllLists() == null) {
                    app.setAllLists(app.getShelpAppService().getLists());
                }
            } catch (SoapFault ex) {
                //TODO errorhandling
            }
            return null;
        }

        protected void onProgessUpdate(Object... values) {
        }

        protected void onPostExecute(Object result) {
            Intent i = new Intent(thisActivity, nextActivity);
            startActivity(i);
        }
    }
}