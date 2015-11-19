package cs2340.edu.gatech.shoppingwithfriends.Friends;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs2340.edu.gatech.shoppingwithfriends.Controller.DataBaseUserHelper;
import cs2340.edu.gatech.shoppingwithfriends.Login.Login;
import cs2340.edu.gatech.shoppingwithfriends.R;
import cs2340.edu.gatech.shoppingwithfriends.User.User;


public class MyFriends extends ActionBarActivity {

    DataBaseUserHelper db;
    String current = Login.currentUserName;

    ListView lv;
    ArrayList<String> listFriends=new ArrayList<String>();
    List<User> friendsCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DataBaseUserHelper(this);
        setContentView(R.layout.activity_friends);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listFriends);
        lv = (ListView)findViewById(R.id.friendsListView);
        lv.setAdapter(itemsAdapter);
        friendsCopy = db.getUser(current).getFriends();
        if(friendsCopy.size() != 0) {
            for (int i = 0; i < friendsCopy.size(); i++) {
                User temp = friendsCopy.get(i);
                String name = temp.getName();
                String email = temp.getEmail();
                String listFriendText = ("Name: " + name + "\nEmail:" + email);
                listFriends.add(listFriendText);
            }
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "No friends added yet";
            Toast loginFail = Toast.makeText(context, text, duration);
            loginFail.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_items, menu);
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
