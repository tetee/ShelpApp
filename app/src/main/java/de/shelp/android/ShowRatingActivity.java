package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.tasks.GetRatingsTask;
import de.shelp.ksoap2.entities.User;

/**
 * Activity um Bewertungen eines bestimmten Benutzers anzusehen.
 * In der onCreate() Methode wird der AsyncTask zum Abfragen von Bewertungen aufgerufen {@link de.shelp.android.tasks.GetRatingsTask}.
 *
 * @author Roman Busch
 *
 */
//
public class ShowRatingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rating_activity);

        Intent intent = getIntent();
        //Annahme des mit dem Intent übergebenen User Object
        User user = (User) intent.getSerializableExtra("User");

        //Bewertungen über AsyncTask vom Server laden
        GetRatingsTask ratingTask = new GetRatingsTask(getApplicationContext(), this, user);
        ratingTask.execute();

        //Name des bewerteten Users ausgeben
        TextView owner = (TextView) findViewById(R.id.user);
        owner.setText(user.toString());
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
}
