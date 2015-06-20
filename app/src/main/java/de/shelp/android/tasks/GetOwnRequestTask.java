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
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.actionlistener.AddDeleteListener;
import de.shelp.android.actionlistener.AddRatingListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ObjectResponse;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.TourStatus;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Created by user on 02.06.15.
 */


public class GetOwnRequestTask extends AsyncTask<Object, Integer,  ObjectResponse<Request>> {
    private Context context;
    private ShelpApplication myApp;
    private ShowOwnRequestActivity activity;

    private int nextAskedId = R.id.ownRequest;


    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetOwnRequestTask(Context context, ShelpApplication myApp, ShowOwnRequestActivity activity) {
        this.context = context;
        this.myApp = myApp;
        this.activity = activity;
    }

    @Override
    protected ObjectResponse<Request> doInBackground(Object... params) {
        try {
            return new ObjectResponse<Request>( myApp.getOwnRequestService().getRequest(myApp.getSession().getId()), "");
        } catch (InvalidRequestException e) {
            return new ObjectResponse<Request>(null, "Fehler: " + e.getMessage());
        } catch (SoapFault e) {
            return new ObjectResponse<Request>(null, "Serververbindung konnte nicht erfolgreich aufgebaut werden!");
        }
    }

    protected void onPostExecute(ObjectResponse<Request> result) {
        if (result.getList() == null) {
            if(result.getMessage().equals("")) {
                //Prüfung ob die Liste <Friendship> bereits gefüllt ist, ansonsten Ausgabe des Toasts
                Toast.makeText(myApp.getApplicationContext(), "Du hast noch keine Anfragen gestellt.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity.getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }
           } else {
            for (Request request : result.getList()) {
                //Layout anhand der ID suchen und in Variable speichern
                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);
                //neues Layout erstellen und unter der nextAskedId anordnen
                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, nextAskedId);
                this.nextAskedId++;
                TextView et = new TextView(context);
                et.setId(nextAskedId);
                //setzen der Textgröße/Textfarbe
                et.setTextSize(20);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams.setMargins(0, 0, 0, 20);
                et.setTextColor(Color.BLACK);
                et.setText("Fahrt: " + request.getTargedUser() + " " + request.getTour().getLocation() + " " + request.getStatus());
                ll.addView(et, relativeParams);


                for(int i=0; i < request.getWishes().size(); i++){
                    if(request.getWishes().get(i).isChecked()) {
                        //Layout anhand der ID suchen und in Variable speichern
                        RelativeLayout ll1 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);
                        //neues Layout erstellen und unter der nextAskedId anordnen
                        RelativeLayout.LayoutParams relativeParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams1.addRule(RelativeLayout.BELOW, nextAskedId);
                        this.nextAskedId++;
                        TextView et1 = new TextView(context);
                        et1.setId(nextAskedId);
                        et1.setTextSize(20);
                        relativeParams1.setMargins(0, 0, 0, 20);
                        et1.setTextColor(Color.parseColor("#10A24A"));
                        et1.setText("Wunsch: " + request.getWishes().get(i).getText());
                        ll1.addView(et1, relativeParams1);
                    } else {
                        //Layout anhand der ID suchen und in Variable speichern
                        RelativeLayout ll11 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);
                        //neues Layout erstellen und unter der nextAskedId anordnen
                        RelativeLayout.LayoutParams relativeParams11 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                        relativeParams11.addRule(RelativeLayout.BELOW, nextAskedId);
                        this.nextAskedId++;
                        TextView et11 = new TextView(context);
                        et11.setId(nextAskedId);
                        et11.setTextSize(20);
                        relativeParams11.setMargins(0, 0, 0, 20);
                        et11.setTextColor(Color.RED);
                        et11.setText("Wunsch: " + request.getWishes().get(i).getText());
                        ll11.addView(et11, relativeParams11);

                    }
                }

                RelativeLayout ll2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, nextAskedId);
                this.nextAskedId++;
                Button bt2 = new Button(context);
                bt2.setId(nextAskedId);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams2.setMargins(0, 0, 0, 20);
                //setzen der Textfarbe
                bt2.setTextColor(Color.BLACK);
                bt2.setText("löschen");
                //setzen des definierten Hintergrund in drawable
                bt2.setBackgroundResource(R.drawable.button);
                ll2.addView(bt2, relativeParams2);

                bt2.setOnClickListener(new AddDeleteListener(request, activity));

                if( request.getTour().getStatus().equals(TourStatus.CLOSED) && ( request.getStatus().equals("ACCEPT") || request.getStatus().equals("PARTLY_ACCEPT")) ) {
                   RelativeLayout ll3 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRequest);

                    RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                    relativeParams3.addRule(RelativeLayout.BELOW, nextAskedId);
                    this.nextAskedId++;
                    Button bt3 = new Button(context);
                    bt3.setId(nextAskedId);
                    //Abstände zwischen den Button werden programmatisch gesetzt
                    relativeParams3.setMargins(0, 0, 0, 20);
                    //setzen der Textfarbe
                       bt3.setTextColor(Color.BLACK);
                    bt3.setText("Bewertung");
                    //setzen des definierten Hintergrund in drawable
                    bt3.setBackgroundResource(R.drawable.button);

                    ll3.addView(bt3, relativeParams3);
                    bt3.setOnClickListener(new AddRatingListener(request, activity));
                   }
            }
        }
    }
}