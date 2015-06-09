package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.shelp.android.FriendsActivity;
import de.shelp.android.ShelpActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Friendship;

/**
 * Created by user on 02.06.15.
 */


public class GetFriendsTask extends AsyncTask<Object, Integer, List<Friendship>>
{
    private Context context;
    private ShelpApplication myApp;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetFriendsTask(Context context, ShelpApplication myApp) {
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected List<Friendship> doInBackground(Object... params){
        try {
            return myApp.getFriendService().getFriends(myApp.getSession().getId());
        } catch (SoapFault e) {
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(List<Friendship> result)
    {
        Intent i = new Intent(context,FriendsActivity.class);
        context.startActivity(i);
        if(result ==null) {
            Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Freunde. Füge doch einfach welche hinzu.", Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}