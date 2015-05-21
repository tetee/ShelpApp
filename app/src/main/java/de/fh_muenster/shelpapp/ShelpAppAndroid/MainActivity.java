package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

import de.fh_muenster.shelpapp.R;
import de.fh_muenster.shelpapp.ShelpApp.Exceptions.InvalidLoginException;
import de.fh_muenster.shelpapp.ShelpApp.ShelpSession;
import de.fh_muenster.shelpapp.ShelpApp.User;


public class MainActivity extends ActionBarActivity {

    private String usern, userp;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //E-Mail und Passwort aus den SharedPreferences laden
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Benutzer/E-Mail als String in Variable speichern
        String username = prefs.getString("username", "");
        //Passwort als String in Variable speichern
        String password = prefs.getString("password", "");
        //Textfeld anhand der ID suchen und Benutzerdaten setzen
        EditText un = (EditText) findViewById(R.id.editUsername);
        un.setText(username);
        //Textfeld anhand der ID suchen und Passwort setzen
        EditText pw = (EditText) findViewById(R.id.editTextPassword);
        pw.setText(password);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (id == R.id.action_settings) {
            //neues Intent erstellen und zur PrefsActivity wechseln
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void registration(View view) {
        //neues Intent erstellen und zur registration_activity wechseln
        Intent i = new Intent(this, registration_activity.class);
        startActivity(i);
    }

    public void login(View loginView) {
        EditText un = (EditText) findViewById(R.id.editUsername);
        EditText pw = (EditText) findViewById(R.id.editTextPassword);
        usern = un.getText().toString();
        userp = pw.getText().toString();
        //Passwort in Hash Wert ändern/ Aufruf der computeMD5Hash Methode
        computeMD5Hash(userp);

        //Prüfung ob Benutzer und Passwort ausgefüllt sind/ Passwort wird anhand des Hash-Wertes verglichen
        if ((usern != null && userp != null)) {
                //LoginTask erstellen
                LoginTask loginTask = new LoginTask(loginView.getContext());
                //LoginTask ausführen
                loginTask.execute(usern, this.result);
            } else {
                //Toast anzeigen
                CharSequence text = "Fehlende Logindaten bitte eintragen!" ;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(loginView.getContext(), text, duration);
                toast.show();
            }
        }

    public void computeMD5Hash(String password) {
        try {
            // Create MD5 Hash cc03e747a6afbbcbf8be7668acfebee5
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
            this.result = MD5Hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private class LoginTask extends AsyncTask<String, Integer, ShelpSession> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public LoginTask(Context context) {
            this.context = context;
        }

        @Override
        protected ShelpSession doInBackground(String... params) {
            if (params.length != 2)
                return null;
            String username = params[0];
            String hash = params[1];
            ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
            try {
               return myApp.getShelpAppService().login(username, hash);
            } catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgessUpdate(Integer... values) {
        }

        protected void onPostExecute(ShelpSession result) {
            if (result != null) {
                //erfolgreich eingeloggt
                ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
                myApp.setSession(result);

                //Toast anzeigen
                CharSequence text = "Login erfolgreich! Angemeldeter Benutzername: " + result.getUser();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Nächste Activity anzeigen
                Intent i = new Intent(context, shelp_activity.class);
                startActivity(i);
            } else {
                //Toast anzeigen
                CharSequence text = "Login fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}

