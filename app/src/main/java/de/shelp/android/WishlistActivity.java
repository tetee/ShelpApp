package de.shelp.android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.WishlistTask;
import de.shelp.ksoap2.entities.Tour;

/**
 * Activity zum Erstellen einer Wunschliste zu einer Anfrage, für eine bestimmte Tour.
 * {@link #addTextView(android.view.View)}
 * {@link #requestWishList(android.view.View)}
 * Das erstellen der Wunschliste läuft über den AsyncTask {@link de.shelp.android.tasks.WishlistTask}
 *
 * @author
 *
 */
public class WishlistActivity extends ActionBarActivity {

    private int lastEditText = R.id.editTextWishList;
    private int idEditText = 1;
    private String targedUserId;
    private Long tourId;
    private List<EditText> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist_activity);

        //eingegebene Daten abfragen und speichern
        EditText wish = (EditText) findViewById(R.id.editTextWishList);
        list.add(wish);
        Intent intent = getIntent();
        Tour tour = (Tour) intent.getSerializableExtra("Tour");
        targedUserId = tour.getOwner().toString();
        tourId = tour.getId();

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

        if (id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Methode um weitere Wünsche zur Wunschliste hinzuzufügen
     *
     * @param view - Die aktuell sichtbare View
     */
    public void addTextView(View view) {
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.relativeLayout);

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        relativeParams.addRule(RelativeLayout.BELOW, lastEditText);
        this.idEditText++;
        EditText et = new EditText(this);
        et.setId(idEditText);
        this.lastEditText = et.getId();
        et.setHint("+ Wunsch");
        list.add(et);
        ll.addView(et, relativeParams);
    }

    /**
     * Methode um die Wunschliste an den Benutzer zu senden.
     * Prüfung ob mindestens ein Wunsch gefüllt ist.
     * Die Wünsche und der Kurztext werden an den {@link de.shelp.android.tasks.WishlistTask} gesendet.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void requestWishList(View view) {

        boolean oneFilled = false;
        for (int j = 0; j < list.size(); j++) {
            if (!list.get(j).getText().toString().trim().equals("")) {
                oneFilled = true;
            }
        }
        if (oneFilled) {
            List<String> wishes = new ArrayList<String>();

            for (EditText et : list) {

                wishes.add(et.getText().toString());

            }
            EditText noticeText = (EditText) findViewById(R.id.wishEditText);
            String notice = noticeText.getText().toString();

            ShelpApplication application = (ShelpApplication) getApplication();

            //Sendern der Daten Daten an den Server über einen AsynsTask
            WishlistTask wishlistTask = new WishlistTask(view.getContext(), tourId, wishes, notice, application.getSession().getId(), this);
            wishlistTask.execute();

            //Wechsel in die ShelpActivity
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        } else {

            Toast.makeText(getApplicationContext(), "Es wmuss mind. ein Feld gefüllt sein!", Toast.LENGTH_SHORT).show();
        }
    }
}



