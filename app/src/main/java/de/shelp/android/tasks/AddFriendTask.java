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
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 02.06.15.
 */


public class AddFriendTask extends AsyncTask<Object, Object, Boolean>
{
    private Context context;
    private ShelpApplication myApp;
    private User user;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public AddFriendTask(Context context, ShelpApplication myApp, User user) {
        this.context = context;
        this.myApp = myApp;
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Object... params){
        try {
            return myApp.getFriendService().addFriend(myApp.getSession().getId(), user.getUserName());
        } catch (SoapFault e) {
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }

    protected void onPostExecute(Boolean result)
    {
        if(result) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Freund erfolgreich hinzugefügt!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShelpActivity.class);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Freund konnte nicht hinzugefügt werden!", Toast.LENGTH_SHORT).show();
        }
    }
}