package de.shelp.android.tasks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 02.06.15.
 */


public class GetUpdatedRequestsTask extends AsyncTask<Object, Object,  List<Request>>
{
    private ShelpApplication myApp;
    private ShelpActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetUpdatedRequestsTask(ShelpApplication myApp, ShelpActivity activity) {
        this.myApp = myApp;
        this.activity = activity;
    }

    @Override
    protected List<Request> doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return myApp.getRequestService().getUpdatedRequests(myApp.getSession().getId());
        }catch (NoSessionException e) {
            Toast.makeText(myApp.getApplicationContext(), "Fehler: " +e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut worden konnte
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(List<Request> result)
    {
       myApp.setUpdatedRequests(result);
        if(!result.isEmpty()) {
            Button button = (Button) activity.findViewById(R.id.request);
            button.setTextColor(Color.MAGENTA);
        }
    }
}