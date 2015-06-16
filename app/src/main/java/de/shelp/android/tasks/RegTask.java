package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import de.shelp.android.RegActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;

/**
 * Created by user on 15.06.15.
 */
public class RegTask extends AsyncTask<String, Integer, ShelpSession> {
    private Context context;
    private RegActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public RegTask(Context context, RegActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected ShelpSession doInBackground(String... params) {
        if (params.length != 2)
            return null;
        String eMail = params[0];
        String hash = params[1];
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getUserService().registration(eMail, hash);
        } catch (InvalidRegistrationException e) {
           return null;
        }
    }

    protected void onProgessUpdate(Integer... values) {
    }

    protected void onPostExecute(ShelpSession result) {
        if (result != null) {
            //erfolgreich eingeloggt
            ShelpApplication myApp = (ShelpApplication) activity.getApplication();
            myApp.setSession(result);

            //Toast anzeigen
            CharSequence text = "Registrierung erfolgreich! Angemeldeter Benutzername: " + result.getUser();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Nächste Activity anzeigen
            Intent i = new Intent(context, ShelpActivity.class);
            context.startActivity(i);
        } else {
            //Toast anzeigen
            CharSequence text = "Registrierung fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}