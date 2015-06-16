package de.shelp.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.fh_muenster.shelpapp.R;
import de.shelp.android.tasks.LoginTask;

//Die MainActivity ist der Startbildschirm der Application
//Hier gibt es die Methoden zum Login und zur Registrierung
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

    //Wechsel in die RegActivity um einen neuen Benutzer zu registrieren
    public void registration(View view) {
        //neues Intent erstellen und zur RegActivity wechseln
        Intent i = new Intent(this, RegActivity.class);
        startActivity(i);
    }

    //Login eines bestehenden Benutzers
    public void login(View loginView) {
        //Eingegebene Daten des Benutzers auslesen
        EditText un = (EditText) findViewById(R.id.editUsername);
        EditText pw = (EditText) findViewById(R.id.editTextPassword);
        usern = un.getText().toString();
        userp = pw.getText().toString();
        //Passwort in Hash Wert ändern/ Aufruf der computeMD5Hash Methode
        computeMD5Hash(userp);

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


    //Verschlüsselung des Passwortes
    public void computeMD5Hash(String password) {
        try {
            // Create MD5 Hash example: mcc03e747a6afbbcbf8be7668acfebee5
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
}

