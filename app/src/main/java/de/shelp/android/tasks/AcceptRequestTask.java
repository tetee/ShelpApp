package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.ShowTourRequestActivity;
import de.shelp.android.WishlistActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.WishlistItem;

/**
 * AsyncTask, für das (teilweise) Annehmen von Anfragen.
 * Schickt die angenommen Anfrage anschließend an den Server.
 *
 * @author
 *
 */
public class AcceptRequestTask extends AsyncTask<Object, Integer, SoapObject> {
    private Context context;
    private Request request;
    private Map<WishlistItem, CheckBox> wishMap;
    private static ShowTourRequestActivity activity;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public AcceptRequestTask(Context context, Request request, Map<WishlistItem,CheckBox> wishMap, ShowTourRequestActivity activity) {
        this.request = request;
        this.wishMap = wishMap;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected SoapObject doInBackground(Object... params) {
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            String idChecked = "";
            for(WishlistItem key : wishMap.keySet()) {
                if(wishMap.get(key).isChecked()){
                    idChecked += key.getId() + "\n";
                }
            }
            return myApp.getRequestService().acceptRequest(request.getId(), myApp.getSession().getId(), idChecked);
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }


    protected void onProgessUpdate(Integer... values) {
    }

    protected void onPostExecute(SoapObject result) {
        if (result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            Toast.makeText(context.getApplicationContext(), "Anfrage erfolgreich versendet!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, ShowOwnTourActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }


    }
}
