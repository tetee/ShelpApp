package de.shelp.android;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.Tour;

public class CreateTourActivity extends ActionBarActivity {

    private AllLists allLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();

        //Aufruf der addItemsOnSpinner Methode
        addItemsOnSpinner();
        //addItemsOnSpinnerDB();
    }


    public void addItemsOnSpinner(){

        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        spinnerCity.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, allLists.getLocations()));

        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        cap.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLists.getCapacities()));

        Spinner enabling = (Spinner) findViewById(R.id.enablingSpinner);
        enabling.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLists.getStates()));

        Spinner pay = (Spinner) findViewById(R.id.paySpinner);
        pay.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLists.getPaymentConditions()));

        Spinner del = (Spinner) findViewById(R.id.delSpinner);
        del.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLists.getDeliveryConditions()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_activity, menu);
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

    private class CreateTask extends AsyncTask<Object, Integer, Tour>
    {
        private Context context;
        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public CreateTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Tour doInBackground(Object... params){
            return null;
        }

        protected void onProgessUpdate(Integer... values)
        { }

        protected void onPostExecute(Tour result)
        {

        }
    }

}
