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

//Activity zum Erstellen einer Wunschliste zu einer Anfrage für eine bestimmte Tour
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

        if (id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    //Methode um weitere Wünsche zur Wunschliste hinzuzufügen
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

    //Methode um endgültige Anfrage zu schicken mit der Wunschliste
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
                //Toast.makeText(getApplicationContext(), "Es wurden nicht alle Felder gefüllt!", Toast.LENGTH_SHORT).show();

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



