package de.shelp.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.GetUpdatedRequestsTask;
import de.shelp.android.tasks.GetUpdatedToursTask;
import de.shelp.android.tasks.LogoutTask;
import de.shelp.android.tasks.SetListTask;

/**
 * Activity, die eine Übersicht der Funktionen der Anwendung zeigt (nach Login oder Registrierung):
 * {@link #search(android.view.View)} eine Fahrt suchen,
 * {@link #create(android.view.View)} eine Fahrt erstellen,
 * {@link #friends(android.view.View)} Freunde enzeigen/suchen,
 * {@link #request(android.view.View)} Anfragen zeigen,
 * {@link #ownTours(android.view.View)} eigens angelegte Touren zeigen und
 * {@link #logout(android.view.View)} Logout durchführen.
 * Vor Anzeige der View werden die Tourstaten {@link de.shelp.android.tasks.GetUpdatedToursTask}
 * und Anfragen {@link de.shelp.android.tasks.GetUpdatedRequestsTask} aktualisiert.
 *
 * @author Roman Busch
 *
 */
public class ShelpActivity extends ActionBarActivity {

    private ShelpActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelp_activity);

        thisActivity = this;

        ShelpApplication application = (ShelpApplication) getApplication();

        GetUpdatedToursTask getUpdatedToursTask = new GetUpdatedToursTask( application, this);
        getUpdatedToursTask.execute();

        GetUpdatedRequestsTask getUpdatedRequestsTask = new GetUpdatedRequestsTask( application, this);
        getUpdatedRequestsTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methode um in die {@link de.shelp.android.CreateTourActivity} zu wechseln um hier eine neue Fahrt zu erstellen.
     * Über den {@link de.shelp.android.tasks.SetListTask} werden die vorbereiteten Listen für die Tourestellung geladen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void create(View view) {
        ShelpApplication app = (ShelpApplication) getApplication();
        SetListTask createTask = new SetListTask(view.getContext(),this, CreateTourActivity.class, app);
        createTask.execute();
    }

    /**
     * Methode um in die {@link de.shelp.android.SearchTourActivity} zu wechseln um hier nach Fahrten zu suchen.
     * Über den {@link de.shelp.android.tasks.SetListTask} werden die vorbereiteten Listen für die Toursuche geladen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void search(View view) {
        ShelpApplication app = (ShelpApplication) getApplication();
        SetListTask createTask = new SetListTask(view.getContext(),this, SearchTourActivity.class, app);
        createTask.execute();
    }

    /**
     * Methode um in die {@link de.shelp.android.ShowOwnRequestActivity} zu wechseln um hier eigene Anfragen an fremde Fahrten anzuzeigen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void request(View view) {
        Intent i = new Intent(this, ShowOwnRequestActivity.class);
        startActivity(i);
    }

    /**
     * Methode um in die {@link de.shelp.android.ShowOwnTourActivity} zu wechseln um eigene Fahrten, sowie zugehörige Anfragen anzuzeigen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void ownTours(View view) {
        Intent i = new Intent(this, ShowOwnTourActivity.class);
        startActivity(i);
    }

    /**
     * Methode um in die {@link de.shelp.android.FriendsActivity} zu wechseln um hier eigene Freunde anzuzeigen, neue zu suchen oder Freundschaftsanfragen annehmen.
     *
     * @param view - Die aktuell sichtbare View
     */
    public void friends(View view) {
        Intent i = new Intent(this,FriendsActivity.class);
        startActivity(i);
    }

    /**
     * Ausloggen des Benutzers über den {@link de.shelp.android.tasks.LogoutTask}
     *
     * @param ausloeser - Die aktuell sichtbare View
     */
    public void logout(View ausloeser) {
        //Logout asynchron ausfuehren:
        LogoutTask logoutTask = new LogoutTask(ausloeser.getContext(), (ShelpApplication) getApplication());
        logoutTask.execute();
    }

}