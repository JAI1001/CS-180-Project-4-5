import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Project-4 Marketplace
 *
 * <p>Purdue University -- CS18000 -- Spring 2023 -- Project-4</p>
 *
 * @version April 10, 2023
 *
 * @author: Seungjae Baik, Jane Billa, Thomas Birk, Kuan-Yu Chen, Jai Nanda
 */


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
    private static final String SUCHUSER = "Username already exists. Choose new name.";
    private static final String SETPASS = "Set password: ";
    private static final String SETEMAIL = "Set email: ";
    private static final String PASS = "Enter password";

    private static final String WRONGPASS = "Wrong password !";

    // Strings for buyer menu
    private static final String BUYMENU = """
            1. Enter product search list
            2. Purchase items in Shopping Cart
            3. Show statistics
            4. View Purchase History
            5. Exit
            """;
    private static final String SEARCH = """
            1. Show entire Store List
            2. Search with keyword
            """;
    private static final String SHOPPING = """
            1. Buy all items
            """;// removing items from cart method was not achieved, did not have enough time

    // Strings for seller menu
    private static final String SELLMENU = """
            1. Edit existing product
            2. Add new product
            3. Delete product
            4. Show statistics
            5. Show your products
            """;
    private static final String EXIT = """
            Do you want to exit?
            0. Yes
            1. No
            """;
    private static final String ADDCART = "Press 1 to add to cart\nPress 2 to remove from cart\nPress 0 to start over";
    private static final String HOWMUCH = "How much products do you want to purchase or remove?";
    private static final String NOTENOUGH = "There are not enough products";
    private static final String ENTERKEY = "Enter Keyword";

    static ArrayList<Product> productList = new ArrayList<Product>();
    ArrayList<String> productQuantitySold = new ArrayList<String>();
    ArrayList<String> productStoreName = new ArrayList<String>();
    ArrayList<String> customerName = new ArrayList<String>();
    ArrayList<String> qtyPurchased = new ArrayList<String>();
    ArrayList<String> storeListOne = new ArrayList<String>();

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
    static ArrayList<User> users = new ArrayList<>();

    //===============================================================
    // shopping cart fields

    static ArrayList<String> storedInfo = new ArrayList<>(); //ArrayList to store file info
    static ArrayList<String> userNames = new ArrayList<>();
    static ArrayList<ArrayList<String>> productCart = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<Integer>> quantities = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> totalQuantity = new ArrayList<>();

    // specific shopping cart fields by Kuanyu Chen
    //===============================================================

    public static User login(Scanner scan) {
        User confirmedUser = null;
        int option = -1;
        do {
            System.out.println(LOGIN);
            option = scan.nextInt();
            scan.nextLine();
        } while ((option != 1) && (option != 2));

        switch (option) {
            case 1:
                do {

                    System.out.println("Enter an User Name: ");
                    String username = scan.nextLine();

                    boolean haveName = false;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getName().equals(username)) {
                            haveName = true;
                        }
                    }

                    if (!haveName) {
                        System.out.println("User not Found");
                        continue;
                    }
                    System.out.println("Enter the Password: ");
                    do {
                        String password = scan.nextLine();
                        for (int i = 0; i < users.size(); i++) {
                            if (username.equals(users.get(i).getName())) {
                                if (password.equals(users.get(i).getPassword())) {
                                    for (int j = 0; j < users.size(); j++) {
                                        if (users.get(j).getName().equals(username)) {
                                            return users.get(j);
                                        }
                                    }


                                }
                            }
                        }
                        System.out.println("Incorrect Password");
                    } while (true);

                } while (confirmedUser == null);
            case 2:
                System.out.println("Enter Username:");
                String user = scan.nextLine();

                while (userNames.contains(user)) {
                    System.out.println("Overlapping user name");
                    System.out.println("Enter Username:");
                    user = scan.nextLine();
                }
                System.out.println(SETPASS);
                String password = scan.nextLine();
                System.out.println(SETEMAIL);
                String email = scan.nextLine();

                boolean buyer = false;
                String bs = "";
                do {
                    System.out.println("Buyer or Seller (enter 'b' or 's':");
                    bs = scan.nextLine();
                } while (!bs.equals("b") && !bs.equals("s"));
                if (bs.equalsIgnoreCase("b")) {
                    buyer = true;
                    confirmedUser = new User(user , password , email , buyer , "");
                    users.add(confirmedUser);
                    addNewUserToCart(user); //implementing addnewusermkpadslfnwkjefnjoweqjodalnldanfdaljnfkadmclkdnvjsndacmpkdamcjo
                } else if (bs.equalsIgnoreCase("s")) {
                    System.out.println("Please Enter the Store name: ");
                    String storeName = scan.nextLine();
                    buyer = false;

                    confirmedUser = new User(user , password , email , buyer , storeName);
                    users.add(confirmedUser);
                }
        }
        return confirmedUser;
    }


    public static void addToCart(String userName, Product product, int quantity) {
        System.out.println(quantities.size());
        System.out.println(productCart.size());

        for (int i = 0; i < userNames.size(); i++) {
            System.out.println(userNames.get(i));
        }


        for (int i = 0; i < userNames.size(); i++) {
            if (userName.equals(userNames.get(i))) {
                for (int j = 0; j < productCart.get(i).size(); j++) {
                    if (productCart.get(i).get(j).equals(product.getName())) {
                        quantities.get(i).set(j , quantities.get(i).get(j) + quantity);
                        return;
                    }
                }
                productCart.get(i).add(product.getName());
                quantities.get(i).add(quantity);
            }
        }
    }

    public static void removeFromCart(String userName , Product product, int quantity) {
        for (int i = 0; i < userNames.size(); i++) {
            if (userName.equals(userNames.get(i))) {
                for (int j = 0; j < productCart.get(i).size(); j++) {
                    if (productCart.get(i).get(j).equals(product.getName())) {
                        quantities.get(i).set(j , quantities.get(i).get(j) - quantity);
                    }
                }
                for (int e = 0; e < productCart.get(i).size(); e++) {
                    if (quantities.get(i).get(e) <= 0) {
                        quantities.get(i).remove(e);
                        productCart.get(i).remove(e);
                    }
                }
            }
        }
    }

    public static void addNewUserToCart(String userName) {
        userNames.add(userName);
        productCart.add(new ArrayList<String>());
        quantities.add(new ArrayList<Integer>());
    }

    public static void clearCart(String userName) {
        for (int i = 0; i < userNames.size(); i++) {
            if (userName.equals(userNames.get(i))) {
                productCart.get(i).clear();
                quantities.get(i).clear();
            }
        }
    }

    public static void printShoppingCart(String userName) {
        for (int i = 0; i < userNames.size(); i++) {
            if (userName.equals(userNames.get(i))) {
                System.out.println("===========================================");
                System.out.println("Shopping Cart for: " + userName);
                for (int j = 0; j < productCart.get(i).size(); j++) {
                    System.out.println(j + ". Name: " + productCart.get(i).get(j) + " Quantity:" + quantities.get(i).get(j));
                }
                System.out.println("===========================================");

            }
        }
    }

    public static boolean buyMenu(User user, Scanner scan, Marketplace marketplace) throws IOException {
        System.out.println(BUYMENU);
        int response;
        String answer;
        do {
            response = scan.nextInt();
            scan.nextLine();
        } while (response != 1 && response != 2 && response != 3 && response != 4 && response != 5);
        if (response == 1) {
            System.out.println(SEARCH);
            do {
                response = scan.nextInt();
                scan.nextLine();
            } while (response != 1 && response != 2 && response != 0);
            if (response == 1) { // Search case with displaying all stores
                if (productList == null){
                    System.out.println("No product!");
                    return true;
                } else { // code for displaying all store names so that user can input store name to choose.
                    // Should be dropdown menu in GUI
                    for(int i = 0; i < users.size(); i++) {
                        if (!users.get(i).getStoreName().equals("")) {
                            System.out.println(users.get(i).getStoreName());
                        }
                    }
                }
                Product product = null;
                boolean pass = true;
                System.out.println("Enter Desired Store Name");
                do {
                    answer = scan.nextLine();
                    for(int i = 0; i < users.size(); i++) {
                        if (users.get(i).getStoreName().equals(answer)) {
                            pass = false;
                        }
                    }
                    if (pass) {
                        System.out.println("No such store exists!");
                    }
                } while (pass);
                pass = true;
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getStoreName().equals(answer)) {
                        System.out.printf("%s's store %s\n%s $%.2f %d left in stock\n\n", productList.get(i).getSeller(),
                                productList.get(i).getStoreName(), productList.get(i).getName(), productList.get(i).getPrice(),
                                productList.get(i).getQuantity());
                        pass = false;
                    }
                }
                if (pass) {
                    System.out.println("Store has no products to purchase!");
                    return true;
                }
                System.out.println("Enter Desired Product Name");
                pass = true;
                do {
                    answer = scan.nextLine();
                    for(int i = 0; i < productList.size(); i++) {
                        if (productList.get(i).getName().equals(answer)) {
                            pass = false;
                            product = productList.get(i);
                        }
                    }
                    if (pass) {
                        System.out.println("No such product exists!");
                    }
                } while (pass);
                System.out.printf("%s's store %s\n%s $%.2f %d left in stock\n\n", product.getSeller(),
                        product.getStoreName(), product.getName(), product.getPrice(),
                        product.getQuantity());
                System.out.println(product.getDescription());

                // code block for cart operations
                System.out.println(ADDCART);
                do {
                    response = scan.nextInt();
                    scan.nextLine();
                } while (response != 0 && response != 1 && response != 2);
                if (response == 1) {

                    // number of products to add to cart
                    System.out.println(HOWMUCH);
                    do {
                        do {
                            response = scan.nextInt();
                            scan.nextLine();
                            if (response > product.getQuantity()) {
                                System.out.println(NOTENOUGH);
                            }
                        } while (response > product.getQuantity());
                    } while (response <= 0);
                    addToCart(user.getName(), product, response);

                } else if (response == 2) {

                    // number of products to remove from cart
                    System.out.println(HOWMUCH);
                    response = scan.nextInt();
                    scan.nextLine();
                    System.out.println("ENTER CHECK");
                    removeFromCart(user.getName(), product, response);
                    System.out.println("STORED CHECK");
                } else if (response == 0) {

                    // exit prompt
                    System.out.println(EXIT);
                    do {
                        response = scan.nextInt();
                        scan.nextLine();
                    } while (response != 0 && response != 1);
                    if (response == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }

            } else if (response == 2) { // search case with keywords
                String keyword = null;
                Product product = null;
                boolean pass = true;
                ArrayList<Product> keySearch = new ArrayList<Product>();
                do { // append products with keyword to keysearch
                    System.out.println(ENTERKEY);
                    keyword = scan.nextLine();
                    for(int i = 0; i < productList.size(); i++) {
                        if (productList.get(i).getName().contains(keyword) ||
                                productList.get(i).getSeller().contains(keyword) ||
                                productList.get(i).getStoreName().contains(keyword) ||
                                productList.get(i).getDescription().contains(keyword)) {
                            pass = false;
                            keySearch.add(productList.get(i));
                        }
                    }
                    if (pass) {
                        System.out.println("no product with keyword");
                        return true;
                    }
                } while (pass);
                for(int i = 0; i < keySearch.size(); i++) {
                    product = keySearch.get(i);
                    System.out.printf("%s's store %s\n%s $%.2f %d left in stock\n\n", product.getSeller(),
                            product.getStoreName(), product.getName(), product.getPrice(),
                            product.getQuantity());
                }
                System.out.println("Enter desired product");
                pass = true;
                do {
                    answer = scan.nextLine();
                    for(int i = 0; i < keySearch.size(); i++) {
                        if (keySearch.get(i).getName().equals(answer)) {
                            product = keySearch.get(i);
                            pass = false;
                        }
                    }
                    if(pass) {
                        System.out.println("Enter valid product name!");
                    }
                } while (pass);
                System.out.printf("%s's store %s\n%s $%.2f %d left in stock\n\n", product.getSeller(),
                        product.getStoreName(), product.getName(), product.getPrice(),
                        product.getQuantity());
                System.out.println(product.getDescription());

                // code block for cart operations
                System.out.println(ADDCART);
                do {
                    response = scan.nextInt();
                    scan.nextLine();
                } while (response != 0 && response != 1);
                if (response == 1) {

                    // number of products to add to cart
                    System.out.println(HOWMUCH);
                    do {
                        do {
                            response = scan.nextInt();
                            scan.nextLine();
                            if (response > product.getQuantity()) {
                                System.out.println(NOTENOUGH);
                            }
                        } while (response > product.getQuantity());
                    } while (response <= 0);
                    addToCart(user.getName(), product, response);
                }
                if (response == 0) {

                    // exit prompt
                    System.out.println(EXIT);
                    do {
                        response = scan.nextInt();
                        scan.nextLine();
                    } while (response != 0 && response != 1);
                    if (response == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else if (response == 2) {
            printShoppingCart(user.getName());
            System.out.println(SHOPPING);
            do {
                response = scan.nextInt();
                scan.nextLine();
            } while (response != 1 && response != 2);
            if (response == 1) {
                clearCart(user.getName());

                //dqsa[dmaoidcaocmpas;kcjlbad[ks;ljpioc[l;knbhiop[lkmnbhkjop[l;,mnbkhojp][;,m.nbg
            } else if (response == 2) {
                clearCart(user.getName());
            }
        }
        // exit prompt
        System.out.println(EXIT);
        do {
            response = scan.nextInt();
            scan.nextLine();
        } while (response != 0 && response != 1);
        if (response == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean sellMenu(User user, Scanner scan, Marketplace marketplace) {
        System.out.println(SELLMENU);
        int choice;
        do {
            choice = scan.nextInt();
            scan.nextLine();
        } while (choice != 3 && choice != 1 && choice != 2 && choice != 4 && choice != 5);
        if (choice == 1) {
            System.out.println("Enter the name of the product you want to edit:");
            String name = scan.nextLine();
            // step of choosing product. when doing GUI, make sure it is a dropdown menu to choose from the products from seller
            System.out.println("Enter the price of the product you want to edit:");
            double newPrice = scan.nextDouble();
            scan.nextLine();
            System.out.println("How many units are in stock currently?");
            int newQuantity = scan.nextInt();
            scan.nextLine();
            System.out.println("Enter the new description for the product: ");
            String description = scan.nextLine();
            int position = -1;
            for (int i = 0; i < productList.size(); i++) {
                if (name.equals(productList.get(i).getName())) {
                    position = i;
                }
            }
            if (position == -1) {
                System.out.println("Product doesn't exist");
            } else {
                productList.get(position).setPrice(newPrice);
                productList.get(position).setQuantity(newQuantity);
                productList.get(position).setDescription(description);
                System.out.println("Product edited");
            }
        }
        if (choice == 2) {
            try {
                System.out.println("Enter the name of the product:");
                String name = scan.nextLine();
                System.out.println("Enter the price of the product:");
                double price = scan.nextDouble();
                scan.nextLine();
                System.out.println("How many units would you like to add?");
                int quantity = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter product description");
                String description = scan.nextLine();

                Product product = new Product(name, price, quantity, 0, user.getName(), user.getStoreName(), description);
                productList.add(product);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
                e.printStackTrace();
            }
        }
        if (choice == 3) {
            System.out.println("Enter the name of the product you would like to delete:");
            String deletedProduct = scan.nextLine();
            int remTag = -1;
            for (int i = 0; i < productList.size(); i++) {
                if (deletedProduct.equals(productList.get(i).getName())) {
                    remTag = i;
                }
            }
            if (remTag >= 0) {
                productList.remove(productList.get(remTag));
            } else {

            }
        }
        //PLEASE HELP JAI I DO NOT KNOW WHAT IS GOING ON
        if (choice == 4) {
            //jai
            int userChoice;
            do {
                System.out.println("1. List of customers with the number of items that they have purchased.");
                System.out.println("2. List of products with the number of sales.");
                userChoice = scan.nextInt();
                scan.nextLine();
                if (userChoice == 1) {
                    //marketplace.statisticsOne();
                }
                if (userChoice == 2) {
                    //marketplace.statisticsTwo();
                }
            }
            while (userChoice != 1 || userChoice != 2);
        }
        if (choice == 5) {
            //implement code for showing stuff
        }
        // exit prompt
        System.out.println(EXIT);
        int response;
        do {
            response = scan.nextInt();
            scan.nextLine();
        } while (response != 0 && response != 1);
        if (response == 0) {
            return false;
        } else {
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        User user;
        Marketplace marketplace = new Marketplace();
        boolean loop = false;
        System.out.println(WELCOME);
        do {
            do {
                user = login(scan);
            } while (user.getName() == null);
            if (user.isBuyer()) {
                do {
                    loop = buyMenu(user, scan, marketplace);
                } while (loop);
            } else {
                do {
                    loop = sellMenu(user, scan, marketplace);
                } while (loop);
            }
            for (int i = 0; i < productList.size(); i++) {
                System.out.printf("%s, %f, %d, %d, %s, %s, %s\n", productList.get(i).getName(), productList.get(i).getPrice(), productList.get(i).getQuantity(), productList.get(i).getQuantitySold(), productList.get(i).getSeller(), productList.get(i).getStoreName(), productList.get(i).getDescription());
            }
            for (int i = 0; i < users.size(); i++) {
                System.out.printf("%s, %s, %s, %s\n", users.get(i).getName(), users.get(i).getPassword(), users.get(i).getStoreName(), users.get(i).getEmailAddress());
            }
        } while (true);
    }
}
