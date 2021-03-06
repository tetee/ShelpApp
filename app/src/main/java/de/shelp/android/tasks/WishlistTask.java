package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.List;

import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.WishlistActivity;
import de.shelp.android.applications.ShelpApplication;

/**
 * AsyncTask, für das Erstellen einer Wunschliste.
 * Schickt die Anfrage an den Server und erhält einen ReturnCode zurück.
 *
 * @author Theresa Sollert
 *
 */
public class WishlistTask extends AsyncTask<Object, Integer, SoapObject> {
    private Context context;
    private Long tourId;
    private List<String> wishes;
    private String notice;
    private int sessionId;
    private static WishlistActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public WishlistTask(Context context, Long tourId, List<String> wishes, String notice, int sessionId, WishlistActivity activity) {
        this.tourId = tourId;
        this.wishes = wishes;
        this.notice = notice;
        this.context = context;
        this.sessionId = sessionId;
        this.activity = activity;
    }

    @Override
    protected SoapObject doInBackground(Object... params) {
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRequestService().createRequest(tourId, wishes, notice, sessionId);
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }


    protected void onProgessUpdate(Integer... values) {
    }

    protected void onPostExecute(SoapObject result) {
        if (result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Anfrage erfolgreich erstellt!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowOwnRequestActivity.class);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }


    }
}
