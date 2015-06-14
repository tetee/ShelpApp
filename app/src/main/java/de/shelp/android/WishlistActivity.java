package de.shelp.android;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.SearchTask;
import de.shelp.android.tasks.WishlistTask;
import de.shelp.ksoap2.entities.Tour;


public class WishlistActivity extends ActionBarActivity {

    private int lastEditText = R.id.editTextWishList;
    private int idEditText = 1;
    private String targedUserId;
    private Long tourId;
    private List<EditText> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist_activity);

        EditText wish = (EditText) findViewById(R.id.editTextWishList);
        list.add(wish);
        Intent intent = getIntent();
        Tour tour = (Tour) intent.getSerializableExtra("Tour");
        targedUserId = tour.getOwner().toString();
        tourId=tour.getId();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.logo) {
            Intent i = new Intent(this, ShelpActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTextView(View view) {
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.relativeLayout);

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        relativeParams.addRule(RelativeLayout.BELOW, lastEditText);
        this.idEditText++;
        EditText et = new EditText(this);
        et.setId(idEditText);
        this.lastEditText = et.getId();
        et.setHint("+ Wunsch");
        list.add(et);
        ll.addView(et, relativeParams);
    }

    public void requestWishList(View view) {

        List<String> wishes = new ArrayList<String>();

        for(EditText et : list){
            wishes.add(et.getText().toString());
        }
        EditText noticeText = (EditText) findViewById(R.id.wishEditText);
        String notice = noticeText.getText().toString();

        ShelpApplication application = (ShelpApplication) getApplication();

        WishlistTask wishlistTask = new WishlistTask(view.getContext(), tourId, wishes, notice, application.getSession().getId(), this);
        wishlistTask.execute();

        Intent i = new Intent(this, ShelpActivity.class);
        startActivity(i);
    }
}


