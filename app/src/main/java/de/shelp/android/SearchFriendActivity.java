package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.AddFriendTask;
import de.shelp.android.tasks.SearchUserTask;
import de.shelp.ksoap2.entities.User;

/**
 * Activity, für das Suchen nach einem neuen Freund/neuen Freunden {@link #search(android.view.View)}
 * und das Hinzufügen eines neuen Freundes {@link #add(android.view.View, de.shelp.ksoap2.entities.User)}
 * {@link #getSearchedElements()} Die gesuchten Freunde werden angezeigt
 * {@link #addSearchedElement(android.widget.TextView)} und zur bestehenden View hinzugefügt.
 *
 * @author Theresa Sollert
 *
 */
public class SearchFriendActivity extends ActionBarActivity {

    private List<TextView> searchedElements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends_activity);
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
     * Methode um nach Freunden zu suchen über den {@link de.shelp.android.tasks.SearchUserTask}
     *
     * @param view - Die aktuell sichtbare View
     */
    public void search(View view){

        EditText search = (EditText) findViewById(R.id.searchFriendByName);
        String searchText = search.getText().toString();

        SearchUserTask searchTask = new SearchUserTask(view.getContext(), searchText, this);
        searchTask.execute();

    }

    /**
     * Methode um einen neuen Freund hinzuzufügen über den {@link de.shelp.android.tasks.AddFriendTask}.
     *
     * @param view - Die aktuell sichtbare View.
     * @param user - der hinzuzufügende Freund.
     */
    public void add(View view, User user) {
        AddFriendTask addFriendTask = new AddFriendTask(view.getContext(),(ShelpApplication) this.getApplication(), user);
        addFriendTask.execute();
    }


    public List<TextView> getSearchedElements() {
        return searchedElements;
    }

    public void addSearchedElement(TextView view) {
        searchedElements.add(view);
    }

}
