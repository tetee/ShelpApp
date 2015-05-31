package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import de.shelp.ksoap2.exceptions.NoSessionException;
import de.shelp.android.MainActivity;
import de.shelp.android.applications.SessionApplication;


/**
 * Logout as AsyncTask
 */
public class LogoutTask extends AsyncTask<Void, Integer, Boolean>  {

    private Context context;
    private SessionApplication myApp;

    public LogoutTask(Context context, SessionApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Boolean doInBackground(Void... params){
        try {
            myApp.getUserService().logout(myApp);
            return true;
        } catch (NoSessionException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void onPostExecute(Boolean result) {
        if(result)
        {
            //erfolgreich ausgeloggt
            myApp.setSession(null);

            //Toast anzeigen
            CharSequence text = "Logout erfolgreich!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Nächste Activity anzeigen
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Logout fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Nächste Activity anzeigen
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }
}
