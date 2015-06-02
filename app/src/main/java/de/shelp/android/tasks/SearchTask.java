package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapFault;

import de.shelp.android.SearchTourActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 02.06.15.
 */


public class SearchTask extends AsyncTask<Object, Integer, String>
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
    protected String doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().searchTour(approvalStatus, location, capacity, timeStart, timeEnd, directSearch, sessionId);
        } catch (SoapFault e) {
            //TODO errorhandling
            e.printStackTrace();
        }
        return "";
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(String result)
    {
        String r = result;
        String t = result;
    }
}