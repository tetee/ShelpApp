package de.shelp.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.tasks.LoginTask;

/**
 * MainActivity ist der Startbildschirm der Anwendung
 * Hier besteht die Möglichkeit für einen Login {@link #login(android.view.View)}
 * und für die Registrierung {@link #registration(android.view.View)}
 * das Passwort für den Login wird verschlüsselt {@link #computeMD5Hash(String)}
 *
 * @author
 *
 */
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

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo == null || !netInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "Einen Internetverbindung wird zur Nutzung der App benötigt!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    /**
     * Methode um zur RegActivity zu wechseln, um einen neuen Benutzer zu registrieren
     *
     * @param view - Die aktuelle sichtbare View
     */
    public void registration(View view) {
        //neues Intent erstellen und zur RegActivity wechseln
        Intent i = new Intent(this, RegActivity.class);
        startActivity(i);
    }

    /**
     *Methode um im AsyncTask {@link de.shelp.android.tasks.LoginTask} einen Benutzer einzuloggen
     *
     * @param loginView- die aktuell sichtbare View
     */
    public void login(View loginView) {
        //Eingegebene Daten des Benutzers auslesen
        EditText un = (EditText) findViewById(R.id.editUsername);
        EditText pw = (EditText) findViewById(R.id.editTextPassword);
        usern = un.getText().toString();
        userp = pw.getText().toString();
        //Passwort in Hash Wert ändern/ Aufruf der computeMD5Hash Methode
        result = computeMD5Hash(userp);

        if ((usern != null && userp != null)) {
                //LoginTask erstellen
                LoginTask loginTask = new LoginTask(loginView.getContext(), this);
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

    /**
     * Methode um das Passwort zu Verschlüsseln
     *
     * @param password - Passwort, das gehasht wird
     * @return - Das gehashte Passwort
     *
     */
    public String computeMD5Hash(String password) {
        try {
            // Erstelle MD5 Hash Beispiel: mcc03e747a6afbbcbf8be7668acfebee5
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
            return MD5Hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}

