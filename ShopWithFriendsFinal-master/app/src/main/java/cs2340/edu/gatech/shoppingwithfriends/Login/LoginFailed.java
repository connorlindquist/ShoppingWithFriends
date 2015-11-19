package cs2340.edu.gatech.shoppingwithfriends.Login;

/**
 * Created by connorlindquist on 2/6/15.
 */
public class LoginFailed extends Exception {

    private String message  = null;

    public LoginFailed() {
        super();
    }

    /**
     * Failed login message
     * @param message
     */
    public LoginFailed(String message) {
        super(message);
        this.message = message;
    }

    public LoginFailed(Throwable cause) {
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