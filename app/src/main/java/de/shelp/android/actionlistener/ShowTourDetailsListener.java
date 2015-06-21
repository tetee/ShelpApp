package de.shelp.android.actionlistener;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.tasks.OwnToursTask;
import de.shelp.ksoap2.entities.Tour;

/**
 * Die Klasse stellt einen Listener fuer den Button "Details" bereit.
 * Mit Ausfuehren des Buttons koennen Details zu einer Fahrt angezeigt werden.
 *
 * @author Roman Busch
 *
 */
public class ShowTourDetailsListener implements View.OnClickListener{

    private Tour tour;
    private Activity activity;


    public ShowTourDetailsListener(Tour tour, Activity activity){
        this.tour = tour;
        this.activity=activity;
    }

    @Override
    public void onClick(View v) {
        if(activity instanceof SearchTourActivity){
            SearchTourActivity search = (SearchTourActivity) activity;
            search.details(v, tour);
        }
        else if(activity instanceof ShowOwnTourActivity){
            ShowOwnTourActivity show = (ShowOwnTourActivity) activity;
            show.details(v, tour);
        }

    }
}
