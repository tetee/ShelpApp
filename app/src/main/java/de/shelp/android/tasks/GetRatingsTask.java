package de.shelp.android.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShowRatingActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ObjectResponse;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidRatingException;

/**
 * AsyncTask, für das Anzeigen von Bewertungen eines Benutzers.
 * Die Ausgabe erfolgt dynamisch in {@link #onPostExecute(de.shelp.ksoap2.ObjectResponse)}
 * Fragt die Daten vom Server an.
 *
 * @author Roman Busch
 *
 */
public class GetRatingsTask extends AsyncTask<Object,Object, ObjectResponse<Rating>> {

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
    protected ObjectResponse<Rating> doInBackground(Object[] params) {
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return new ObjectResponse<Rating> (myApp.getRatingService().getRatings(user, context),"");
        } catch (InvalidRatingException e) {
            return new ObjectResponse<Rating>(null,"Fehler: " + e.getMessage() );
        } catch (SoapFault e) {
            return new ObjectResponse<Rating>(null, "Serververbindung konnte nicht erfolgreich aufgebaut werden!" );

        }
    }


    @Override
    protected void onPostExecute(ObjectResponse<Rating>  ratings) {
        if (ratings.getList() == null) {
            if(ratings.getMessage().equals("")) {
                Toast.makeText(activity.getApplicationContext(), "Es gibt keine Bewertungen!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity.getApplicationContext(), ratings.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            float sum = 0;
            for(Rating r : ratings.getList()) {
                 sum += r.getRating();
            }

            sum = ((int) (sum / ratings.getList().size() * 10) ) / 10f;

            RelativeLayout ll0 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

            RelativeLayout.LayoutParams relativeParams0 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            relativeParams0.addRule(RelativeLayout.BELOW, idEditText);
            this.idEditText++;
            TextView et0 = new TextView(context);
            et0.setId(idEditText);
            //setzen der Textgröße/Textfarbe
            et0.setTextColor(Color.BLACK);
            et0.setTextSize(20);
            et0.setText("Gesamtbewertung: " + sum);
            ll0.addView(et0, relativeParams0);

            for (int i = 0; i <= ratings.getList().size() - 1; i++) {

                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutRatings);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                String owner = ratings.getList().get(i).getSourceUser().getUserName();
                //setzen der Textgröße/Textfarbe
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
                float ratingValue = ratings.getList().get(i).getRating();
                //setzen der Textgröße/Textfarbe
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
                String notice = ratings.getList().get(i).getNotice();
                //setzen der Textgröße/Textfarbe
                et3.setTextColor(Color.BLACK);
                et3.setTextSize(20);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams3.setMargins(0, 0, 0, 45);
                et3.setText("Beschreibung: " + notice);
                et3.setLayoutParams(relativeParams3);
                ll3.addView(et3);
            }
        }
    }
}
