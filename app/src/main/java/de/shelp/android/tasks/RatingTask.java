package de.shelp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.ksoap2.SoapFault;
import de.shelp.android.RatingActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.ReturnCode;
import de.shelp.ksoap2.entities.User;

/**
 * Created by Rome on 02.06.2015.
 */
public class RatingTask extends AsyncTask<Object, Integer, String>{

    private Context context;
    private User targetUser;
    private int rating;
    private String notice;
    private static RatingActivity activity;
    private int sessionId;

    public RatingTask(Context context, User user, int rating, String notice, int sessionId, RatingActivity activity) {
        this.context = context;
        this.targetUser = user;
        this.rating = rating;
        this.notice = notice;
        this.activity = activity;
        this.sessionId = sessionId;
    }

    @Override
    protected String doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRatingService().createRating(targetUser, rating, notice, sessionId);
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    protected void onProgessUpdate(Integer... values)
    { }

    protected void onPostExecute(String result)
    {
        if(result.equals(ReturnCode.ERROR.toString())) {
            Toast.makeText(activity.getApplicationContext(), "ERROR: Bewertung wurde nicht erstellt!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Bewertung wurde erfolgreich angelegt!", Toast.LENGTH_SHORT).show();
        }

    }
}


