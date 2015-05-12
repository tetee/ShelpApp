package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import de.fh_muenster.shelpapp.R;

public class create_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        //Aufruf der addItemsOnSpinner Methode
        addItemsOnSpinner();
    }


    public void addItemsOnSpinner(){

        //Daten anhand der Spinner ID holen und in Variable speichern
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        Spinner spinnerCapacity = (Spinner) findViewById(R.id.capacitySpinner);
        Spinner spinnerEnabling = (Spinner) findViewById(R.id.enablingSpinner);
        Spinner spinnerRange = (Spinner) findViewById(R.id.rangeSpinner);
        //Erstellen eines ArrayAdapters/ String Array und standard Layout des Spinners wird genutzt
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.citySpinner, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.capacitySpinner, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.enablingSpinner, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.rangeSpinner, android.R.layout.simple_spinner_item);

        //Anzeigewert f�r das Dropdown Feld definieren
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Werte in das Dropdown laden
        spinnerCity.setAdapter(adapter);
        spinnerCapacity.setAdapter(adapter2);
        spinnerEnabling.setAdapter(adapter3);
        spinnerRange.setAdapter(adapter4);
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
}
