package de.shelp.android.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShowRatingActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidRatingException;
import de.shelp.ksoap2.exceptions.InvalidRequestException;

/**
 * Created by user on 09.06.15.
 */
public class GetRatingsTask extends AsyncTask<Object,Object, List<Rating>> {

    private Context context;
    private ShowRatingActivity activity;
    private User user;

    int idEditText = R.id.user;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public GetRatingsTask(Context context, ShowRatingActivity activity, User user) {
        this.context = context;
        this.activity = activity;
        this.user = user;
    }

    @Override
    protected List<Rating> doInBackground(Object[] params) {
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getRatingService().getRatings(user, context);
        } catch (InvalidRatingException e) {
            Toast.makeText(activity.getApplicationContext(), "Fehler: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();

        }
        return null;
    }


    @Override
    protected void onPostExecute(List<Rating> ratings) {
        if (ratings == null) {
            Toast.makeText(activity.getApplicationContext(), "ERROR: Bewertung konnte nicht gefunden werden!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i <= ratings.size() - 1; i++) {

                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                String owner = ratings.get(i).getSourceUser().getUserName();
                et.setTextColor(Color.BLACK);
                et.setTextSize(20);
                et.setText("Ersteller: " + owner);
                ll.addView(et, relativeParams);


                RelativeLayout ll2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et2 = new TextView(context);
                et2.setId(idEditText);
                int ratingValue = ratings.get(i).getRating();
                et2.setTextColor(Color.BLACK);
                et2.setTextSize(20);
                et2.setText("Sterne: " + ratingValue);
                ll2.addView(et2, relativeParams2);

                RelativeLayout ll3 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

                RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et3 = new TextView(context);
                et3.setId(idEditText);
                String notice = ratings.get(i).getNotice();
                et3.setTextColor(Color.BLACK);
                et3.setTextSize(20);
                relativeParams3.setMargins(0, 0, 0, 45);
                et3.setText("Beschreibung: " + notice);
                et3.setLayoutParams(relativeParams3);
                ll3.addView(et3);
            /*
                RelativeLayout ll4 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

                RelativeLayout.LayoutParams relativeParams4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et4 = new TextView(context);
                et4.setId(idEditText);
                String notice = ratings.get(i).getNotice();
                et4.setTextColor(Color.BLACK);
                et4.setTextSize(10);
                et4.setText(notice);
                relativeParams4.setMargins(0,0,0,20);
                ll4.addView(et4, relativeParams4);
            }
            */


            }
        }
    }
}
