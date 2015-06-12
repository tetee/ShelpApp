package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.ksoap2.entities.Tour;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 12.06.15.
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
        activity.edit(v, tour);

    }
}
