package de.shelp.android.tasks;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.shelp.android.CreateTourActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;

public class CreateTask extends AsyncTask<Object, Integer, String>
{
    private Context context;
    private Tour tour;
    private int sessionId;
    private static CreateTourActivity activity;
    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public CreateTask(Context context, Tour tour, int sessionId, CreateTourActivity activity)
    {
        this.tour = tour;
        this.sessionId = sessionId;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().createTour(tour, sessionId);
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
            Toast.makeText(activity.getApplicationContext(), "ERROR: Fahrt wurde nicht angelegt!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Fahrt wurde erfolgreich angelegt!", Toast.LENGTH_SHORT).show();
        }
    }
}
