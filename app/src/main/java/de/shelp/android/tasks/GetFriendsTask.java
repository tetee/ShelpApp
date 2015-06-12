package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.FriendsActivity;
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

    private int nextFriendId  =  R.id.searchFriend;

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
            RelativeLayout ll = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
            this.nextFriendId++;
            TextView et = new TextView(context);
            et.setId(nextFriendId);
            et.setTextSize(20);
            et.setText("Anfragen");
            ll.addView(et, relativeParams);

            for( Friendship friendship: result) {
                if (friendship.getInitiatorUser().getUserName().equals(myApp.getSession().getUser().getUserName())) {
                    RelativeLayout ll2 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
                    this.nextFriendId++;
                    TextView et2 = new TextView(context);
                    et2.setId(nextFriendId);
                    et2.setTextSize(20);
                    et2.setText(friendship.toString());
                    ll2.addView(et2, relativeParams2);

                    RelativeLayout ll4 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
                    this.nextFriendId++;
                    Button et4 = new Button(context);
                    et4.setId(nextFriendId);
                    et4.setTextSize(20);
                    et4.setText("Löschen");
                    ll4.addView(et4, relativeParams4);
                }
            }

//            RelativeLayout ll4 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);
//
//            RelativeLayout.LayoutParams relativeParams4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//            relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
//            this.nextFriendId++;
//            TextView et4 = new TextView(context);
//            et4.setId(nextFriendId);
//            et4.setTextSize(20);
//            et4.setText("Freunde " + "");
//            ll4.addView(et4, relativeParams4);
//
//            for( Friendship friendship: result) {
//                if (friendship.getRecipientUser().getUserName().equals(myApp.getSession().getUser().getUserName())){
//                    RelativeLayout ll3 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);
//
//                    RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//                    relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
//                    this.nextFriendId++;
//                    TextView et3 = new TextView(context);
//                    et3.setId(nextFriendId);
//                    et3.setTextSize(20);
//                    et3.setText(friendship.toString() + "");
//                    ll3.addView(et3, relativeParams3);
//                }
//            }
        }
    }
}