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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.SearchTask;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

public class SearchTourActivity extends ActionBarActivity {

    private AllLists allLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();

        //Aufruf der addItemsOnSpinner Methode
        addItemsOnSpinner();
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

    public void search(View view){
        ApprovalStatus approvalStatus = ((ApprovalStatus)((Spinner) findViewById(R.id.enablingSpinner)).getSelectedItem());
        Location location =((Location)((Spinner) findViewById(R.id.citySpinner)).getSelectedItem());
        Capacity capacity= ((Capacity) ((Spinner) findViewById(R.id.capacitySpinner)).getSelectedItem());
        long timeStart;
        long timeEnd;

        boolean directSearch =((boolean) ((CheckBox) findViewById(R.id.checkBox)).isChecked());

        EditText newDateStart = (EditText) findViewById(R.id.dateCreateStart);
        EditText newTimeStart = (EditText) findViewById(R.id.timeCreateStart);
        String txtDateStart = newDateStart.getText().toString();
        String txtTimeStart = newTimeStart.getText().toString();

        EditText newDateEnd = (EditText) findViewById(R.id.dateCreateEnd);
        EditText newTimeEnd = (EditText) findViewById(R.id.timeCreateEnd);
        String txtDateEnd = newDateEnd.getText().toString();
        String txtTimeEnd = newTimeEnd.getText().toString();
        try {
            timeStart =(ServiceUtils.formatInputToDate(txtDateStart + " " + txtTimeStart).getTime());
            timeEnd =(ServiceUtils.formatInputToDate(txtDateEnd + " " + txtTimeEnd).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Falsches Format!", Toast.LENGTH_SHORT).show();
            return;
        }

            ShelpApplication application = (ShelpApplication) getApplication();

            SearchTask searchTask = new SearchTask(view.getContext(),approvalStatus.getId(), location.getId(), capacity.getId(), timeStart, timeEnd, directSearch, application.getSession().getId(), this);
            searchTask.execute();


    }

    //Wechsel in die ShowTourActivity
    public void details(View view, Tour tour){
        Intent i = new Intent(this, ShowTourActivity.class);
        i.putExtra("Tour", tour);
        startActivity(i);
    }

    //Wechsel in die ShowRatingActivity
    public void rating(View view, User owner) {
        Intent i = new Intent(this, ShowRatingActivity.class);
        i.putExtra("User", owner);
        startActivity(i);
    }

    public void addItemsOnSpinner(){
         //Daten anhand der Spinner ID holen und in Variable speichern
        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        spinnerCity.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, allLists.getLocations()));

        Spinner approvalStatus = (Spinner) findViewById(R.id.enablingSpinner);
        approvalStatus.setAdapter(new ArrayAdapter<ApprovalStatus>(this, android.R.layout.simple_spinner_item, allLists.getStates()));

        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        cap.setAdapter(new ArrayAdapter<Capacity>(this, android.R.layout.simple_spinner_item, allLists.getCapacities()));

    }

}
