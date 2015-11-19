package cs2340.edu.gatech.shoppingwithfriends.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseUser;

import cs2340.edu.gatech.shoppingwithfriends.Friends.MyFriends;
import cs2340.edu.gatech.shoppingwithfriends.Friends.addFriend;
import cs2340.edu.gatech.shoppingwithfriends.Items.Deals;
import cs2340.edu.gatech.shoppingwithfriends.Items.PostItem;
import cs2340.edu.gatech.shoppingwithfriends.Items.WishList;
import cs2340.edu.gatech.shoppingwithfriends.Login.Login;
import cs2340.edu.gatech.shoppingwithfriends.Map.Map;
import cs2340.edu.gatech.shoppingwithfriends.R;


public class MainScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
     * Logs user out
     * Returns to main screen of app
     * @param view Logout
     */
    public void logoutRun(View view) {
        final AlertDialog alertDialog = new AlertDialog.Builder(MainScreen.this).create();
        alertDialog.setTitle("Logout?");
        alertDialog.setMessage("Are you sure you want to logout?");
        alertDialog.setButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });
        alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        alertDialog.show();
    }

    /**
     * Goes to friends screen when friends button is clicked
     * @param view
     */
    public void friendsScreen(View view){
        Intent intent = new Intent(this, MyFriends.class);
        startActivity(intent);
    }

    /**
     * Goes to add friends screen when add friends button is clicked
     * @param view
     */
    public void addFriendScreen(View view) {
        Intent intent = new Intent(this, addFriend.class);
        startActivity(intent);
    }

    public void logout() {
        ParseUser.logOut();
        Context context = getApplicationContext();
        CharSequence text = "Logout Successful";
        int duration = Toast.LENGTH_LONG;
        Toast logoutSuccess = Toast.makeText(context, text, duration);
        logoutSuccess.show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void postItem(View view){
        Intent intent = new Intent(this, PostItem.class);
        startActivity(intent);
    }

    public void wishList(View view){
        Intent intent = new Intent(this, WishList.class);
        startActivity(intent);
    }

    public void mapScreen(View view) {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void deals(View view) {
        Intent intent = new Intent(this, Deals.class);
        startActivity(intent);
    }
}