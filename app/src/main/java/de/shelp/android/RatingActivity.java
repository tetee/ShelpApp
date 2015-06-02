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

public class RatingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rating_activity, menu);
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

    //Aufruf der ShowOwnRequestActivity
    public void sendRating(View view) {
        RatingBar rating = (RatingBar) findViewById(R.id.rating);
        EditText ratingText = (EditText) findViewById(R.id.editTextRating);
        String txtRating = ratingText.getText().toString();
        int ratingBar = (int)rating.getRating();

        ShelpApplication application = (ShelpApplication) getApplication();

        RatingTask ratingTask = new RatingTask(view.getContext(), new User("tt"), ratingBar, txtRating, application.getSession().getId(), this);
        ratingTask.execute();

        Intent i = new Intent(this, ShowOwnRequestActivity.class);
        startActivity(i);
    }
}
