package de.shelp.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.actionlistener.ShowRequestListener;
import de.shelp.ksoap2.ServiceUtils;
import de.shelp.ksoap2.entities.Tour;


/**
 * Activity um Tour Details anzuzeigen und diese ggf. anzufragen
 * {@link #showRequest(View, Tour)}
 * {@link #request(View)}
 * In der Methode onCreate() wird die Fahrt abgebildet, ohne das noch Änderungen gemacht werden können.
 *
 * @author
 *
 */
public class ShowTourActivity extends ActionBarActivity {

    Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tour_activity);

        Intent intent = getIntent();
        tour = (Tour) intent.getSerializableExtra("Tour");
        //Abfrage des Besitzers der Tour
        //Ist der Besitzer der User wird der Anfrage Button ausgeblendet um das Anfragen eigener Touren zu verhindern
        boolean ownerIntent = (boolean) intent.getSerializableExtra("Owner");
        if(ownerIntent == true){
            Button button = (Button) findViewById(R.id.requestButton);
            button.setVisibility(View.GONE);

            //Anlegen eines neuen Buttons um vorhandene Anfragen an eigens erstellte Tour zu zeigen
            RelativeLayout ll = (RelativeLayout) this.findViewById(R.id.relativeLayoutShowTourActivity);
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams.addRule(RelativeLayout.BELOW, R.id.status);
            Button request = new Button(getApplicationContext());
            request.setBackgroundResource(R.drawable.button);
            request.setText("Anfragen");
            request.setTextColor(Color.BLACK);
            request.setOnClickListener(new ShowRequestListener(tour, this));
            ll.addView(request, relativeParams);

        }
        //Tour Details und ausgeben
        TextView owner = (TextView) findViewById(R.id.owner);
        owner.setText(tour.getOwner().toString());

        TextView location = (TextView) findViewById(R.id.citySpinner);
        location.setText(tour.getLocation().toString());

        TextView capacity = (TextView) findViewById(R.id.capacitySpinner);
        capacity.setText(tour.getCapacity().toString());

        TextView payConditions = (TextView) findViewById(R.id.paySpinner);
        payConditions.setText(tour.getPaymentConditions().toString());

        TextView delConditions = (TextView) findViewById(R.id.delSpinner);
        delConditions.setText(tour.getDeliveryConditions().toString());

        TextView date = (TextView) findViewById(R.id.dateCreate);
        //Test des Datums
        try {
            date.setText(ServiceUtils.formatDatetoString(new Date(tour.getTime())));
        } catch (ParseException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Falsches Format!", Toast.LENGTH_SHORT).show();
        }

        TextView approval = (TextView) findViewById(R.id.enablingSpinner);
        approval.setText(tour.getApprovalStatus().toString());

        TextView status = (TextView) findViewById(R.id.status);
        status.setText(tour.getStatus().toString());

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
     * Methode um eine Tour anzufragen. Für Anfrage zunächst Weiterleitung zur WishlistActivity.
     * @param v - Die aktuell sichtbare View.
     *
     */
    public void request(View v){
        Intent i = new Intent(this, WishlistActivity.class);
        //Übergabe der entsprechenden Tour
        i.putExtra("Tour", tour);
        startActivity(i);
    }

    /**
     * Methode um bereits gestellte Anfragen an eine bestimmte Tour anzuzeigen.
     * @param v - Die aktuell sichtbare View
     * @param tour - Fahrt wird an eine andere Activity übergeben
     *
     */
    public void showRequest(View v, Tour tour){
        Intent i = new Intent(this, ShowTourRequestActivity.class);
        i.putExtra("Tour", tour);
        startActivity(i);
    }

}
