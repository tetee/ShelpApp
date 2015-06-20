package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.CreateTask;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.DeliveryCondition;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.PaymentCondition;
import de.shelp.ksoap2.entities.Tour;

/**
 * Diese Activity ermöglicht das Erstellen einer Fahrt
 * {@link #createTour(android.view.View)}
 * {@link #addItemsOnSpinner()}
 * Das Erstellen der Fahrt läuft über den AsynTask {@link CreateTask}
 *
 * @author
 * 
 */

public class CreateTourActivity extends ActionBarActivity {

    Tour tour;
    private AllLists allLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();

        //Aufruf der addItemsOnSpinner Methode um Spinner mit Werten zu füllen
        addItemsOnSpinner();
    }

    /**
     * Methode um Daten in die Spinner zu laden
     */
    public void addItemsOnSpinner(){

        //Daten der Spinner mit Werten füllen
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        spinnerCity.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, allLists.getLocations()));

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methode zur Erstellung einer Fahrt
     * Werte werden aus Spinner ausgelesen und an den {@link de.shelp.android.tasks.CreateTask} gesendet
     *
     * @param view - Die aktuell sichtbare View
     */
    public void createTour(View view) {
        //leeres Tour Object anlegen
        Tour tour = new Tour();

        //Tour Object mit angegebenen Daten des Erstellers füllen
        tour.setLocation((Location) ((Spinner) findViewById(R.id.citySpinner)).getSelectedItem());
        tour.setApprovalStatus((ApprovalStatus) ((Spinner) findViewById(R.id.enablingSpinner)).getSelectedItem());
        tour.setCapacity((Capacity) ((Spinner) findViewById(R.id.capacitySpinner)).getSelectedItem());
        tour.setDeliveryConditions((DeliveryCondition) ((Spinner) findViewById(R.id.delSpinner)).getSelectedItem());
        tour.setPaymentConditions((PaymentCondition) ((Spinner) findViewById(R.id.paySpinner)).getSelectedItem());

        EditText newDate = (EditText) findViewById(R.id.dateCreate);
        EditText newTime = (EditText) findViewById(R.id.timeCreate);
        String txtDate = newDate.getText().toString();
        String txtTime = newTime.getText().toString();
        //Test des Datumsformats
        try {
            tour.setTime(ServiceUtils.formatInputToDate(txtDate + " " + txtTime).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Falsches Format!", Toast.LENGTH_SHORT).show();
            //bei falschem Format wird die Activity neu geladen
            finish();
            startActivity(getIntent());
            return;
        }


        ShelpApplication application = (ShelpApplication) getApplication();

        //Erstellen der Tour über einen AsyncTask und Übergabe an den Server
        CreateTask createTask = new CreateTask(view.getContext(), tour, application.getSession().getId(), this);
        createTask.execute();

        //Wechsel zu ShelpActivity
        Intent i = new Intent(this, ShelpActivity.class);
        startActivity(i);
    }
}
