package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import de.fh_muenster.shelpapp.R;

public class search_activity extends ActionBarActivity {



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
        //Button suchen
        Button detailsButton = (Button) findViewById(R.id.detailsButton);
        Button ratingButton = (Button) findViewById(R.id.ratingButton);
        //Button sichtbar machen
        detailsButton.setVisibility(View.VISIBLE);
        ratingButton.setVisibility(View.VISIBLE);
    }

    //Wechsel in die create_activity
    public void details(View view){
        Intent i = new Intent(this, create_activity.class);
        startActivity(i);
    }

    //Wechsel in die show_rating_activity
    public void rating(View view) {
        Intent i = new Intent(this, show_rating_activity.class);
        startActivity(i);
    }

    public void addItemsOnSpinner(){
        //Daten anhand der Spinner ID holen und in Variable speichern
        Spinner spinnerCity = (Spinner) findViewById(R.id.citySpinner);
        Spinner spinnerCapacity = (Spinner) findViewById(R.id.capacitySpinner);
        Spinner spinnerRange = (Spinner) findViewById(R.id.rangeSpinner);
        //Erstellen eines ArrayAdapters/ String Array und standard Layout des Spinners wird genutzt
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.citySpinner, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.capacitySpinner, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.rangeSpinner, android.R.layout.simple_spinner_item);

        //Anzeigewert für das Dropdown Feld definieren
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Werte in das Dropdown laden
        spinnerCity.setAdapter(adapter);
        spinnerCapacity.setAdapter(adapter2);
        spinnerRange.setAdapter(adapter3);
    }
}
