package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.android.FriendsActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.User;

/**
 * AsyncTask, für das Hinzufügen eines neuen Freundes.
 * Schickt die Anfrage an den Server und erhält einen ReturnCode zurück.
 *
 * @author Theresa Sollert
 *
 */
public class AddFriendTask extends AsyncTask<Object, Object, SoapObject>
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
    protected SoapObject doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return myApp.getFriendService().addFriend(myApp.getSession().getId(), user.getUserName());
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut worden konnte
            Toast.makeText(myApp.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(SoapObject result)
    {
        if(result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Freund erfolgreich hinzugefügt!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, FriendsActivity.class);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}