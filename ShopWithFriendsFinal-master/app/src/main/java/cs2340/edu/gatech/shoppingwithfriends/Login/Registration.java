package cs2340.edu.gatech.shoppingwithfriends.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cs2340.edu.gatech.shoppingwithfriends.Controller.DataBaseUserHelper;
import cs2340.edu.gatech.shoppingwithfriends.Controller.DispatchActivity;
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainActivity;
import cs2340.edu.gatech.shoppingwithfriends.R;
import cs2340.edu.gatech.shoppingwithfriends.User.User;

/**
 import com.parse.ParseException;
 import com.parse.ParseUser;
 import com.parse.SignUpCallback; */

public class Registration extends ActionBarActivity {
    DataBaseUserHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DataBaseUserHelper(this);
        User newUser = new User("admin", "admin", "password", "admin");
        User user2 = new User("connor", "connor", "password", "connor");
        db.addUser(user2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    /**
     * Cancels registration without storing data
     * Returns to welcome screen of app
     * @param view Registration
     */
    public void cancelRegister(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Registration Cancelled";
        int duration = Toast.LENGTH_LONG;
        Toast cancelReg = Toast.makeText(context, text, duration);
        cancelReg.show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * Registration process
     * Checks validity of variables
     * @param view Registration
     */
    public void validRegister(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        String name = ((EditText) findViewById(R.id.nameReg)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailReg)).getText().toString();
        String username = ((EditText) findViewById(R.id.usernameReg)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordReg)).getText().toString();
        String passwordConf = ((EditText) findViewById(R.id.passConfReg)).getText().toString();
        if (name.length() == 0) {
            toast = Toast.makeText(context, "Invalid name", duration);
            toast.show();
        } else if (email.contains(" ") || email.length() == 0) {
            toast = Toast.makeText(context, "Invalid email", duration);
            toast.show();
        } else if (!password.equals(passwordConf)) {
            toast = Toast.makeText(context, "Passwords must match", duration);
            toast.show();
        } else if (username.length() == 0) {
            toast = Toast.makeText(context, "Invalid username", duration);
            toast.show();
        } else {
            User newUser = new User(username, email, password, name);
            db.addUser(newUser);
            CharSequence text = "Registration successful!";
            Toast loginFail = Toast.makeText(context, text, duration);
            loginFail.show();
            Intent intent = new Intent(Registration.this, DispatchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            /**
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.put("ratings", 0);
            user.put("name", name);
            final ProgressDialog dialog = new ProgressDialog(Registration.this);
            dialog.setMessage("Signing up");
            dialog.show();
            // Call the Parse signup method
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    dialog.dismiss();
                    if (e != null) {
                        // Show the error message
                        Toast.makeText(Registration.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        // Start an intent for the dispatch activity
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        CharSequence text = "Registration successful!";
                        Toast loginFail = Toast.makeText(context, text, duration);
                        loginFail.show();
                        Intent intent = new Intent(Registration.this, DispatchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }); */
        }
    }
    
    public Toast validRegister(String username, String email, String password, String name, String passwordConf) {
         Context context = getApplicationContext();
         int duration = Toast.LENGTH_SHORT;
         Toast toast;
         if (name.length() == 0) {
            toast = Toast.makeText(context, "Invalid name", duration);
            toast.show();
            return toast;
        } else if (email.contains(" ") || email.length() == 0) {
            toast = Toast.makeText(context, "Invalid email", duration);
            toast.show();
            return toast;
        } else if (!password.equals(passwordConf)) {
            toast = Toast.makeText(context, "Passwords must match", duration);
            toast.show();
            return toast;
        } else if (username.length() == 0) {
            toast = Toast.makeText(context, "Invalid username", duration);
            toast.show();
            return toast;
        } else {
            User newUser = new User(username, email, password, name);
            db.addUser(newUser);
            CharSequence text = "Registration successful!";
            Toast loginFail = Toast.makeText(context, text, duration);
            loginFail.show();
            Intent intent = new Intent(Registration.this, DispatchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return loginFail;
        }
    }
}
