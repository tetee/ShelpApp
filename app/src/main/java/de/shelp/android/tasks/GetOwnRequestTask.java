package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.FriendsActivity;
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.actionlistener.AddDeleteListener;
import de.shelp.android.actionlistener.AddRatingListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.InvalidRequestException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * Created by user on 02.06.15.
 */


public class GetOwnRequestTask extends AsyncTask<Object, Integer, List<Request>> {
    private Context context;
    private ShelpApplication myApp;
    private ShowOwnRequestActivity activity;

    private int nextAskedId = R.id.ownRequest;


    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetOwnRequestTask(Context context, ShelpApplication myApp, ShowOwnRequestActivity activity) {
        this.context = context;
        this.myApp = myApp;
        this.activity = activity;
    }

    @Override
    protected List<Request> doInBackground(Object... params) {
        try {
            return myApp.getOwnRequestService().getRequest(myApp.getSession().getId());
        } catch (InvalidRequestException e) {
                Toast.makeText(activity.getApplicationContext(), "Fehler: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values) {
    }

    protected void onPostExecute(List<Request> result) {
        if (result == null) {
            Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Anfragen gestellt.", Toast.LENGTH_SHORT).show();
        } else {
            for (Request request : result) {

                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, nextAskedId);
                this.nextAskedId++;
                TextView et = new TextView(context);
                et.setId(nextAskedId);
                et.setTextSize(20);
                relativeParams.setMargins(0, 0, 0, 20);
                et.setTextColor(Color.BLACK);
                et.setText("Benutzer: " + request.getTargedUser() + " " + request.getTour().getLocation() + " " + request.getStatus());
                ll.addView(et, relativeParams);

                RelativeLayout ll2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, nextAskedId);
                this.nextAskedId++;
                Button bt2 = new Button(context);
                bt2.setId(nextAskedId);
                relativeParams2.setMargins(0, 0, 0, 20);
                bt2.setTextColor(Color.BLACK);
                bt2.setText("löschen");
                bt2.setBackgroundResource(R.drawable.button);
                ll2.addView(bt2, relativeParams2);

                RelativeLayout ll3 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, nextAskedId);
                this.nextAskedId++;
                Button bt3 = new Button(context);
                bt3.setId(nextAskedId);
                relativeParams3.setMargins(0, 0, 0, 20);
                bt3.setTextColor(Color.BLACK);
                bt3.setText("Bewertung");
                bt3.setBackgroundResource(R.drawable.button);
                ll3.addView(bt3, relativeParams3);

                bt3.setOnClickListener(new AddRatingListener(request, activity));
                bt2.setOnClickListener(new AddDeleteListener(request, activity));
            }
        }
    }
}