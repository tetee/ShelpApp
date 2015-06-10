package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.SearchTourActivity;
import de.shelp.android.WishlistActivity;
import de.shelp.android.actionlistener.ShowRatingsListener;
import de.shelp.android.actionlistener.ShowTourDetailsListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 10.06.15.
 */
public class WishlistTask extends AsyncTask<Object, Integer, String>
{
    private Context context;
    private String targedUserId;
    private Long tourId;
    private List<String> wishes;
    private String notice;
    private int sessionId;
    private static WishlistActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public WishlistTask(Context context, String targedUserId, Long tourId, List<String> wishes, String notice, int sessionId, WishlistActivity activity)
    {
        this.targedUserId=targedUserId;
        this.tourId=tourId;
        this.wishes=wishes;
        this.notice=notice;
        this.context = context;
        this.sessionId = sessionId;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRequestService().createRequest(targedUserId, tourId, wishes, notice, sessionId);
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }



    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(String result)
    {
        if(result.equals(ReturnCode.ERROR.toString())) {
            Toast.makeText(activity.getApplicationContext(), "ERROR: Anfrage wurde nicht angelegt!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Anfrage wurde erfolgreich angelegt!", Toast.LENGTH_SHORT).show();
        }
    }

}
