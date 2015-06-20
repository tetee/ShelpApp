package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.SearchTourActivity;
import de.shelp.ksoap2.entities.User;

/**
 * Die Klasse stellt einen Listener f�r den Button Bewertung bereit.
 * Mit ausf�hren des Buttons werden die Bewertungen zu einer Fahrt angezeigt.
 *
 */
public class ShowRatingsListener implements View.OnClickListener {

    private User user;
    private SearchTourActivity activity;

    public ShowRatingsListener(User user, SearchTourActivity activity){
        this.user = user;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.rating(v, user);

    }
}
