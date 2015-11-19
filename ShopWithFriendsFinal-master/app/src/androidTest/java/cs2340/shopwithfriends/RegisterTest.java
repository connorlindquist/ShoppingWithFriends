package cs2340.shopwithfriends;

import junit.framework.TestCase;

import cs2340.edu.gatech.shoppingwithfriends.Login.Registration;

/**
 * Created by elise on 4/3/15.
 */
public class RegisterTest extends TestCase{
    String[] UserIDs = {"elise", "ben", "taylor", "connor", "bik"};
    String[] emails = {"e@gmail.com", "b@gmail.com", "t@gmail.com", "c@gmail.cim", "bi@gmail.com"};
    String[] pass = {"pass1", "pass2", "pass3", "pass4", "pass5"};
    String[] passConf = {"pass1", "pass2", "pass3", "pass4", "pass5"};
    String[] names = {"Elise", "Ben", "Taylor", "Connor", "Bik"};
    Registration r = new Registration();

    public void testValidRegister() {
        for(int i = 0; i < 5; i++) {
            assertEquals("Registration successful!", r.validRegister(UserIDs[i], emails[i], pass[i], names[i], passConf[i]));
        }
    }

    public void testEmptyID() {
        assertEquals("Invalid username", r.validRegister("", "a@gmail.com", "pass5", "Ann", "pass5"));
    }

    public void testInvalidEmail1() {
        assertEquals("Invalid email", r.validRegister("chase", "", "pass7", "Chase", "pass7" ));
    }

    public void testInvalidEmail2() {
        assertEquals("Invalid email", r.validRegister("morgan", "m @gmail.com", "pass8", "Morgan", "pass8"));
    }

    public void testPassMisMatch() {
        assertEquals("Passwords must match", r.validRegister("noah", "n@gmail.com", "pass9", "Noah", "pass10"));
    }

    public void testInvalidUsername() {
        assertEquals("Invalid username", r.validRegister("", "m@gmail.com", "pass11", "Maddie", "pass11"));
    }

}
