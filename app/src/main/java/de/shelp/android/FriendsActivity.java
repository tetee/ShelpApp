package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.ChangeFriendshipTask;
import de.shelp.android.tasks.GetFriendsTask;
import de.shelp.ksoap2.entities.Friendship;

/**
 * Activity, die bestehende Freunde anzeigt über den AsyncTask {@link de.shelp.android.tasks.GetFriendsTask}
 * sowie die Suche nach neuen Freunden {@link #searchFriend(android.view.View)} und
 * das Statusänderungen {@link #changeFriendship(de.shelp.ksoap2.entities.Friendship, int)} ermöglicht.
 *
 * @author
 *
 */
public class FriendsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_activity);

        GetFriendsTask getFriendsTask = new GetFriendsTask(this.getApplicationContext(), (ShelpApplication) getApplication(), this);
        getFriendsTask.execute();

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
     * Methode um zur {@link de.shelp.android.SearchFriendActivity} zu gelangen um hier neue Freunde zu suchen
     * Methode um zur SearchFriendActivity zu gelangen um hier neue Freunde zu suchen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void searchFriend(View view) {
        Intent i = new Intent(this, SearchFriendActivity.class);
        startActivity(i);
    }

    /**
     * Methode um den Freundschaftsstaus zu verändern über {@link de.shelp.android.tasks.ChangeFriendshipTask}
     * @param fs - Freundschaft deren Status verändert werden soll
     * Methode um den Freundschaftsstaus zu verändern.
     * @param fs - Freundschaft deren Status verändert werden soll.
     * @param changeType - 0 - annehmen, 1 - ablehnen, rest - löschen
     */
    public void changeFriendship(Friendship fs, int changeType) {
        ChangeFriendshipTask changeFriendshipTask = new ChangeFriendshipTask(fs,(ShelpApplication) getApplication(),this.getApplicationContext(),changeType);
        changeFriendshipTask.execute();
    }

}
