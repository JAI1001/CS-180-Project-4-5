import java.awt.*;
import java.io.*;
import java.security.spec.ECField;
import java.util.ArrayList;


/**
 * Project-4 Marketplace
 *
 * <p>Purdue University -- CS18000 -- Spring 2023 -- Project-4</p>
 *
 * @version April 10, 2023
 *
 * @author: Seungjae Baik, Jane Billa, Thomas Birk, Kuan-Yu Chen, Jai Nanda
 */

/*
   Class for all users
   Made by Thomas, feel free to ask questions for clarification
*/


public class User {
    private String username;
    private String emailAddress;
    private String password;
    private boolean buyer; //a user can only be a buyer or a seller, not both
    private String storeName;

    public User(String username, String password, String emailAddress, boolean buyer, String storeName) { //constructor for a new
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.buyer = buyer;
        this.storeName = storeName;
    }

    public String getName() {
        return username;
    }


    public String getEmailAddress() {
        return emailAddress;
    }


    public String getPassword() {
        return password;
    }

    public boolean isBuyer() {
        return buyer;
    }

    public String getStoreName() {
        return storeName;
    }

    //setters below
    public void setName(String username) {
        this.username = username;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
