package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.R;
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
/**
 * AsyncTask, für das Löschen einer Anfrage an eine fremde Fahrt.
 * Schickt die Anfrage an den Server und erhält einen ReturnCode zurück.
 *
 * @author Roman Busch
 *
 */
public class DeleteRequestTask extends AsyncTask<Object, Integer, SoapObject>
{
    private Context context;
    private Request request;
    private ShowOwnRequestActivity activity;

    private int idEditText = R.id.searchFriendButton;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public DeleteRequestTask(Context context, Request request, ShowOwnRequestActivity activity)
    {
        this.context = context;
        this.request = request;
        this.activity = activity;
    }

    @Override
    protected SoapObject doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRequestService().deleteRequest(myApp.getSession().getId(), request.getId());
               } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(SoapObject result)
    {
        //Prüfung des returnCodes
        if(result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Anfrage erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowOwnRequestActivity.class);
            //Aufruf eines neuen Tasks
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Wechsel zur ShowOwnRequestActivity
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}