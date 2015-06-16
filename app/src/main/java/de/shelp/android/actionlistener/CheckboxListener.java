package de.shelp.android.actionlistener;

import android.view.View;
import android.widget.CheckBox;

import java.util.HashMap;
import java.util.Map;

import de.shelp.android.SearchFriendActivity;
import de.shelp.android.ShowTourRequestActivity;
import de.shelp.ksoap2.entities.Request;
import de.shelp.ksoap2.entities.User;
import de.shelp.ksoap2.entities.WishlistItem;

/**
 * Created by user on 09.06.15.
 */
public class CheckboxListener implements View.OnClickListener {

    private Map<WishlistItem,CheckBox> wishMap;
    private ShowTourRequestActivity activity;
    private Request request;

    public CheckboxListener(Map<WishlistItem,CheckBox> wishMap, ShowTourRequestActivity activity, Request request){
        this.wishMap = wishMap;
        this.activity = activity;
        this.request = request;
    }

    @Override
    public void onClick(View v) {
        activity.acceptRequest(wishMap, request);
    }
}
