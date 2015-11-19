package cs2340.edu.gatech.shoppingwithfriends.User;

import java.util.ArrayList;

import cs2340.edu.gatech.shoppingwithfriends.Items.Item;

/**
 * Created by connorlindquist on 2/6/15.
 */
public class User {

    private String name;
    private String userID;
    private String password;
    private String email;
    private static ArrayList<User> friendsList;
    private double rating;
    private int reports;
    private static ArrayList<Item> wishList;
    private static ArrayList<Item> postedItems;
    /**
     * Initializes variables
     * @param userID
     * @param email
     * @param password
     */
    public User(String userID, String email, String password, String name) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.name = name;
        friendsList = new ArrayList<>();
        wishList = new ArrayList<>();
        postedItems = new ArrayList<>();
    }


    /**
     * UserID getter
     * @return a String with the user's userID
     */
    public String getName() {
        return name;
    }

    public String getUserID(){ return userID; }

    public String getEmail(){ return email; }

    public double getRatings(){ return rating; }

    public int getReports(){ return reports; }
    /**
     * Password getter
     * @return a String with the user's password
     */
    public String getPassword() {
        return password;
    }

    public static void addFriend(User u) {
        //if()
    }

    /**
     * Removes target user from the current user's friends list.
     * @param userID
     * @return boolean representing success.
     */
    public static boolean removeFriend(String userID){
        for(int i=0; i<friendsList.size(); i++){
            User spot = friendsList.get(i);
            if(spot.getUserID().equals(userID)){
                friendsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> getFriends(){
        return friendsList;
    }

    public static ArrayList<Item> getWishList(){ return wishList; }

    public static ArrayList<Item> getPostedItems() { return postedItems; }
    /**
    @override
    */
    public boolean equals(User u) {
        if(userID.equals(u.getUserID()) && email.equals(u.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

}