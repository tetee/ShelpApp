package de.fh_muenster.shelpapp.ShelpAppAndroid;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import de.fh_muenster.shelpapp.R;



public class whishlist_activity extends ActionBarActivity {

    private int lastEditText = R.id.editTextWishList;
    private int idEditText = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist_activity);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_whishlist_activity, menu);
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
        ll.addView(et, relativeParams);
    }

    public void requestWishList(View view) {
        //Toast anzeigen
        //Context context = getApplicationContext();
        //CharSequence text = "Anfrage erfolgreich gesendet!";
        //int duration = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();
        Toast.makeText(getApplicationContext(), "Anfrage erfolgreich gesendet!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, shelp_activity.class);
        startActivity(i);
    }
}


