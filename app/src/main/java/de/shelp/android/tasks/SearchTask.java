package de.shelp.android.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.SearchTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.actionlistener.ShowRatingsListener;
import de.shelp.android.actionlistener.ShowTourDetailsListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * Created by user on 02.06.15.
 */


public class SearchTask extends AsyncTask<Object, Integer, List<Tour>>
{
    private Context context;
    private int approvalStatus;
    private long location;
    private int capacity;
    private long timeStart;
    private long timeEnd;
    boolean directSearch;
    private int sessionId;
    private static SearchTourActivity activity;


    private int idEditText = R.id.searchButton;
    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public SearchTask(Context context,int approvalStatus, long location, int capacity, long timeStart, long timeEnd,boolean directSearch, int sessionId, SearchTourActivity activity)
    {
        this.approvalStatus = approvalStatus;
        this.location =location;
        this.capacity=capacity;
        this.timeStart=timeStart;
        this.timeEnd=timeEnd;
        this.directSearch=directSearch;
        this.context = context;
        this.sessionId = sessionId;
        this.activity = activity;
    }

    @Override
    protected List<Tour> doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().searchTour(approvalStatus, location, capacity, timeStart, timeEnd, directSearch, sessionId);
        } catch (InvalidTourException e) {
            Toast.makeText(activity.getApplicationContext(), "Fehler: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }



    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(List<Tour> result)
    {
        if(result ==null) {
            Toast.makeText(activity.getApplicationContext(), "ERROR: Fahrt konnte nicht gefunden werden!", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 0; i<=result.size()-1;i++){
                //Layout anhand der ID suchen und in Variable speichern
                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutSearch);
                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                //setzen der Textgröße
                et.setTextSize(20);
                String owner = result.get(i).getOwner().toString();
                String destination = result.get(i).getLocation().toString();
                et.setText(owner + " " + destination);
                ll.addView(et, relativeParams);

                 //Button Details unter ausgegebener Tour anzeigen
                RelativeLayout ll2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutSearch);
                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, idEditText);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams2.setMargins(0, 10, 0, 20);
                this.idEditText++;
                Button details = new Button(context);
                details.setLayoutParams(relativeParams2);
                //setzen des definierten Hintergrund in drawable
                details.setBackgroundResource(R.drawable.button);
                details.setId(idEditText);
                details.setText("Details");
                details.setOnClickListener(new ShowTourDetailsListener(result.get(i), activity));
                ll2.addView(details);

                //Button Bewertung unter ausgegebener Tour anzeigen
                RelativeLayout ll3 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutSearch);
                RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                Button rating = new Button(context);
                rating.setBackgroundResource(R.drawable.button);
                rating.setId(idEditText);
                rating.setText("Bewertung");
                rating.setOnClickListener(new ShowRatingsListener(result.get(i).getOwner(), activity));
                ll3.addView(rating, relativeParams3);

            }
        }
    }

}