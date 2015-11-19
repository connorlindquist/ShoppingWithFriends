package cs2340.edu.gatech.shoppingwithfriends.User;

/**
 * Created by connorlindquist on 2/6/15.
 */
public class UserAlreadyExists extends Exception {

    private String message  = "UserID already exists";

    public UserAlreadyExists() {
        super();
    }

    public UserAlreadyExists(String message) {
        super(message);
        this.message = message;
    }

    public UserAlreadyExists(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}