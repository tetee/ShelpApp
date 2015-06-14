package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.android.ShowTourActivity;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 14.06.15.
 */
public class ShowRequestListener implements View.OnClickListener {

    private Tour tour;
    private ShowTourActivity activity;

    public ShowRequestListener(Tour tour, ShowTourActivity activity){
        this.tour = tour;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.showRequest(v, tour);
    }
}