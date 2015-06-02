package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.SearchTourActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.Tour;

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


    private int lastEditText = R.id.ratingButton;
    private int idEditText = 1;
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
                //result.get(i);
                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutSearch);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, lastEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                String owner = result.get(i).getOwner().toString();
                et.setText(owner);
                this.lastEditText = et.getId();
                ll.addView(et, relativeParams);
            }
        }
    }
}