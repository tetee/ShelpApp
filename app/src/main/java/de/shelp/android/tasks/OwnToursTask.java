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

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.actionlistener.EditTourListener;
import de.shelp.android.actionlistener.ShowRatingsListener;
import de.shelp.android.actionlistener.ShowTourDetailsListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.exceptions.InvalidTourException;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * Created by user on 12.06.15.
 */
public class OwnToursTask extends AsyncTask<Object, Integer, List<Tour>>
{
    private Context context;
    private int sessionId;
    private static ShowOwnTourActivity activity;


    private int idEditText =R.id.relativeLayoutOwnTour;
    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public OwnToursTask(Context context,int sessionId, ShowOwnTourActivity activity){
        this.context = context;
        this.sessionId = sessionId;
        this.activity = activity;
    }

    @Override
    protected List<Tour> doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getTourService().searchOwnTour(sessionId);
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

                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutOwnTour);
                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                et.setTextSize(20);
                et.setTextColor(Color.BLACK);
                String destination = result.get(i).getLocation().toString();
                String status =result.get(i).getStatus().toString();
                et.setText("Ziel: "+ destination+ "\n" +"Status: "+ status);
                ll.addView(et, relativeParams);

                //Button Details unter ausgegebener Tour anzeigen
                RelativeLayout ll2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutOwnTour);
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

                if(!(result.get(i).getStatus().toString().equals("CANCLED"))){
                //Button Bewertung unter ausgegebener Tour anzeigen
                RelativeLayout ll3 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutOwnTour);
                RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams3.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                Button edit = new Button(context);
                edit.setBackgroundResource(R.drawable.button);
                edit.setId(idEditText);
                edit.setText("Löschen");
                edit.setOnClickListener(new EditTourListener(result.get(i), activity));
                ll3.addView(edit, relativeParams3);
            }
            }
        }
    }
}
