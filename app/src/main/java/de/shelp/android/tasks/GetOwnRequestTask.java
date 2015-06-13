package de.shelp.android.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Created by user on 02.06.15.
 */


public class GetOwnRequestTask extends AsyncTask<Object, Integer, List<Request>>
{
    private Context context;
    private ShelpApplication myApp;
    private ShowOwnRequestActivity activity;

    private int nextAskedId =  R.id.ownRequest;


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
        } catch (InvalidRequestException | SoapFault e) {
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(List<Request> result)
    {
        if(result == null) {
            Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Anfragen gestellt.", Toast.LENGTH_SHORT).show();
        } else {
            for(Request request: result) {

                    RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                    RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams.addRule(RelativeLayout.BELOW, nextAskedId);
                    this.nextAskedId++;
                    TextView et = new TextView(context);
                    et.setId(nextAskedId);
                    et.setTextSize(20);
                    et.setTextColor(Color.BLACK);
                    et.setText("Benutzer: " + request.getTargedUser() + " " + request.getTour().getLocation() + " " + request.getStatus());
                    ll.addView(et, relativeParams);

            }
        }
    }
}