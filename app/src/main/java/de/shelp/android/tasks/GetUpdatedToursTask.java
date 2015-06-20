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
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 02.06.15.
 */


public class GetUpdatedToursTask extends AsyncTask<Object, Object,  ObjectResponse<Tour>>
{
    private ShelpApplication myApp;
    private ShelpActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetUpdatedToursTask( ShelpApplication myApp, ShelpActivity activity) {
        this.myApp = myApp;
        this.activity = activity;
    }

    @Override
    protected ObjectResponse<Tour> doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return new ObjectResponse<Tour>(myApp.getTourService().getUpdatedTours(myApp.getSession().getId()), "");
        }catch (NoSessionException e) {
            Toast.makeText(myApp.getApplicationContext(), "Fehler: " +e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut worden konnte
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(ObjectResponse<Tour> result)
    {
        if(result==null) {
            if(!result.getMessage().equals("")) {
                Toast.makeText(myApp.getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
       myApp.setUpdatedTours(result.getList());
        if(!result.getList().isEmpty()) {
            Button button = (Button) activity.findViewById(R.id.ownTours);
            button.setTextColor(Color.RED);
        }
    }
}