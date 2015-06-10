package de.shelp.android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.fh_muenster.shelpapp.R;
import de.shelp.ksoap2.entities.Tour;

public class ShowTourActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tour_activity);

        Intent intent = getIntent();
        Tour tour = (Tour) intent.getSerializableExtra("Tour");
        TextView owner = (TextView) findViewById(R.id.owner);
        owner.setText(tour.getOwner().toString());

        TextView location = (TextView) findViewById(R.id.citySpinner);
        location.setText(tour.getOwner().toString());

        TextView capacity = (TextView) findViewById(R.id.capacitySpinner);
        capacity.setText(tour.getCapacity().toString());

        TextView payConditions = (TextView) findViewById(R.id.paySpinner);
        payConditions.setText(tour.getPaymentConditions().toString());

        TextView delConditions = (TextView) findViewById(R.id.delSpinner);
        delConditions.setText(tour.getDeliveryConditions().toString());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(tour.getTime().toString());

        TextView approval = (TextView) findViewById(R.id.enablingSpinner);
        approval.setText(tour.getApprovalStatus().toString());

        TextView status = (TextView) findViewById(R.id.status);
        status.setText(tour.getStatus().toString());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_tour_activity, menu);
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
