package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.android.CreateTourActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;

/**
 * AsyncTask, für das Ändern des Tourstatusses (CANCELLED).
 * Schickt die Anfrage an den Server und erhält einen ReturnCode zurück.
 *
 * @author
 *
 */
public class DeleteTourTask extends AsyncTask<Object, Integer, SoapObject>
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
    protected SoapObject doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().deleteTour(tour.getId(), sessionId);
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(SoapObject result)
    {
        if(result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Tour erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowOwnTourActivity.class);
            //Wechsel zur ShowOwnTourActivity
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}
