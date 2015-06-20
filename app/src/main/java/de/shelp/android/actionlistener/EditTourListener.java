package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

/**
 * Die Klasse stellt einen Listener f�r den Button "l�schen" bereit.
 * Mit ausf�hren des Buttons wird eine selbst erstellte Fahrt gel�scht.
 *
 * @author Theresa Sollert
 *
 */
public class EditTourListener implements View.OnClickListener {

    private Tour tour;
    private ShowOwnTourActivity activity;

    public EditTourListener(Tour tour, ShowOwnTourActivity activity){
        this.tour = tour;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.delete(v, tour);

    }
}
