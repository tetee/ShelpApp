package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.shelp.android.CreateTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 14.06.15.
 */
public class DeleteTourTask extends AsyncTask<Object, Integer, String>
{
    private Context context;
    private Tour tour;
    private int sessionId;
    private static ShowOwnTourActivity activity;
    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public DeleteTourTask(Context context, Tour tour,int sessionId, ShowOwnTourActivity activity)
    {
        this.tour = tour;
        this.context = context;
        this.sessionId = sessionId;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().deleteTour(tour.getId(), sessionId);
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(String result)
    {
        if(result.equals(ReturnCode.ERROR.toString())) {
            Toast.makeText(activity.getApplicationContext(), "ERROR: Fahrt wurde nicht gelöscht!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Fahrt wurde erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
        }
    }
}
