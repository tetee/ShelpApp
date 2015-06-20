package de.shelp.android.tasks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ObjectResponse;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 02.06.15.
 */


public class GetUpdatedRequestsTask extends AsyncTask<Object, Object,  ObjectResponse<Request> >
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
    protected ObjectResponse<Request>  doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return new ObjectResponse<Request>(myApp.getRequestService().getUpdatedRequests(myApp.getSession().getId()), "");
        }catch (NoSessionException e) {
            return new ObjectResponse<Request>(null, "Fehler: " +e.getMessage());
           } catch (SoapFault e) {
            return new ObjectResponse<Request>(null, "Serververbindung konnte nicht erfolgreich aufgebaut werden!");
        }
    }

    protected void onPostExecute(ObjectResponse<Request> result)
    {
        if(result==null) {
            if(!result.getMessage().equals("")) {
                Toast.makeText(myApp.getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
       myApp.setUpdatedRequests(result.getList());
        if(!result.getList().isEmpty()) {
            Button button = (Button) activity.findViewById(R.id.request);
            button.setTextColor(Color.RED);
        }
    }
}