package cs2340.edu.gatech.shoppingwithfriends.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cs2340.edu.gatech.shoppingwithfriends.Controller.MainActivity;
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainScreen;
import cs2340.edu.gatech.shoppingwithfriends.Login.Login;

/**
 * Activity which starts an intent for either the logged in (MainActivity) or logged out
 * (SignUpOrLoginActivity) activity.
 */
public class DispatchActivity extends Activity {

    public DispatchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if there is current user info
        if (Login.currentUserName != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, MainScreen.class));
        }
    }

}