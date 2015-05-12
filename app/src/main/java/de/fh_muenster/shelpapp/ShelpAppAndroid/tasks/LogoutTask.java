package de.fh_muenster.shelpapp.ShelpAppAndroid.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import de.fh_muenster.shelpapp.ShelpApp.Exceptions.NoSessionException;
import de.fh_muenster.shelpapp.ShelpAppAndroid.MainActivity;
import de.fh_muenster.shelpapp.ShelpAppAndroid.ShelpAppApplication;


/**
 * Logout as AsyncTask
 */
public class LogoutTask extends AsyncTask<Void, Integer, Boolean>  {

    private Context context;
    private ShelpAppApplication myApp;

    public LogoutTask(Context context, ShelpAppApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Boolean doInBackground(Void... params){
        try {
            myApp.getShelpAppService().logout();
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
            myApp.setUser(null);

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
