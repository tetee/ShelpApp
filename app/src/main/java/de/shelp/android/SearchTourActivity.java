package de.shelp.android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.ksoap2.SoapFault;

import java.util.Date;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Tour;

public class SearchTourActivity extends ActionBarActivity {

    private AllLists allLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);

        //Button suchen
        Button detailsButton = (Button) findViewById(R.id.detailsButton);
        Button ratingButton = (Button) findViewById(R.id.ratingButton);
        //Button unsichtbar machen
        detailsButton.setVisibility(View.INVISIBLE);
        ratingButton.setVisibility(View.INVISIBLE);

        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();

        //Aufruf der addItemsOnSpinner Methode
        addItemsOnSpinner();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_activity, menu);
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

    public void search(View view){

        Tour tour = new Tour();
        tour.getLocation();
            tour.setApprovalStatus((ApprovalStatus) ((Spinner) findViewById(R.id.enablingSpinner)).getSelectedItem());
            tour.setCapacity((Capacity) ((Spinner) findViewById(R.id.capacitySpinner)).getSelectedItem());
            tour.setDeliveryConditions((DeliveryCondition)((Spinner) findViewById(R.id.delSpinner)).getSelectedItem());
            tour.setPaymentConditions((PaymentCondition)((Spinner) findViewById(R.id.paySpinner)).getSelectedItem());
            tour.setTime(new Date());

            ShelpApplication application = (ShelpApplication) getApplication();

            SearchTask searchTask = new SearchTask(view.getContext(), tour, application.getSession().getId());
            searchTask.execute();

        //Button suchen
        Button detailsButton = (Button) findViewById(R.id.detailsButton);
        Button ratingButton = (Button) findViewById(R.id.ratingButton);
        //Button sichtbar machen
        detailsButton.setVisibility(View.VISIBLE);
        ratingButton.setVisibility(View.VISIBLE);

    }

    //Wechsel in die CreateTourActivity
    public void details(View view){
        Intent i = new Intent(this, ShowTourActivity.class);
        startActivity(i);
    }

    //Wechsel in die ShowRatingActivity
    public void rating(View view) {
        Intent i = new Intent(this, ShowRatingActivity.class);
        startActivity(i);
    }

    public void addItemsOnSpinner(){
         //Daten anhand der Spinner ID holen und in Variable speichern
        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        spinnerCity.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, allLists.getLocations()));

        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        cap.setAdapter(new ArrayAdapter<Capacity>(this, android.R.layout.simple_spinner_item, allLists.getCapacities()));

    }

    private class SearchTask extends AsyncTask<Object, Integer, String>
    {
        private Context context;
        private Tour  tour;
        private int sessionId;
        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public SearchTask(Context context, Tour tour, int sessionId)
        {
            this.tour = tour;
            this.sessionId = sessionId;
            this.context = context;
        }

        @Override
        protected String doInBackground(Object... params){
            ShelpApplication myApp = (ShelpApplication) getApplication();
            try {
                return myApp.getTourService().searchTour(tour, sessionId);
            } catch (SoapFault e) {
                //TODO errorhandling
                e.printStackTrace();
            }
            return "";
        }

        protected void onProgessUpdate(Integer... values)
        { }

        protected void onPostExecute(String result)
        {
            String r = result;
            String t = result;
        }
    }

}
