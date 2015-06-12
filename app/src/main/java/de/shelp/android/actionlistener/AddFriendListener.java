package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.SearchFriendActivity;
import de.shelp.android.SearchTourActivity;
import de.shelp.ksoap2.entities.User;

/**
 * Created by user on 09.06.15.
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
