package cs2340.edu.gatech.shoppingwithfriends.Friends;

/**
 * Created by elise on 3/13/15.
 */
public class Friend {
    private String name;
    private String email;

    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {return name; }

    public String getEmail() {return email; }
}
