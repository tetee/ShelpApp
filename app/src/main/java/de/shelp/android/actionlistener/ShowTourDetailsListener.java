package de.shelp.android.actionlistener;

import android.content.DialogInterface;
import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 09.06.15.
 */
public class ShowTourDetailsListener implements View.OnClickListener{

    private Tour tour;
    private SearchTourActivity activity;

    public ShowTourDetailsListener(Tour tour, SearchTourActivity activity){
        this.tour = tour;
        this.activity=activity;
    }

    @Override
    public void onClick(View v) {
        activity.details(v, tour);

    }
}
