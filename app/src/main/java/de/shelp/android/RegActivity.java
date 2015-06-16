package de.shelp.android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import de.shelp.android.applications.ShelpApplication;
import de.shelp.android.tasks.RegTask;
import de.shelp.ksoap2.exceptions.InvalidRegistrationException;
import de.shelp.ksoap2.entities.ShelpSession;

//Activity für das Registrieren einen neuen Benutzers
public class RegActivity extends ActionBarActivity {

    private String eMail, password, passwordConfirm, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);
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

    //Registration eines neuen Benutzers
    public void registration(View regView) {
        EditText mail = (EditText) findViewById(R.id.editMailReg);
        EditText pw = (EditText) findViewById(R.id.editTextPasswordReg);
        EditText pwConfirm = (EditText) findViewById(R.id.editPasswordRegConfirm);
        eMail = mail.getText().toString();
        password = pw.getText().toString();
        passwordConfirm = pwConfirm.getText().toString();
        //Verschlüsselung des Passwortes
        computeMD5Hash(password);

        //Format des Passwortes überprüfen
        if(password.length() < 6 && passwordConfirm.length() < 6){
            Toast.makeText(getApplicationContext(), "Passwort muss mind. 6 Zeichen enthalten!", Toast.LENGTH_SHORT).show();
        } else if (password.equals(passwordConfirm)) {
            if ((eMail != null && password != null && passwordConfirm != null)) {
                //Registrierung im AsyncTask
                RegTask registrationTask = new RegTask(regView.getContext(), this);
                registrationTask.execute(eMail, this.result);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Passwörter stimmen nicht überein!", Toast.LENGTH_SHORT).show();
        }
    }



    //Methode zur Verschlüsselung des Passwortes
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
}
