import java.io.Serializable;

/**
 * Project-4 Marketplace
 *
 * <p>Purdue University -- CS18000 -- Spring 2023 -- Project-4</p>
 *
 * @version April 10, 2023
 *
 * @author: Seungjae Baik, Jane Billa, Thomas Birk, Kuan-Yu Chen, Jai Nanda
 */


public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
