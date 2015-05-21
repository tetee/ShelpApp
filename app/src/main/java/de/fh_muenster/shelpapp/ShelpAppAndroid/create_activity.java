package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseErrorHandler;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.fh_muenster.shelpapp.ShelpApp.ApprovalStatus;
import de.fh_muenster.shelpapp.ShelpApp.Capacity;
import de.fh_muenster.shelpapp.ShelpApp.DeliveryCondition;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidTourException;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;
import de.fh_muenster.shelpapp.ShelpApp.Location;
import de.fh_muenster.shelpapp.ShelpApp.PaymentCondition;
import de.fh_muenster.shelpapp.ShelpApp.Request;
import de.fh_muenster.shelpapp.ShelpApp.Tour;
import de.fh_muenster.shelpapp.ShelpApp.TourStatus;
import de.fh_muenster.shelpapp.ShelpApp.User;

public class create_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        //Aufruf der addItemsOnSpinner Methode
        addItemsOnSpinner();
        //addItemsOnSpinnerDB();
    }


    public void addItemsOnSpinner(){

        //Daten anhand der Spinner ID holen und in Variable speichern
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        //Erstellen eines ArrayAdapters/ String Array und standard Layout des Spinners wird genutzt
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.citySpinner, android.R.layout.simple_spinner_item);

        //Anzeigewert f�r das Dropdown Feld definieren
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Werte in das Dropdown laden
        spinnerCity.setAdapter(adapter);

        //Daten der Spinner mit Enumeration Werten auffüllen
        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        cap.setAdapter(new ArrayAdapter<Capacity>(this, android.R.layout.simple_spinner_item, Capacity.values()));

        Spinner enabling = (Spinner) findViewById(R.id.enablingSpinner);
        enabling.setAdapter(new ArrayAdapter<ApprovalStatus>(this, android.R.layout.simple_spinner_item, ApprovalStatus.values()));

        Spinner pay = (Spinner) findViewById(R.id.paySpinner);
        pay.setAdapter(new ArrayAdapter<PaymentCondition>(this, android.R.layout.simple_spinner_item, PaymentCondition.values()));

        Spinner del = (Spinner) findViewById(R.id.delSpinner);
        del.setAdapter(new ArrayAdapter<DeliveryCondition>(this, android.R.layout.simple_spinner_item, DeliveryCondition.values()));
    }

   /* public void addItemsOnSpinnerDB(){
        Spinner cap = (Spinner) findViewById(R.id.capacitySpinner);
        //database Handler
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        //Spinner Drop Down Elemente
        List<String> elements = db.getAllLabels();
        //Adapter erstellen
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elements);
        //Elemente zum Adapter hinzufügen
        cap.setAdapter(adapter);
    }*/


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



    public void createTour(View createView) {
        Long id = 123L;
        User owner = new User("Busch.Roman20@gmail.com", "test123");
        //Inhalt der Spinner erhalten
        Spinner loc = (Spinner)findViewById(R.id.citySpinner);
        //Location location = (Location) loc.getSelectedItem();
        String location = loc.getSelectedItem().toString();

        Spinner cap = (Spinner)findViewById(R.id.capacitySpinner);
        String capacity = cap.getSelectedItem().toString();

        Spinner pay = (Spinner)findViewById(R.id.paySpinner);
        String payCondition = loc.getSelectedItem().toString();

        Spinner del = (Spinner)findViewById(R.id.delSpinner);
        String delCondition = loc.getSelectedItem().toString();

        Spinner enab = (Spinner)findViewById(R.id.enablingSpinner);
        String approval = loc.getSelectedItem().toString();

        //Inhalt des Datums auslesen
        TextView dateText = (TextView)findViewById(R.id.date);
        String date = dateText.getText().toString();


            //CreateTask erstellen
            CreateTask createTask = new CreateTask(createView.getContext());
            //CreateTask ausführen
            createTask.execute(id,approval,location,capacity,payCondition,delCondition,date,null,owner,null,TourStatus.PLANED);


        /**Toast.makeText();
         CharSequence text = "Fahrt erfolgreich erstellt";
         int duration = Toast.LENGTH_SHORT;
         Toast toast = Toast.makeText(context, text, duration);
         toast.show();*/
        Toast.makeText(getApplicationContext(), "Fahrt erfolgreich erstellt!", Toast.LENGTH_SHORT).show();

        //nach erfolgreicher Erstellung der Tour zurück zur shelp_activity
        Intent i = new Intent(this, shelp_activity.class);
        startActivity(i);

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
            if(params.length != 11)
                return null;
            long id = (Long) params[0];
            ApprovalStatus approval = (ApprovalStatus) params[1];
            Location location = (Location) params[2];
            Capacity capacity = (Capacity) params[3];
            PaymentCondition payCondition = (PaymentCondition) params[4];
            DeliveryCondition delCondition = (DeliveryCondition) params[5];
            Calendar date = (Calendar) params[6];
            List<Request> request = (List<Request>) params[7];
            User owner = (User) params[8];
            Calendar updatedOn = (Calendar) params[9];
            TourStatus status = (TourStatus) params[10];
            ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
            try {
                Tour newTour = myApp.getShelpAppService().newTour(id,approval,location,capacity,payCondition,delCondition,date,request,owner,updatedOn,status);
                return newTour;
            } catch (InvalidTourException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgessUpdate(Integer... values)
        { }

        protected void onPostExecute(Tour result)
        {
            if(result != null)
            {
                //erfolgreich eingeloggt
                ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
                myApp.setTour(result);

                //Toast anzeigen
                CharSequence text = "Tour erfolgreich erstellt" ;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Nächste Activity anzeigen
                Intent i = new Intent(context, shelp_activity.class);
                startActivity(i);
            }
            else
            {
                //Toast anzeigen
                CharSequence text = "Tour konnte nicht erstellt werden";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

}
