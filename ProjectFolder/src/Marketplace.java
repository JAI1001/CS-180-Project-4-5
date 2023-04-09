import java.io.*;
import java.util.Scanner;
public class Marketplace {

    // Strings for Login and Account Creation
    private static final String WELCOME = "Welcome to the Marketplace !";
    private static final String USER = "Enter username";
    private static final String RIGHTOPTION = "Enter Correct option!";
    private static final String LOGIN = """
Choose Sign-in or Sign-up:
1. Log in
2. Create New Account
""";
    private static final String NOSUCHUSER = "Username not found. Would you like to make a new account? (Answer YES if needed)";
    private static final String SUCHUSER = "Username already exists. Choose new name.";
    private static final String ACCTYPE = """
Choose account type:
1. Seller
2. Buyer
""";
    private static final String SETPASS = "Set password: ";
    private static final String SETEMAIL = "Set email: ";
    private static final String PASS = "Enter password";

    private static final String WRONGPASS = "Wrong password !";

    // Strings for buyer menu
    private static final String BUYMENU = """
1. Enter product search list
2. Show purchase history
3. Show remaining credit
""";
    private static final String SEARCH = """
1. Show entire Store List
2. Search with 3. Show remaining credit
4. Back
""";
    // Strings for seller menu
    private static final String SELLMENU = """
1. Edit existing product
2. Add new product
3. Show statistics
""";
        /*
    //1. to edit existing product, they have to go to a separate scanner prompt that asks "enter the product name"
    if (1)
    then "what is the name of the product you would like to edit?"
    if substring equals words in that users file, then allow them to write to the file
    else throw an exception if no such word exists and loop it back to the prompts

    2. for add new product, show a bunch of new prompts
    "what is the name?"
    "what is the price?"
    "how many would you like to add?"
    "what store will this be sold in?"
    if the store name doesn't exist, add a new store name to the userinfo file or wherever we're storing it
    
    3. show statistics
    call methods that jai is gonna put in later


 */


    // method for logging in
    public static User login(Scanner scan) throws IOException, UserNotFoundException {
        System.out.println(LOGIN);
        int response;
        User users = null;
        do {
            response = scan.nextInt();
            scan.nextLine();
            boolean loop;
            switch (response) {
                case 1:
                    do {
                        loop = true;
                        System.out.println(USER);
                        String user = scan.nextLine();
                        try {
                            users = new User(user);
                            loop = false;
                        } catch (UserNotFoundException e) {
                            System.out.println(e.getMessage() + ". Please try again.");
                        }
                    } while (loop);
                    String pass;
                    do {
                        System.out.println(PASS);
                        pass = scan.nextLine();
                    } while (!users.testPassword(pass));
                    break;
                case 2:
                    System.out.println("Enter Username:");
                    String user = scan.nextLine();

                    while (User.userExists(user)) {
                        System.out.println(SUCHUSER);
                        System.out.println("Enter Username:");
                        user = scan.nextLine();
                    }

                    System.out.println(SETPASS);
                    String password = scan.nextLine();
                    System.out.println(SETEMAIL);
                    String email = scan.nextLine();
                    System.out.println("Buyer or Seller (enter 'b' or 's':");
                    boolean buyer = false;
                    if (scan.nextLine().equalsIgnoreCase("b")) {
                        buyer = true;
                    }

                    users = new User(user, password, email, buyer);
                    users.writeUser();
                    break;
                default:
                    System.out.println("Please enter valid option !\n");
            }
        } while (response != 1 && response != 2);
        return users;
    }



    public static void main(String[] args) throws IOException, UserNotFoundException {
        Scanner scan = new Scanner(System.in);
        User user;
        System.out.println(WELCOME);
        do {
            user = login(scan);
        } while (user.getName() == null);
        System.out.println(user.toString());
        user.isBuyer();
    }
}
