package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.ShowRatingActivity;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.DeleteRequestTask;
import de.shelp.android.tasks.GetOwnRequestTask;
import de.shelp.ksoap2.entities.Request;

/**
 * Die Klasse stellt einen Listener fuer den Button "loeschen" bereit.
 * Mit Ausfuehren des Buttons wird eine persoenliche Anfrage geloescht.
 *
 * @author Roman Busch
 *
 */
public class AddDeleteListener implements View.OnClickListener {

    private Request request;
    private ShowOwnRequestActivity activity;

    public AddDeleteListener(Request request, ShowOwnRequestActivity activity){
        this.request = request;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        DeleteRequestTask deleteRequestTask = new DeleteRequestTask(activity.getApplicationContext(),request, activity);
        deleteRequestTask.execute();
    }
}
