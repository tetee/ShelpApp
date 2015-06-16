package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.FriendsActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.NoSessionException;

/**
 * Created by user on 02.06.15.
 */


public class GetUpdatedToursTask extends AsyncTask<Object, Object,  List<Tour>>
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
    protected List<Tour> doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return myApp.getTourService().getUpdatedTours(myApp.getSession().getId());
        }catch (NoSessionException e) {
            Toast.makeText(myApp.getApplicationContext(), "Fehler: " +e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut worden konnte
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(List<Tour> result)
    {
       myApp.setUpdatedTours(result);
        if(!result.isEmpty()) {
            Button button = (Button) activity.findViewById(R.id.ownTours);
            button.setTextColor(Color.RED);
        }
    }
}