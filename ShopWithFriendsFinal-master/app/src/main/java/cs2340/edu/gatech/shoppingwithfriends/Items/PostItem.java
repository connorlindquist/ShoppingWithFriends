package cs2340.edu.gatech.shoppingwithfriends.Items;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cs2340.edu.gatech.shoppingwithfriends.Controller.DataBaseUserHelper;
import cs2340.edu.gatech.shoppingwithfriends.Controller.MainScreen;
import cs2340.edu.gatech.shoppingwithfriends.Login.Login;
import cs2340.edu.gatech.shoppingwithfriends.R;

public class PostItem extends ActionBarActivity {

    DataBaseUserHelper db;
    String current = Login.currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);
        db = new DataBaseUserHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_item, menu);
        return true;
    }


    @Override
    /**
     *
     */
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
     * This deletes the item you are about to post to a wishList.
     * @param view
     */
    public void cancel(View view) {
        final AlertDialog alertDialog = new AlertDialog.Builder(PostItem.this).create();
        alertDialog.setTitle("Are you sure?");
        alertDialog.setMessage("Are you sure you want to delete this item?");
        alertDialog.setButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.hide();
            }
        });
        alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                delete();
            }
        });
        alertDialog.show();
    }

    /**
     * Returns to the main screen. Cancels method.
     */
    public void delete() {
        Intent launchApp = new Intent(this, MainScreen.class);
        startActivity(launchApp);
    }

    /**
     * Add's an item to the current user's WISHLIST. NOTE: PostDeal is used to post deals the current users sees.
     * Checks to see if the item posted by the user is valid and stores the item object in the wishList database.
     * @param view
     */
    public void postItemAction(View view) {
        EditText itemName = (EditText) this.findViewById(R.id.itemNameText);
        EditText itemPrice = (EditText) this.findViewById(R.id.maxPriceText);
        EditText itemNotes = (EditText) this.findViewById(R.id.itemNotesText);
        String name = String.valueOf(itemName.getText());
        String notes = String.valueOf(itemNotes.getText());
        double price = 0;
        if (itemPrice.length() > 0) {
            price = new Double(String.valueOf(itemPrice.getText()));
        }
        if (name.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Invalid item name";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else if (price == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Price must be greater than zero";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else if (db.getUser(current).getWishList().contains(name) == true) {
            Context context = getApplicationContext();
            CharSequence text = "Item already added";
            int duration = Toast.LENGTH_SHORT;
            Toast failed = Toast.makeText(context, text, duration);
            failed.show();
        } else {
            Item item = new Item(name, price, notes);
            ArrayList<Item> wishListItems = db.getUser(current).getWishList();
            wishListItems.add(item);
            CharSequence text = "Item added successfully";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(getApplicationContext(), text, duration).show();
            Intent launchApp = new Intent(this, MainScreen.class);
            startActivity(launchApp);
        }
    }

}

