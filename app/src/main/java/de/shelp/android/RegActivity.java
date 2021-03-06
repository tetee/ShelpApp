package de.shelp.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.shelp.R;
import de.shelp.android.tasks.RegTask;

/**
 * Activity, für das Registrieren eines neuen Benutzers {@link #registration(android.view.View)}
 * Das Passwort für die Registrierung wird verschlüsselt {@link #computeMD5Hash(String)}
 *
 * @author Roman Busch
 *
 */
public class RegActivity extends ActionBarActivity {

    private String eMail, password, passwordConfirm, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methode für die Registrierung eines neuen Benutzers über den  {@link de.shelp.android.tasks.RegTask}
     *
     * @param regView - Die aktuell sichtbare View
     */
    public void registration(View regView) {
        EditText mail = (EditText) findViewById(R.id.editMailReg);
        EditText pw = (EditText) findViewById(R.id.editTextPasswordReg);
        EditText pwConfirm = (EditText) findViewById(R.id.editPasswordRegConfirm);
        eMail = mail.getText().toString();
        password = pw.getText().toString();
        passwordConfirm = pwConfirm.getText().toString();
        //Verschlüsselung des Passwortes
        result = computeMD5Hash(password);

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



    /**
     * Methode um das Passwort zu Verschlüsseln
     *
     * @param password - Passwort, das gehasht wird
     * @return - Das gehashte Passwort
     *
     */
    public String computeMD5Hash(String password) {
        try {
            // Erstelle MD5 Hash cc03e747a6afbbcbf8be7668acfebee5
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
