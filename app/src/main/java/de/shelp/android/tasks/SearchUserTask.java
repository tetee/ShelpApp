package de.shelp.android.tasks;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapFault;

import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.SearchFriendActivity;
import de.shelp.android.SearchTourActivity;
import de.shelp.android.actionlistener.AddFriendListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * Created by user on 02.06.15.
 */


public class SearchUserTask extends AsyncTask<Object, Integer, List<User>>
{
    private Context context;
    private String searchText;
    private SearchFriendActivity activity;

    private int idEditText = R.id.searchFriendButton;

    //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
    //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
    public SearchUserTask(Context context, String searchText, SearchFriendActivity activity)
    {
        this.context = context;
        this.searchText = searchText;
        this.activity = activity;
    }

    @Override
    protected List<User> doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return myApp.getUserService().searchUsers(searchText);
        } catch (InvalidUsersException e) {
            Toast.makeText(activity.getApplicationContext(), "Fehler: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SoapFault e) {
            Toast.makeText(activity.getApplicationContext(), "Serververbindung konnte nicht erfolgreich aufgebaut werden!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    protected void onPostExecute(List<User> result)
    {
        if(result.isEmpty() ||result == null ) {
            Toast.makeText(activity.getApplicationContext(), "Keinen Benutzer gefunden!", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 0; i<=result.size()-1;i++){
                //result.get(i);
                RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.relativeLayoutFriendSearch);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                et.setTextSize(20);
                et.setTextColor(Color.BLACK);
                et.setText(result.get(i).getUserName());

                relativeLayout.addView(et, relativeParams);

                RelativeLayout relativeLayout2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutFriendSearch);

                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                relativeParams2.addRule(RelativeLayout.BELOW, idEditText);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams2.setMargins(0, 10, 0, 10);

                this.idEditText++;
                Button addButton = new Button(context);
                addButton.setText("Hinzufügen");
                addButton.setId(idEditText);
                //setzen des definierten Hintergrund in drawable
                addButton.setBackgroundResource(R.drawable.button);

                addButton.setOnClickListener(new AddFriendListener(result.get(i), activity));

                relativeLayout.addView(addButton, relativeParams2);


            }
        }
    }
}