package de.shelp.android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.AddFriendTask;
import de.shelp.android.tasks.SearchUserTask;
import de.shelp.ksoap2.entities.User;

public class SearchFriendActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_friends_activity, menu);
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

    //Durch betätigen des Suchen Button erscheinen gesuchte Freunde(TODO)
    //und der Hinzufügen Button erscheint
    public void search(View view){

        EditText search = (EditText) findViewById(R.id.searchFriendByName);
        String searchText = search.getText().toString();

        SearchUserTask searchTask = new SearchUserTask(view.getContext(), searchText, this);
        searchTask.execute();

    }

    //Mit Klick auf Hinzufügen wird der Freund angefragt und Rückkehr zur Shelp Activity
    public void add(View view, User user) {
        AddFriendTask addFriendTask = new AddFriendTask(view.getContext(),(ShelpApplication) this.getApplication(), user);
        addFriendTask.execute();
    }
}
