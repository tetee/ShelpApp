package de.shelp.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.CreateTask;
import de.shelp.android.tasks.RatingTask;
import de.shelp.ksoap2.entities.User;

/**
 * Activity, die die Berwetung eines Benutzers ermöglicht {@link #sendRating(android.view.View)}
 * über den AsynsTask{@link de.shelp.android.tasks.RatingTask}
 *
 * @author
 *
 */
public class RatingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_activity);
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
     * Methode um einen anderen Benutzer zu bewerten über Task {@link de.shelp.android.tasks.RatingTask}
     * anschließen Aufruf der {@link de.shelp.android.ShowOwnRequestActivity} um Anfragen des Benutzers anzuzeigen
     *
     * @param view - Die aktuell sichtbare View
     */
    public void sendRating(View view) {
        RatingBar rating = (RatingBar) findViewById(R.id.rating);
        EditText ratingText = (EditText) findViewById(R.id.editTextRating);
        String txtRating = ratingText.getText().toString();
        int ratingBar = (int)(rating.getRating() * 10);
        Intent intent = getIntent();
        ShelpApplication application = (ShelpApplication) getApplication();

        //Benutzer bewerten im AsyncTask
        RatingTask ratingTask = new RatingTask(view.getContext(), (User) intent.getSerializableExtra("RatingUser"), ratingBar, txtRating, application.getSession().getId(), this);
        ratingTask.execute();

        //Wechsel in die ShowOwnRequestActivity
        Intent i = new Intent(this, ShowOwnRequestActivity.class);
        startActivity(i);
    }


}
