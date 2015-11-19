package cs2340.edu.gatech.shoppingwithfriends.Items;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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


public class WishList extends ActionBarActivity {

    DataBaseUserHelper db;

    ListView lv;
    ArrayList<String> listItems = new ArrayList<String>();
    List<Item> itemCopy;
    String current = Login.currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseUserHelper(this);

        setContentView(R.layout.activity_my_items);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        lv = (ListView)findViewById(R.id.itemListView);
        lv.setAdapter(itemsAdapter);


        List<User> friendsList = db.getUser(Login.currentUserName).getFriends();                                           //list of all users
        List<Item> itemList = db.getUser(Login.currentUserName).getWishList();                                             //wishlist
        Item match;

        for(User user: friendsList) {
            for(Item item: itemList) {
                if(user.getPostedItems().contains(item)) {
                    match = item;
                    final AlertDialog matchDialog = new AlertDialog.Builder(WishList.this).create();
                    matchDialog.setTitle("You have matches!");
                    matchDialog.setMessage("Items on sale:\n" + item);
                    matchDialog.setButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            matchDialog.hide();
                        }
                    });
                    matchDialog.show();
                }
            }
        }


        itemCopy = db.getUser(current).getWishList();
        if(itemCopy.size() != 0) {
            for(int i = 0; i < itemCopy.size(); i++) {
                Item temp = itemCopy.get(i);
                String name = temp.getName();
                double price = temp.getPrice();
                String note = temp.getNotes();
                String listItemText = ("Name: " + name + "\nMax Price: " + price + "\nNotes: " + note);
                listItems.add(listItemText);
            }
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "No items added yet";
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
