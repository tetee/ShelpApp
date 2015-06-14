package de.shelp.android;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.actionlistener.ShowRequestListener;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;

public class ShowTourRequestActivity extends ActionBarActivity {
    Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tour_request);

        Intent intent = getIntent();
        tour = (Tour) intent.getSerializableExtra("Tour");

        int lastEdit = R.id.relativeLayoutShowTourRequest;


        for(int i = 0; i <=tour.getRequest().size()-1; i++){
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

            RelativeLayout ll2 = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourRequest);
            RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams2.addRule(RelativeLayout.BELOW, lastEdit);
            lastEdit++;
            TextView wishText = new TextView(getApplicationContext());
            wishText.setId(lastEdit);
            wishText.setTextColor(Color.BLACK);
            wishText.setText("Wünsche: "+ tour.getCapacity().toString());
            ll2.addView(wishText, relativeParams2);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_tour_request, menu);
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
}
