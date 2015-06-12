package de.shelp.android.actionlistener;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.android.ShowOwnTourActivity;
import de.shelp.android.tasks.OwnToursTask;
import de.shelp.ksoap2.entities.Tour;

/**
 * Created by user on 09.06.15.
 */
public class ShowTourDetailsListener implements View.OnClickListener{

    private Tour tour;
    private SearchTourActivity searchActivity;
    private ShowOwnTourActivity ownTourActivity;


    public ShowTourDetailsListener(Tour tour, SearchTourActivity activity){
        this.tour = tour;
        this.searchActivity=activity;
        this.ownTourActivity=null;
    }
    public ShowTourDetailsListener(Tour tour, ShowOwnTourActivity activity){
        this.tour = tour;
        this.ownTourActivity=activity;
        this.searchActivity = null;
    }

    @Override
    public void onClick(View v) {
        if(searchActivity != null){
        searchActivity.details(v, tour);
        }
        if(ownTourActivity!=null){
            ownTourActivity.details(v, tour);
        }

    }
}
