package cs2340.edu.gatech.shoppingwithfriends.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cs2340.edu.gatech.shoppingwithfriends.Controller.DataBaseUserHelper;
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainActivity;
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainScreen;
import cs2340.edu.gatech.shoppingwithfriends.R;
import cs2340.edu.gatech.shoppingwithfriends.User.User;

/**
 import com.parse.LogInCallback;
 import com.parse.Parse;
 import com.parse.ParseAnalytics;
 import com.parse.ParseException;
 import com.parse.ParseUser; */

public class Login extends ActionBarActivity {

    public static String currentUserName;
    DataBaseUserHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DataBaseUserHelper(this);
        //ParseAnalytics.trackAppOpenedInBackground(getIntent());
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

    /**
     * Logs user in
     */
    public boolean loginCheck(View view) {
        EditText username = (EditText) this.findViewById(R.id.usernameText);
        EditText password = (EditText) this.findViewById(R.id.passwordText);
        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());
        currentUserName = user;
        return loginHelper(user, pass);
    }

    public boolean loginHelper(String user, String pass) {
        List<User> registList = db.getAllUsers();
        for (User a : registList) {
            System.out.println(a.getUserID());
            System.out.println(user);
            if (a.getUserID().equals(user)) {
                String p = db.getUser(user).getPassword();
                String userID = db.getUser(user).getUserID();
                if (user.equals(userID) && pass.equals(p)) {
                    Toast loginSuccess = Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT);
                    loginSuccess.show();
                    Intent intent = new Intent(this, MainScreen.class);
                    startActivity(intent);
                    return true;
                }
            }
        }
        Toast loginFail = Toast.makeText(getApplicationContext(), "Invalid username/password combination", Toast.LENGTH_SHORT);
        loginFail.show();
        return false;
    }

    /**
     * Cancels login and returns to welcome screen
     * @param view Login
     */
    public void cancelLogin(View view){
        Intent loginScreen = new Intent(this, MainActivity.class);
        startActivity(loginScreen);
    }

    public void doneLaunch(){
        Intent loginScreen = new Intent(this, MainScreen.class);
        startActivity(loginScreen);
    }
}
