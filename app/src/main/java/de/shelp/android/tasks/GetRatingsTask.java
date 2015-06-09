package de.shelp.android.tasks;

import android.os.AsyncTask;

import java.util.List;

import de.shelp.ksoap2.entities.Rating;

/**
 * Created by user on 09.06.15.
 */
public class GetRatingsTask extends AsyncTask<Object,Object, List<Rating>> {



    @Override
    protected List<Rating> doInBackground(Object[] params) {
        return null;
    }


    @Override
    protected void onPostExecute(List<Rating> o) {
        super.onPostExecute(o);
    }
}
