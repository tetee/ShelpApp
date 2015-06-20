package de.shelp.android.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.ksoap2.SoapFault;

import de.shelp.android.applications.ShelpApplication;

/**
 * Created by user on 20.06.15.
 */
public class SetListTask extends AsyncTask<Object, Object, Object> {
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
    protected Object doInBackground(Object... params) {
        try {
            if (app.getAllLists() == null) {
                app.setAllLists(app.getShelpAppService().getLists());
            }
        } catch (SoapFault ex) {
            //TODO errorhandling
        }
        return null;
    }

    protected void onProgessUpdate(Object... values) {
    }

    protected void onPostExecute(Object result) {
        Intent i = new Intent(activity, nextActivity);
        context.startActivity(i);
    }
}
