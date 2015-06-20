package de.shelp.android.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.shelp.android.applications.ShelpApplication;

/**
 * AsyncTask, für das Laden der Listen vom Server.
 *
 * @author Theresa Sollert
 *
 */
public class SetListTask extends AsyncTask<Object, Object, Boolean> {
    private Context context;
    private Activity activity;
    private Class nextActivity;
    private ShelpApplication app;

    public SetListTask(Context context, Activity activity,Class nextActivity, ShelpApplication app) {
        this.context = context;
        this.activity = activity;
        this.nextActivity=nextActivity;
        this.app = app;
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        try {
            if (app.getAllLists() == null) {
                app.setAllLists(app.getShelpAppService().getLists());
            }
            return true;
        }
        catch (SoapFault e) {
            return false;
        }
    }

    protected void onProgessUpdate(Object... values) {
    }

    protected void onPostExecute(Boolean result) {
        if(result = true){
            Intent i = new Intent(activity, nextActivity);
            context.startActivity(i);}
        else{
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
    }
}
