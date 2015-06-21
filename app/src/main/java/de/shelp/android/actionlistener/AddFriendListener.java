package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.SearchFriendActivity;
import de.shelp.android.SearchTourActivity;
import de.shelp.ksoap2.entities.User;

/**
 * Die Klasse stellt einen Listener fuer den Button "hinzufuegen" bereit.
 * Mit Ausfuehren des Buttons wird ein Freund hinzugefuegt.
 *
 * @author Theresa Sollert
 *
 */
public class AddFriendListener implements View.OnClickListener {

    private User user;
    private SearchFriendActivity activity;

    public AddFriendListener(User user, SearchFriendActivity activity){
        this.user = user;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.add(v, user);
    }
}
