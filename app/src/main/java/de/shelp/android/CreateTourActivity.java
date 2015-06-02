package de.shelp.android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.CreateTask;
import de.shelp.android.tasks.LogoutTask;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidLoginException;

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
        cap.setAdapter(new ArrayAdapter<Capacity>(this, android.R.layout.simple_spinner_item, allLists.getCapacities()));

        Spinner enabling = (Spinner) findViewById(R.id.enablingSpinner);
        enabling.setAdapter(new ArrayAdapter<ApprovalStatus>(this, android.R.layout.simple_spinner_item, allLists.getStates()));

        Spinner pay = (Spinner) findViewById(R.id.paySpinner);
        pay.setAdapter(new ArrayAdapter<PaymentCondition>(this, android.R.layout.simple_spinner_item, allLists.getPaymentConditions()));

        Spinner del = (Spinner) findViewById(R.id.delSpinner);
        del.setAdapter(new ArrayAdapter<DeliveryCondition>(this, android.R.layout.simple_spinner_item, allLists.getDeliveryConditions()));
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

    public void createTour(View view) {
        Tour tour = new Tour();
        tour.setLocation((Location) ((Spinner) findViewById(R.id.citySpinner)).getSelectedItem());
        tour.setApprovalStatus((ApprovalStatus) ((Spinner) findViewById(R.id.enablingSpinner)).getSelectedItem());
        tour.setCapacity((Capacity) ((Spinner) findViewById(R.id.capacitySpinner)).getSelectedItem());
        tour.setDeliveryConditions((DeliveryCondition) ((Spinner) findViewById(R.id.delSpinner)).getSelectedItem());
        tour.setPaymentConditions((PaymentCondition) ((Spinner) findViewById(R.id.paySpinner)).getSelectedItem());

        EditText newDate = (EditText) findViewById(R.id.dateCreate);
        EditText newTime = (EditText) findViewById(R.id.timeCreate);
        String txtDate = newDate.getText().toString();
        String txtTime = newTime.getText().toString();
        try {
            tour.setTime(ServiceUtils.formatInputToDate(txtDate + " " + txtTime).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Falsches Format!", Toast.LENGTH_SHORT).show();
            return;
        }


        ShelpApplication application = (ShelpApplication) getApplication();

        CreateTask createTask = new CreateTask(view.getContext(), tour, application.getSession().getId(), this);
        createTask.execute();

        Intent i = new Intent(this, ShelpActivity.class);
        startActivity(i);
    }
}
