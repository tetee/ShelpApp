package de.shelp.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.tasks.SearchTask;
import de.shelp.android.tasks.SearchUserTask;

public class SearchFriendActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends_activity);
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

    //Durch betätigen des Suchen Button erscheinen gesuchte Freunde(TODO)
    //und der Hinzufügen Button erscheint
    public void search(View view){

        EditText search = (EditText) findViewById(R.id.searchFriendByName);
        String searchText = search.getText().toString();

        SearchUserTask searchTask = new SearchUserTask(view.getContext(), searchText, this);
        searchTask.execute();

        //Button suchen
       // Button addButton = (Button) findViewById(R.id.addButton);
        //Button sichtbar machen
      //  addButton.setVisibility(View.VISIBLE);
    }

    //Mit Klick auf Hinzufügen wird der Freund angefragt und Rückkehr zur Shelp Activity
    public void add(View view) {
        /**Toast.makeText();
         CharSequence text = "Freund erfolgreich hinzugefügt!";
         int duration = Toast.LENGTH_SHORT;
         Toast toast = Toast.makeText(context, text, duration);
         toast.show();*/

        //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
        Toast.makeText(getApplicationContext(), "Freund erfolgreich hinzugefügt!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ShelpActivity.class);
        startActivity(i);
    }
}
