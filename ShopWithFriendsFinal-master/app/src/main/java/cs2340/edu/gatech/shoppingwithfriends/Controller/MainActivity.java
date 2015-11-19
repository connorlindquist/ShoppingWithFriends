package cs2340.edu.gatech.shoppingwithfriends.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseObject;

import cs2340.edu.gatech.shoppingwithfriends.Login.Login;
import cs2340.edu.gatech.shoppingwithfriends.R;
import cs2340.edu.gatech.shoppingwithfriends.Login.Registration;
import cs2340.edu.gatech.shoppingwithfriends.User.User;

public class MainActivity extends ActionBarActivity {
    public ParseObject user;

    /**
     * Adds two accounts
     * admin- not shown in friends list, auto override
     * test- test user for friends list
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(getApplicationContext(), "wRiRrWNKQgEKQEc6OZtqAPYsekcKgjaEO9YolId4", "9mvL7SN6X96e3a0xH8rl4QJDxwoHSm0MWzzVPm9A");
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

        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts login screen on 'login' click
     * @param view Login
     */
    public void loginScreen(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    /**
     * Starts registration screen on 'registration' click
     * @param view Registration
     */
    public void register(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }
}