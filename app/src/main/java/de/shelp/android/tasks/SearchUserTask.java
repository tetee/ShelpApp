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
import de.shelp.android.SearchFriendActivity;
import de.shelp.android.actionlistener.AddFriendListener;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.ksoap2.ObjectResponse;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.exceptions.InvalidUsersException;

/**
 * AsyncTask, für das Suchen von Benutzern..
 * Schickt die Anfrage an den Server .
 * Der Server schickt den oder die passenden Benutzer zurück.
 * Die Ausgabe erfolgt dynamisch in {@link #onPostExecute(de.shelp.ksoap2.ObjectResponse)}
 *
 * @author
 *
 */
public class SearchUserTask extends AsyncTask<Object, Integer, ObjectResponse<User>>
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
    protected ObjectResponse<User> doInBackground(Object... params){
        ShelpApplication myApp = (ShelpApplication) activity.getApplication();
        try {
            return new ObjectResponse<User> ( myApp.getUserService().searchUsers(searchText), "");
        } catch (InvalidUsersException e) {
            return new ObjectResponse<User> ( null , "Fehler: " + e.getMessage());
        } catch (SoapFault e) {
            return new ObjectResponse<User> ( null , "Serververbindung konnte nicht erfolgreich aufgebaut werden!");
        }
    }

    protected void onPostExecute(ObjectResponse<User> result)
    {
        if(result.getList() == null ) {
            if(result.getMessage().equals("")) {
                Toast.makeText(activity.getApplicationContext(), "Keinen Benutzer gefunden!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity.getApplicationContext(), result.getMessage() , Toast.LENGTH_SHORT).show();
            }

        } else {
            for(TextView elem : activity.getSearchedElements()) {
                RelativeLayout ll = (RelativeLayout) activity.findViewById(R.id.relativeLayoutFriendSearch);
                ll.removeView(elem);
            }

            for(int i = 0; i<result.getList().size();i++){
                //result.get(i);
                RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.relativeLayoutFriendSearch);

                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                relativeParams.addRule(RelativeLayout.BELOW, idEditText);
                this.idEditText++;
                TextView et = new TextView(context);
                et.setId(idEditText);
                //setzen der Textgröße/Textfarbe
                et.setTextSize(20);
                et.setTextColor(Color.BLACK);
                et.setText(result.getList().get(i).getUserName());

                relativeLayout.addView(et, relativeParams);

                activity.addSearchedElement(et);

                RelativeLayout relativeLayout2 = (RelativeLayout) activity.findViewById(R.id.relativeLayoutFriendSearch);

                RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
                relativeParams2.addRule(RelativeLayout.BELOW, idEditText);
                //Abstände zwischen den Button werden programmatisch gesetzt
                relativeParams2.setMargins(0, 10, 0, 10);

                this.idEditText++;
                Button addButton = new Button(context);
                addButton.setText("Hinzufügen");
                addButton.setId(idEditText);
                //setzen des definierten Hintergrund in drawable
                addButton.setBackgroundResource(R.drawable.button);

                addButton.setOnClickListener(new AddFriendListener(result.getList().get(i), activity));

                relativeLayout.addView(addButton, relativeParams2);

                activity.addSearchedElement(addButton);

            }
        }
    }
}