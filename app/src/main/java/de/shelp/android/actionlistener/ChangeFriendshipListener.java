package de.shelp.android.actionlistener;

import android.view.View;

import de.shelp.android.FriendsActivity;
import de.shelp.ksoap2.entities.Friendship;

/**
 * Die Klasse stellt einen Listener für die Buttons "annehmen", "ablehnen" und "löschen" bereit.
 * Mit ausführen des Buttons "annehmen" wird eine Freundschaftsanfrage angenommen.
 * Mit ausführen des Buttons "ablehnen" wird eine Freundschaftsanfrage abgelehnt.
 * Mit ausführen des Buttons "löschen" wird eine Freundschaftsanfrage gelöscht.
 *
 * @author Theresa Sollert
 *
 */
public class ChangeFriendshipListener implements View.OnClickListener {

    private Friendship fs;
    private FriendsActivity activity;
    //0 - annehmen, 1 - ablehnen, rest - löschen
    private int changeType;

    public ChangeFriendshipListener(Friendship fs, int changeType, FriendsActivity activity) {
        this.fs = fs;
        this.changeType = changeType;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.changeFriendship(fs, changeType);
    }
}
