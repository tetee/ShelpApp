package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.shelp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.SearchTask;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.AllLists;
import de.shelp.ksoap2.entities.ApprovalStatus;
import de.shelp.ksoap2.entities.Capacity;
import de.shelp.ksoap2.entities.Location;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

/**
 * Activity, um nach einer bestimmten Fahrt zu suchen,
 * {@link #search(android.view.View)},
 * {@link #rating(android.view.View, de.shelp.ksoap2.entities.User)} Bewertungen des Erstellers anzeigen,
 * {@link #details(android.view.View, de.shelp.ksoap2.entities.Tour)} Details der Tour anzeigen,
 * {@link #addItemsOnSpinner()} und Laden der Details in die Spinner.
 *
 * @author Theresa Sollert
 *
 */
public class SearchTourActivity extends ActionBarActivity {

    private AllLists allLists;
    private List<TextView> searchedElements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        ShelpApplication shelpApplication = (ShelpApplication) getApplication();
        allLists = shelpApplication.getAllLists();

        //Aufruf der addItemsOnSpinner Methode um Spinner mit Werten zu füllen
        addItemsOnSpinner();
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
     * Methode um nach einer Tour zu suchen über den {@link de.shelp.android.tasks.SearchTask}.
     * Die Ausgabe der gefundenen Touren erfolgt in aktueller View.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void search(View view){
        //Auslese des Daten für die gesuchte Tour
        ApprovalStatus approvalStatus = ((ApprovalStatus)((Spinner) findViewById(R.id.enablingSpinner)).getSelectedItem());
        Location location =((Location)((Spinner) findViewById(R.id.citySpinner)).getSelectedItem());
        Capacity capacity= ((Capacity) ((Spinner) findViewById(R.id.capacitySpinner)).getSelectedItem());
        long timeStart;
        long timeEnd;

        boolean directSearch =!((boolean) ((CheckBox) findViewById(R.id.checkBox)).isChecked());

        EditText newDateStart = (EditText) findViewById(R.id.dateCreateStart);
        EditText newTimeStart = (EditText) findViewById(R.id.timeCreateStart);
        String txtDateStart = newDateStart.getText().toString();
        String txtTimeStart = newTimeStart.getText().toString();

        EditText newDateEnd = (EditText) findViewById(R.id.dateCreateEnd);
        EditText newTimeEnd = (EditText) findViewById(R.id.timeCreateEnd);
        String txtDateEnd = newDateEnd.getText().toString();
        String txtTimeEnd = newTimeEnd.getText().toString();
        //Überprüfung des Datumsformates
        try {
            timeStart =(ServiceUtils.formatInputToDate(txtDateStart + " " + txtTimeStart).getTime());
            timeEnd =(ServiceUtils.formatInputToDate(txtDateEnd + " " + txtTimeEnd).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Falsches Format!", Toast.LENGTH_SHORT).show();
            //bei falschem Format wird die Activity neu geladen
            finish();
            startActivity(getIntent());
            return;
        }

            ShelpApplication application = (ShelpApplication) getApplication();

            //Erstellen der Tour über einen AsyncTask und Suche der entsprechenden Touren auf den Server
            SearchTask searchTask = new SearchTask(view.getContext(),approvalStatus.getId(), location.getId(), capacity.getId(), timeStart, timeEnd, directSearch, application.getSession().getId(), this);
            searchTask.execute();


    }

    /**
     * Methode, die in die {@link de.shelp.android.ShowTourActivity} wechselt um Details der Fahrt anzuzeigen.
     *
     * @param view - Die aktuell sichtbare View.
     * @param tour - Fahrt, zu der Details angezeigt werden sollen.
     */
    public void details(View view, Tour tour){
        ShelpApplication myApp = (ShelpApplication) getApplication();
        myApp.removeUpdatedTours(tour);

        Intent i = new Intent(this, ShowTourActivity.class);
        //Intent den Besitzer und Tour mitgeben
        i.putExtra("Owner", false);
        i.putExtra("Tour", tour);
        startActivity(i);
    }

    /**
     * Methode um in die {@link de.shelp.android.ShowRatingActivity} um Bewertungen des Fahrterstellers anzuzeugen.
     *
     * @param view - Die aktuell sichtbare View
     * @param owner - Der Ersteller der Fahrt.
     */
    public void rating(View view, User owner) {
        Intent i = new Intent(this, ShowRatingActivity.class);
        i.putExtra("User", owner);
        startActivity(i);
    }

    /**
     * Methode um Daten in die Spinner zu laden
     */
    public void addItemsOnSpinner(){
        //Daten anhand der Spinner ID holen und in Variable speichern
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        spinnerCity.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_spinner_item, allLists.getLocations()));

        Spinner approvalStatus = (Spinner) findViewById(R.id.enablingSpinner);
        approvalStatus.setAdapter(new ArrayAdapter<ApprovalStatus>(this, android.R.layout.simple_spinner_item, allLists.getStates()));

        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        cap.setAdapter(new ArrayAdapter<Capacity>(this, android.R.layout.simple_spinner_item, allLists.getCapacities()));

    }

    public List<TextView> getSearchedElements() {
        return searchedElements;
    }

    public void addSearchedElement(TextView view) {
        searchedElements.add(view);
    }

}
