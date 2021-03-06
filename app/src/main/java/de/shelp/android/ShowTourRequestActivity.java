package de.shelp.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import de.shelp.R;
import de.shelp.android.actionlistener.CheckboxListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.AcceptRequestTask;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.entities.WishlistItem;

/**
 * Activity um Anfragen zu eigens erstellter Tour anzuzeigen.
 * {@link #acceptRequest(Map, Request)}.
 * Das annehmen der Anfragen läuft über den {@link de.shelp.android.tasks.AcceptRequestTask}.
 * In der onCreate() Methode werden dynamisch die Anfragen zu den entsprechenden Fahrten aufgelistet.
 * Aufgelistet wird der Anfrager, der Kurztext und die Checkbox mit dem entsprechenden Wunsch.
 *
 * @author Theresa Sollert
 *
 */
public class ShowTourRequestActivity extends ActionBarActivity {
   private Tour tour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tour_request);

        //Annahme der übergebenen Tour
        Intent intent = getIntent();
        tour = (Tour) intent.getSerializableExtra("Tour");

        int lastEdit = R.id.relativeLayoutShowTourRequest;

        //Für die Tour wird für jedes Request Object eine Schleife durchlaufen um zugehörige Daten auszugeben
        //Der Fahrt Anbieter kann die Anfrage (teilweise) annehmen
        for (int i = 0; i <= tour.getRequest().size() - 1; i++) {
            RelativeLayout ll = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourRequest);
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

            Request req = tour.getRequest().get(i);
            relativeParams.addRule(RelativeLayout.BELOW, lastEdit);
            lastEdit++;
            TextView request = new TextView(getApplicationContext());
            request.setTextSize(20);
            request.setId(lastEdit);
            request.setTextColor(Color.BLACK);
            request.setText("Anfrager: " + req.getSourceUser() + " Status: " + req.getStatus() + " Tour: " + tour.getLocation().toString());
            ll.addView(request, relativeParams);

            RelativeLayout ll1 = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourRequest);
            RelativeLayout.LayoutParams relativeParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams1.addRule(RelativeLayout.BELOW, lastEdit);
            lastEdit++;
            TextView text = new TextView(getApplicationContext());
            text.setTextSize(20);
            text.setId(lastEdit);
            text.setTextColor(Color.BLACK);
            text.setText("Kurztext: " + req.getNotice());
            ll1.addView(text, relativeParams1);

            Map<WishlistItem, CheckBox> wishMap = new HashMap<>();

            for (int j = 0; j < req.getWishes().size(); j++) {
                RelativeLayout ll2 = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourRequest);
                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, lastEdit);
                lastEdit++;
                CheckBox wishBox = new CheckBox(getApplicationContext());
                wishBox.setId(lastEdit);
                // wishBox.setButtonDrawable(R.drawable.checkbox);
                wishBox.setTextColor(Color.BLACK);
                WishlistItem item = req.getWishes().get(j);
                wishBox.setText(item.getText());
                ll2.addView(wishBox, relativeParams2);
                wishMap.put(item, wishBox);
            }

            if(req.getStatus().equals("ASKED") && tour.getStatus().equals(TourStatus.PLANNED)) {
                RelativeLayout ll3 = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourRequest);
                RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, lastEdit);
                lastEdit++;
                Button bt3 = new Button(getApplicationContext());
                bt3.setBackgroundResource(R.drawable.button);
                bt3.setId(lastEdit);
                bt3.setTextColor(Color.BLACK);
                bt3.setText("Anfrage senden");
                ll3.addView(bt3, relativeParams3);

                bt3.setOnClickListener(new CheckboxListener(wishMap, this, req));
            }
        }
    }

    /**
     * Methode um den Anfragen zu Fahrten anzunehmen.
     * @param wishMap - Map die, die Checkboxen und die dazugehörigen Wünsche speichert
     * @param request - Anfrage zu der einer Fahrt
     */
    public void acceptRequest(Map<WishlistItem,CheckBox> wishMap, Request request) {
        AcceptRequestTask acceptRequestTask = new AcceptRequestTask((ShelpApplication) getApplication(), request, wishMap, this );
        acceptRequestTask.execute();
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
