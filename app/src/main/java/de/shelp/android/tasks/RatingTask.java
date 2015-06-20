package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.android.FriendsActivity;
import de.shelp.android.RatingActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.User;

/**
 * AsyncTask, für das Bewerten eines Benutzers .
 * Schickt die Anfrage an den Server und erhält einen ReturnCode zurück.
 *
 * @author
 *
 */
public class RatingTask extends AsyncTask<Object, Integer, SoapObject>{

    private Context context;
    private User targetUser;
    private int rating;
    private String notice;
    private static RatingActivity activity;
    private int sessionId;

    public RatingTask(Context context, User user, int rating, String notice, int sessionId, RatingActivity activity) {
        this.context = context;
        this.targetUser = user;
        this.rating = rating;
        this.notice = notice;
        this.activity = activity;
        this.sessionId = sessionId;
    }

    @Override
    protected SoapObject doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRatingService().createRating(targetUser, rating, notice, sessionId);
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
            Toast.makeText(context.getApplicationContext(), "Bewertung erfolgreich erstellt!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShelpActivity.class);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }

    }
}


