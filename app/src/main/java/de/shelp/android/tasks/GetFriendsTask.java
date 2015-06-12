package de.shelp.android.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
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
    private FriendsActivity friendsActivity;

    private int nextAskedId =  R.id.requestFriend;
    private int nextFriendId  =  R.id.actual;;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetFriendsTask(Context context, ShelpApplication myApp, FriendsActivity friendsActivity) {
        this.context = context;
        this.myApp = myApp;
        this.friendsActivity = friendsActivity;
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
        if(result == null) {
            Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Freunde. Füge doch einfach welche hinzu.", Toast.LENGTH_SHORT).show();
        } else {
            for( Friendship friendship: result) {
                if(friendship.getInitiatorUser().getUserName().equals(myApp.getSession().getUser().getUserName())) {
                    RelativeLayout ll = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
                    this.nextFriendId++;
                    TextView et = new TextView(context);
                    et.setId(nextFriendId);
                    et.setTextSize(20);
                    et.setText(friendship.toString());
                    ll.addView(et, relativeParams);
                } else {
                    RelativeLayout ll = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams.addRule(RelativeLayout.BELOW, nextAskedId);
                    this.nextAskedId++;
                    TextView et = new TextView(context);
                    et.setId(nextAskedId);
                    et.setTextSize(20);
                    et.setText(friendship.toString());
                    ll.addView(et, relativeParams);
                }
            }
        }
    }
}