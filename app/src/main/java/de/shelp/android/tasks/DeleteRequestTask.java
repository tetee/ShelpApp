package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.FriendsActivity;
import de.shelp.android.SearchFriendActivity;
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.actionlistener.AddFriendListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * Created by user on 02.06.15.
 */


public class DeleteRequestTask extends AsyncTask<Object, Integer, SoapObject>
{
    private Context context;
    private Request request;
    private ShowOwnRequestActivity activity;

    private int idEditText = R.id.searchFriendButton;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public DeleteRequestTask(Context context, Request request, ShowOwnRequestActivity activity)
    {
        this.context = context;
        this.request = request;
        this.activity = activity;
    }

    @Override
    protected SoapObject doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRequestService().deleteRequest(myApp.getSession().getId(), request.getId());
               } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(SoapObject result)
    {
        if(result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            //Toast ob das hinzufügen eines neuen Freundes erfolgreich war
            Toast.makeText(context.getApplicationContext(), "Anfrage erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowOwnRequestActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}