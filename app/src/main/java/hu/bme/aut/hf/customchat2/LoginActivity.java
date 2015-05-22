package hu.bme.aut.hf.customchat2;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import hu.bme.aut.hf.customchat2.db.DBLoader;


public class LoginActivity extends Activity {
    private boolean debugMode = false; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //DB open
        final DBLoader db = new DBLoader(this);
        try {
            db.open();
            db.createUser("a", Crypto.genHash("asd"));
            //showToast("User " + db.getUserByName("a").name + " created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Button handle
        Button loginbutton = (Button) findViewById(R.id.button2);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start location service
                Intent li = new Intent(getApplicationContext(), ServiceLocation.class);
                startService(li);
                PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).getBoolean("example_checkbox", false);
                //get textboxes
                String login = ((TextView)findViewById(R.id.editText3)).getText().toString();
                String pass = ((TextView)findViewById(R.id.editText4)).getText().toString();
                //create intent to next activity
                Intent i = new Intent(LoginActivity.this, CnvActivity.class);
                Session.user = db.getUserByName(login); //get user by name
                if(debugMode)
                    Session.user = new User(1,"debug", "debug");
                if(debugMode || Session.user != null && Crypto.checkHash(pass, Session.user.hashedPw)) { //check password
                    db.close(); //close db so it can be opened by other activities
                    startActivity(i); //then starting cnv
                }
                else {
                    showToast("This account does not exist.");
                    Session.user = null;
                }
            }
        });
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
