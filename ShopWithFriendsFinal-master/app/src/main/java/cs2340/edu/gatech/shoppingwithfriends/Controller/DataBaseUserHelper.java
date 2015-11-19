package cs2340.edu.gatech.shoppingwithfriends.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

import cs2340.edu.gatech.shoppingwithfriends.User.User;

/**
 * Created by Elise on 3/23/2015.
 */
public class DataBaseUserHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataBaseUserHelper";
    private static final String TABLE_USERS = "Users";

    private static final String USER_ID = "UserID";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";

    /**
     * Calls parent super constructor
     * @param context current context
     */
    public DataBaseUserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + USER_ID + " TEXT," + USER_EMAIL + " TEXT," + USER_PASSWORD + " TEXT," + USER_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log the version upgrade
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        //  Drop old version and create a new one
        db.execSQL("DROP TABLE IF EXISTS TEMPLATE");
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getUserID());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_NAME, user.getName());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {USER_ID, USER_EMAIL, USER_PASSWORD, USER_NAME}, USER_ID + "=?", new String[] {name}, null, null, null, null);
        if (cursor != null) {
            if(!cursor.moveToFirst()){
                return null;
            }
        }
        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                users.add(user);
            } while(cursor.moveToNext());
        }

        return users;

    }
}
