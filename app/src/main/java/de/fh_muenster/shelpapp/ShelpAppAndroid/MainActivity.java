package de.fh_muenster.shelpapp.ShelpAppAndroid;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    /*public void login(View loginView) {

        //E-Mail und Passwort aus den SharedPreferences laden
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(loginView.getContext());
        //Benutzer/E-Mail als String in Variable speichern
        String username = prefs.getString("username", "");
        //Passwort als String in Variable speichern
        String password = prefs.getString("password", "");

        */

    /**
     * Prüfung ob E-Mail und Passwort mit den Daten aus dem Mockobjekt
     * mit den SharedPreferences übereinstimmen. Wenn die Daten passen, wird
     * der LoginTask ausgeführt. Falls die Daten nicht übereinstimmen, wird eine
     * Fehlermeldung ausgegeben und als Popup angezeigt.
     *//*
        if(!"".equals(username) && !"".equals(password)) {
            //LoginTask erstellen
            LoginTask loginTask = new LoginTask(loginView.getContext());
            //LoginTask ausführen
            loginTask.execute(username, password);
        }
        else
        {
            //Toast anzeigen
            CharSequence text = "Fehlende Logindaten bitte in den Einstellungen eintragen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(loginView.getContext(), text, duration);
            toast.show();
        }
    }*/
    public void login(View loginView) {
        EditText un = (EditText) findViewById(R.id.editUsername);
        EditText pw = (EditText) findViewById(R.id.editTextPassword);
        usern = un.getText().toString();
        userp = pw.getText().toString();
        computeMD5Hash(userp);

        if ((usern != null && usern.equals("")) || (userp != null && userp.equals("")) {
            if (result.equals("d41d8cd98f00b204e9800998ecf8427e")) {
                //LoginTask erstellen
                LoginTask loginTask = new LoginTask(loginView.getContext());
                //LoginTask ausführen
                loginTask.execute(usern, userp);
            } else {
                //Toast anzeigen
                CharSequence text = "Fehlende Logindaten bitte in den Einstellungen eintragen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(loginView.getContext(), text, duration);
                toast.show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter your credentials..", Toast.LENGTH_LONG).show();
        }
    }


    public void computeMD5Hash(String password) {
        try {
            // Create MD5 Hash d41d8cd98f00b204e9800998ecf8427e
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
            result = MD5Hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


    private class LoginTask extends AsyncTask<String, Integer, User> {
        private Context context;

        //Dem Konstruktor der Klasse wird der aktuelle Kontext der Activity übergeben
        //damit auf die UI-Elemente zugegriffen werden kann und Intents gestartet werden können, usw.
        public LoginTask(Context context) {
            this.context = context;
        }

        @Override
        protected User doInBackground(String... params) {
            if (params.length != 2)
                return null;
            String username = params[0];
            String password = params[1];
            ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
            try {
                User myUser = myApp.getShelpAppService().login(username, password);
                return myUser;
            } catch (InvalidLoginException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgessUpdate(Integer... values) {
        }

        protected void onPostExecute(User result) {
            if (result != null) {
                //erfolgreich eingeloggt
                ShelpAppApplication myApp = (ShelpAppApplication) getApplication();
                myApp.setUser(result);

                //Toast anzeigen
                CharSequence text = "Login erfolgreich! Angemeldeter Benutzername: " + result.getUserName();
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
