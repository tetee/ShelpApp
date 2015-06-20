package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.RatingActivity;
import de.shelp.android.SearchFriendActivity;
import de.shelp.android.ShowOwnRequestActivity;
import de.shelp.ksoap2.entities.Rating;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.User;

/**
 * Die Klasse stellt einen Listener für den Button "Bewertung" bereit.
 * Mit ausführen des Buttons wird auf die RatingActivity gewechselet.
 *
 */
public class AddRatingListener implements View.OnClickListener {

    private Request request;
    private ShowOwnRequestActivity activity;

    public AddRatingListener(Request request, ShowOwnRequestActivity activity){
        this.request = request;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.rate(v, request.getTargedUser());
    }
}
