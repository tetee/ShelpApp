package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import de.shelp.android.MainActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ShelpSession;
import de.shelp.ksoap2.exceptions.InvalidLoginException;

public class LoginTask extends AsyncTask<String, Integer, ShelpSession> {
    private Context context;
    private static MainActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public LoginTask(Context context, MainActivity activity) {
        this.context = context;
        this.activity = activity;

    }

    @Override
    protected ShelpSession doInBackground(String... params) {
        if (params.length != 2)
            return null;
        String username = params[0];
        String hash = params[1];
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getUserService().login(username, hash);
        } catch (InvalidLoginException e) {
            Toast.makeText(activity.getApplicationContext(), "Fehler: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onProgessUpdate(Integer... values) {
    }

    protected void onPostExecute(ShelpSession result) {
        if (result != null) {
            //erfolgreich eingeloggt
            ShelpApplication myApp = (ShelpApplication) activity.getApplication();
            myApp.setSession(result);

            //Toast anzeigen
            CharSequence text = "Login erfolgreich! Angemeldeter Benutzername: " + result.getUser();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Nächste Activity anzeigen
            Intent i = new Intent(context, ShelpActivity.class);
            activity.startActivity(i);
        } else {
            //Toast anzeigen
            CharSequence text = "Login fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}