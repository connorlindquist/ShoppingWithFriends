package cs2340.edu.gatech.shoppingwithfriends.Friends;

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
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainScreen;
import cs2340.edu.gatech.shoppingwithfriends.Login.Login;
import cs2340.edu.gatech.shoppingwithfriends.R;
import cs2340.edu.gatech.shoppingwithfriends.User.User;


public class addFriend extends ActionBarActivity {

    DataBaseUserHelper db;
    String current = Login.currentUserName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        db = new DataBaseUserHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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
     * Cancel adding a friend and return to main screen
     * @param view
     */
    public void cancel(View view){
        Intent cancelAdd = new Intent(this, MainScreen.class);
        startActivity(cancelAdd);
    }

    /**
     * Add a friend
     * Ensures user exists and can be added. If not, toast message
     * @param view
     */
    public void addFriend(View view){
        EditText username = (EditText) this.findViewById(R.id.usernameText);
        EditText emailAdd = (EditText) this.findViewById(R.id.emailText);
        final String user = String.valueOf(username.getText());
        final String email = String.valueOf(emailAdd.getText());
        if(user.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Name";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else if (email.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid Email";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else if(db.getUser(current).getFriends().contains(new User(user, email, null, null)) == true) {
            Context context = getApplicationContext();
            CharSequence text = "Friend Already Added";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else {
            User friend = db.getUser(user);
            System.out.println(friend.getUserID());
            db.getUser(current).addFriend(friend);
            CharSequence text = "Friend added successfully";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(getApplicationContext(), text, duration).show();
            Intent launchApp = new Intent(this, MainScreen.class);
            startActivity(launchApp);
        }
    }
}