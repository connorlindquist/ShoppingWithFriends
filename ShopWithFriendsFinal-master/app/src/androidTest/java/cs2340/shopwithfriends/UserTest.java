package cs2340.shopwithfriends;

import junit.framework.TestCase;

import cs2340.edu.gatech.shoppingwithfriends.User.User;

/**
 * Created by connorlindquist on 4/3/15.
 * This class is created to test the RemoveFriend method in User
 */
public class UserTest extends TestCase {
    //Test users created
    User connor = new User("connor", "connor@test.com", "password", "Connor");
    User jake = new User("jake", "jake@test.com", "password", "Jake");
    User jake2 = new User("jake", "jake2@test.com", "password", "Jake");
    User bob = new User("bob", "bob@test.com", "password", "Bob");
    User sam = new User("sam", "sam@test.com", "password", "Sam");
    User notFriend = new User("notFriend", "notFriend@test.com", "password", "NotFriend");

    protected void setUp() throws Exception {
        super.setUp();
        //Add friends to User.friendList to test removal
        User.getFriends().add(connor);
        User.getFriends().add(jake);
        User.getFriends().add(jake2);
        User.getFriends().add(sam);
    }
    /*
     * Tests to verify that users have been added
     */
    public void testVerifyAdd() {
        assertTrue("Does not contain user", User.getFriends().contains(connor));
        assertTrue("Does not contain user", User.getFriends().contains(jake));
        assertTrue("Does not contain user", User.getFriends().contains(jake2));
        assertTrue("Does not contain user", User.getFriends().contains(sam));
        assertFalse("User does exist", User.getFriends().contains(bob));

    }

    /*
     * removeFriend tests
     */
    public void testRemoveFriend() {
        //Test removal of friend that exists in two steps
        User.removeFriend(connor.getUserID());
        assertFalse("Connor is still in list", User.getFriends().contains(connor));
        //Test removal of friend that exists in one step
        assertTrue("Jake is still in list", User.removeFriend(jake.getUserID()));
        //Test removal of friend with similar information
        assertTrue("Jake2 is not in list", User.getFriends().contains(jake2));
        //Test removal of User who does not exist
        assertFalse("Returned true but does not exist", User.removeFriend("random"));
        //Test removal of friend by string as opposed to UserID
        assertTrue("Remove by string does not work", User.removeFriend("sam"));
        //Test removal of null User
        assertFalse("Null removed incorrectly", User.removeFriend(null));
        //Test removal of User who exists but is not in friendsList
        assertFalse("Friend removed but not in friends list", User.removeFriend(notFriend.getUserID()));
    }
}
