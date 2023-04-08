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
                        users = new User(user);
                        if (users.getName().equals(user)) {
                            loop = false;
                        }
                        if (loop) {
                            System.out.println(NOSUCHUSER);
                            String restart = scan.nextLine();
                            if (restart.equals("YES")) {
                                break;
                            }
                        }
                    } while (loop);
                    String pass;
                    do {
                        System.out.println(PASS);
                        pass = scan.nextLine();
                    } while (!users.testPassword(pass));
                    break;
                case 2:
                    String user = null;
                    boolean buyer = true;
                    do {
                        loop = true;
                        System.out.println(USER);
                        user = scan.nextLine();
                        users = new User(user);
                        if (users.toString().split(",")[0].equals(user)) {
                            loop = false;
                        }
                        if (loop) {
                            System.out.println(SUCHUSER);
                        }
                    } while (loop);
                    System.out.println(SETPASS);
                    pass = scan.nextLine();
                    System.out.println(SETEMAIL);
                    String email = scan.nextLine();
                    int buySell;
                    do {
                        System.out.println(ACCTYPE);
                        buySell = scan.nextInt();
                        scan.nextLine();
                        if (buySell == 1) {
                            buyer = false;
                        } else if (buySell == 2) {
                            buyer = true;
                        } else {
                            System.out.println(RIGHTOPTION);
                        }
                    } while (buySell != 1 && buySell != 2);
                    users = new User(user, pass, email, buyer);
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
        user.isBuyer()
    }
}
