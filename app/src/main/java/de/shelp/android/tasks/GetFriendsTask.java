package de.shelp.android.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.FriendsActivity;
import de.shelp.android.actionlistener.ChangeFriendshipListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ObjectResponse;
import de.shelp.ksoap2.entities.Friendship;

/**
 * AsyncTask, für das Anzeigen bestehender Freunde, bestehender Freundesanfragen sowie
 * die Möglichkeit neue Freunde hinzuzufügen.
 * Die Ausgabe erfolgt dynamisch in {@link #onPostExecute(de.shelp.ksoap2.ObjectResponse)}
 * Fragt die Daten vom Server an.
 *
 * @author
 *
 */
public class GetFriendsTask extends AsyncTask<Object, Integer, ObjectResponse<Friendship>>
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
    protected ObjectResponse<Friendship> doInBackground(Object... params){
        try {

            return new ObjectResponse<>(myApp.getFriendService().getFriends(myApp.getSession().getId()), "");
        } catch (SoapFault e) {
            return new ObjectResponse<>(null, "Serververbindung konnte nicht erfolgreich aufgebaut werden!");
        }
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(ObjectResponse<Friendship> result)
    {
        if(result.getList() == null) {
            if(result.getMessage().equals("")) {
                //Prüfung ob die Liste <Friendship> bereits gefüllt ist, ansonsten Ausgabe des Toasts
                Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Freunde. Füge doch einfach welche hinzu.", Toast.LENGTH_SHORT).show();
            } else {
                //Toast das die Verbindung zum Server nicht aufgebaut werden konnte
                Toast.makeText(myApp.getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            //Layout anhand der ID suchen und in Variable speichern
            RelativeLayout ll = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);
            //neues Layout erstellen und unter der nextFriendId anordnen
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams.addRule(RelativeLayout.BELOW, nextFriendId);
            this.nextFriendId++;
            TextView et = new TextView(context);
            et.setId(nextFriendId);
            //setzen der Textgröße/Textfarbe
            et.setTextSize(20);
            et.setTextColor(Color.BLACK);
            et.setText("Anfragen:");
            ll.addView(et, relativeParams);

            for( Friendship friendship: result.getList()) {
                if (friendship.getStatus().equals("ASKED") || friendship.getStatus().equals("DENIED")) {
                    if (friendship.getInitiatorUser().getUserName().equals(myApp.getSession().getUser().getUserName()) ||friendship.getStatus().equals("DENIED")) {
                        RelativeLayout ll2 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                        RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams2.addRule(RelativeLayout.BELOW, nextFriendId);
                        this.nextFriendId++;
                        TextView et2 = new TextView(context);
                        et2.setId(nextFriendId);
                        //setzen der Textgröße/Textfarbe
                        et2.setTextSize(15);
                        et2.setTextColor(Color.BLACK);
                        et2.setText(friendship.toString());
                        ll2.addView(et2, relativeParams2);

                        RelativeLayout ll3 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                        RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams3.addRule(RelativeLayout.BELOW, nextFriendId);
                        //Abstände zwischen den Button werden programmatisch gesetzt
                        relativeParams3.setMargins(0, 10, 0, 10);
                        this.nextFriendId++;
                        Button et3 = new Button(context);
                        et3.setTextColor(Color.BLACK);
                        //setzen des definierten Hintergrund in drawable
                        et3.setBackgroundResource(R.drawable.button);
                        et3.setId(nextFriendId);
                        et3.setText("Löschen");

                        et3.setOnClickListener(new ChangeFriendshipListener(friendship, 2, friendsActivity));

                        ll3.addView(et3, relativeParams3);
                    } else {
                        RelativeLayout ll5 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                        RelativeLayout.LayoutParams relativeParams5 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams5.addRule(RelativeLayout.BELOW, nextFriendId);
                        this.nextFriendId++;
                        TextView et5 = new TextView(context);
                        et5.setId(nextFriendId);
                        //setzen der Textgröße/Textfarbe
                        et5.setTextSize(15);
                        et5.setTextColor(Color.BLACK);
                        et5.setText(friendship.toString());
                        ll5.addView(et5, relativeParams5);

                        RelativeLayout ll6 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                        RelativeLayout.LayoutParams relativeParams6 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams6.addRule(RelativeLayout.BELOW, nextFriendId);
                        //Abstände zwischen den Button werden programmatisch gesetzt
                        relativeParams6.setMargins(0, 10, 0, 10);
                        this.nextFriendId++;
                        Button et6 = new Button(context);
                        et6.setTextColor(Color.BLACK);
                        //setzen des definierten Hintergrund in drawable
                        et6.setBackgroundResource(R.drawable.button);
                        et6.setId(nextFriendId);
                        et6.setText("Annehmen");

                        et6.setOnClickListener(new ChangeFriendshipListener(friendship, 0, friendsActivity));

                        ll6.addView(et6, relativeParams6);

                        RelativeLayout ll7 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                        RelativeLayout.LayoutParams relativeParams7 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams7.addRule(RelativeLayout.BELOW, nextFriendId);
                        //Abstände zwischen den Button werden programmatisch gesetzt
                        relativeParams7.setMargins(0, 10, 0, 10);
                        this.nextFriendId++;
                        Button et7 = new Button(context);
                        et7.setTextColor(Color.BLACK);
                        //setzen des definierten Hintergrund in drawable
                        et7.setBackgroundResource(R.drawable.button);
                        et7.setId(nextFriendId);
                        et7.setText("Ablehnen");

                        et7.setOnClickListener(new ChangeFriendshipListener(friendship, 1, friendsActivity));

                        ll7.addView(et7, relativeParams7);
                    }
                }
            }

            RelativeLayout ll4 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

            RelativeLayout.LayoutParams relativeParams4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams4.addRule(RelativeLayout.BELOW, nextFriendId);
            this.nextFriendId++;
            TextView et4 = new TextView(context);
            et4.setId(nextFriendId);
            et4.setTextSize(20);
            et4.setTextColor(Color.BLACK);
            et4.setText("Freunde:");
            ll4.addView(et4, relativeParams4);

            for( Friendship friendship: result.getList()) {
                if (friendship.getStatus().equals("ACCEPT")) {
                    RelativeLayout ll5 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams5 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams5.addRule(RelativeLayout.BELOW, nextFriendId);
                    this.nextFriendId++;
                    TextView et5 = new TextView(context);
                    et5.setId(nextFriendId);
                    et5.setTextSize(15);
                    et5.setTextColor(Color.BLACK);
                    et5.setText(friendship.toString());
                    ll5.addView(et5, relativeParams5);

                    RelativeLayout ll6 = (RelativeLayout) friendsActivity.findViewById(R.id.relativeLayoutFriend);

                    RelativeLayout.LayoutParams relativeParams6 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams6.addRule(RelativeLayout.BELOW, nextFriendId);
                    //Abstände zwischen den Button werden programmatisch gesetzt
                    relativeParams6.setMargins(0, 10, 0, 10);
                    this.nextFriendId++;
                    Button et6 = new Button(context);
                    et6.setTextColor(Color.BLACK);
                    //setzen des definierten Hintergrund in drawable
                    et6.setBackgroundResource(R.drawable.button);
                    et6.setId(nextFriendId);
                    et6.setText("Löschen");
                    et6.setOnClickListener(new ChangeFriendshipListener(friendship, 2, friendsActivity));
                    ll6.addView(et6, relativeParams6);
                }
            }
        }
    }
}