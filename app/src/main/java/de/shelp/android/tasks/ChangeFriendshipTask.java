package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import de.shelp.android.FriendsActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Friendship;

/**
 * Created by Jos-Laptop on 13.06.2015.
 */
public class ChangeFriendshipTask  extends AsyncTask<Object, Object, SoapObject> {

    private Friendship fs;
    private ShelpApplication myApp;
    private Context context;
    //0 - annehmen, 1 - ablehnen, rest - löschen
    private int changeType;

    public ChangeFriendshipTask(Friendship fs, ShelpApplication myApp, Context context,int changeType) {
        this.fs = fs;
        this.myApp = myApp;
        this.context = context;
        this.changeType = changeType;
    }

    @Override
    protected SoapObject doInBackground(Object... params){
        try {
            //Übergabe der Parameter an die FriendServiceImpl
            return myApp.getFriendService().changeFriendship(myApp.getSession().getId(), fs, changeType);
        } catch (SoapFault e) {
            //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
            Toast.makeText(context.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(SoapObject result)
    {
        if(result.getPrimitivePropertyAsString("returnCode").equals("OK")) {
            switch(changeType) {
                case 0:
                    Toast.makeText(context.getApplicationContext(), "Freundschaft wurde angenommen!", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(context.getApplicationContext(), "Freundschaft wurde abgelehnt!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context.getApplicationContext(), "Freundschaft wurde gelöscht!", Toast.LENGTH_SHORT).show();
                    break;
            }

            Intent i = new Intent(context, FriendsActivity.class);
            //Aufruf eines neuen Tasks
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Wechsel in die FriendsActivity
            context.startActivity(i);
        } else {
            Toast.makeText(context.getApplicationContext(), "Fehler: " + result.getPrimitivePropertyAsString("message"), Toast.LENGTH_SHORT).show();
        }
    }
}
