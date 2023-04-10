import java.io.*;
import java.util.*;
import java.util.ArrayList;
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
    private static final String SUCHUSER = "Username already exists. Choose new name.";
    private static final String SETPASS = "Set password: ";
    private static final String SETEMAIL = "Set email: ";
    private static final String PASS = "Enter password";

    private static final String WRONGPASS = "Wrong password !";

    // Strings for buyer menu
    private static final String BUYMENU = """
1. Enter product search list
2. Purchase items in Shopping Cart
3. Show purchase history
4. List of stores by products purchased
5. List of stores by products sold
6. Exit
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
3. Show statistics
""";
    private static final String EXIT = """
Do you want to exit?
0. Yes
1. No
""";
    private static final String ADDCART = "Press 1 to add to cart or 0 to start over";
    private static final String HOWMUCH = "How much products do you want to purchase?";
    private static final String NOTENOUGH = "There are not enough products";
    private static final String ENTERKEY = "Enter Keyword";

    ArrayList<String> productName=new ArrayList<String>();
    ArrayList<String> productQuantitySold=new ArrayList<String>();
    ArrayList<String> productStoreName=new ArrayList<String>();
    ArrayList<String> customerName=new ArrayList<String>();
    ArrayList<String> qtyPurchased=new ArrayList<String>();
    ArrayList<String> storeListOne=new ArrayList<String>();
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


    public void statisticsOne(){
        try {
            FileReader fr = new FileReader("UserProductHistory.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null){
                customerName.add(line.substring(0,line.indexOf(";")));
                qtyPurchased.add(line.substring(line.indexOf(";")+2,line.indexOf(",")));
                line= bfr.readLine();
            }
            for (int i=0;i<customerName.size();i++){
                System.out.println(customerName.get(i)+" - "+qtyPurchased.get(i));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void statisticsTwo(){
        try {
            FileReader fr = new FileReader("productList.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            int qty=0;
            while (line != null) {
                String[] productsList = line.split(",");
                line = bfr.readLine();
                productName.add(productsList[0]);
                productQuantitySold.add(productsList[3]);
            }

            System.out.println("Product Name - Quantity Sold");

            ArrayList<String> productList = new ArrayList<String>();  // creating a new arraylist storelist with unique store names
            for (int i = 0; i < productName.size(); i++) {
                if (!(productList.contains(productName.get(i)))) {
                    productList.add(productName.get(i));
                }
            }
            qty = 0; // declaring quantity field and setting it to 0

            String[][] statisticsOne = new String[productList.size()][2]; // creating a 2d array named statistics
            line = bfr.readLine();
            for (int i = 0; i < productList.size(); i++) {
                statisticsOne[i][0] = productList.get(i); // adding the the unique store names to the first columns of all rows
                for (int j = 0; j < productName.size(); j++) {
                    if (productList.get(i).equals(productName.get(j))) {
                        qty = qty + Integer.parseInt(productQuantitySold.get(j));
                    }
                }
                String qtystring = String.valueOf(qty);
                statisticsOne[i][1] = qtystring;  // adding the the quantity of products sold of that store to the second columns of the respective product tows.
                qty = 0;
                System.out.println(statisticsOne[i][0]+" - "+statisticsOne[i][1]);

            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void statisticsThree(){
        try {
            int qtyThree=0;
            FileReader fr = new FileReader("productList.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] productsList = line.split(",");
                line = bfr.readLine();
                productStoreName.add(productsList[5]);
                productQuantitySold.add(productsList[3]);
            }
            ArrayList<String> storelist = new ArrayList<String>();  // creating a new arraylist storelist with unique store names
            for (int i = 0; i < productStoreName.size(); i++) {
                if (!(storelist.contains(productStoreName.get(i)))) {
                    storelist.add(productStoreName.get(i));
                }
            }
            qtyThree = 0; // declaring quantity field and setting it to 0
            System.out.println("Store Name - Quantity Sold");

            String[][] statistics = new String[storelist.size()][2]; // creating a 2d array named statistics
            line = bfr.readLine();
            for (int i = 0; i < storelist.size(); i++) {
                statistics[i][0] = storelist.get(i); // adding the the unique store names to the first columns of all rows
                for (int j = 0; j < productStoreName.size(); j++) {
                    if (storelist.get(i).equals(productStoreName.get(j))) {
                        qtyThree = qtyThree + Integer.parseInt(productQuantitySold.get(j));
                    }
                }
                String qtystring = String.valueOf(qtyThree);
                statistics[i][1] = qtystring;  // adding the the quantity of products sold of that store to the second columns of the respective product tows.
                qtyThree = 0;
                System.out.println(statistics[i][0]+" - "+statistics[i][1]);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void statisticsFour(User user){
        String name = user.getName();
        try {
            System.out.println("Stores List - Products Purchased");
            FileReader fr = new FileReader("UserProductHistory.txt");
            BufferedReader bfr=new BufferedReader(fr);
            FileReader f = new FileReader("productList.txt");
            BufferedReader bf=new BufferedReader(f);
            String line= bfr.readLine();
            String lineOne=bf.readLine();
            productStoreName=new ArrayList<String>();
            while (lineOne!=null){
                String[] productsList=lineOne.split(",");
                lineOne = bf.readLine();
                productName.add(productsList[0]);
                productStoreName.add(productsList[5]);
            }
            while (line!=null){
                if (name.equals(line.substring(0,line.indexOf(";")))){
                    String products=line.substring(line.indexOf(",")+2);
                    //System.out.println(products);
                    String[] productsOne = products.split(", ");
                    for (int i=0;i<productsOne.length;i++){
                        storeListOne.add(productsOne[i]);
                        //System.out.println(storeListOne.get(i));
                        if (productName.contains(storeListOne.get(i))){
                            int k=productName.indexOf(storeListOne.get(i));

                            System.out.print(productStoreName.get(k)+" - ");
                            System.out.print(storeListOne.get(i));
                        }
                        System.out.println();
                    }
                    //storeListOne.add(productsOne[0]);
                    break;
                }
                line=bfr.readLine();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean editProduct(Product product) {
        ArrayList<String> lines = new ArrayList<String>();
        File f = new File("productList.txt");
        String line;
        boolean found = false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(",")).equals(product.getName())) {
                    found = true;
                } else {
                    lines.add(line);
                }
                line = bfr.readLine();
            }
            bfr.close();

            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(product.toString());
            for (String s : lines) {
                pw.println(s);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return found;
    }

    public static boolean deleteProduct(String productName) {
        ArrayList<String> lines = new ArrayList<String>();
        File f = new File("productList.txt");
        String line;
        boolean found = false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(",")).equals(productName)) {
                    found = true;
                } else {
                    lines.add(line);
                }
                line = bfr.readLine();
            }
            bfr.close();

            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);
            for (String s : lines) {
                pw.println(s);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }


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
                        if (!users.testPassword(pass)) {
                            System.out.println(WRONGPASS);
                        }
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

                    users = new User(user, email, password, buyer);
                    users.writeUser();
                    users.addNewUserToCart(users.getName());
                    break;
                default:
                    System.out.println("Please enter valid option !\n");
            }
        } while (response != 1 && response != 2);
        return users;
    }
    public static boolean buyMenu(User user, Scanner scan, Marketplace marketplace) throws IOException {
        System.out.println(BUYMENU);
        boolean output = false;
        int response;
        do {
            response = scan.nextInt();
            scan.nextLine();
        } while (response != 1 && response != 2 && response != 3);
        if (response == 1) {
            System.out.println(SEARCH);
            do {
                response = scan.nextInt();
                scan.nextLine();
            } while (response != 1 && response != 2 && response != 0);
            if (response == 1) { // Search case with displaying all stores
                System.out.println(storeList());
                String store;
                Product product;
                do {
                    response = scan.nextInt();
                    scan.nextLine();
                    store = chooseStore(response);
                } while (store == null);
                System.out.println(storeProductDisplay(store));
                do {
                    response = scan.nextInt();
                    scan.nextLine();
                    product = storeProductChoose(store, response);
                } while (product == null);

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
                    user.addToCart(user.getName(), product, response);
                    user.storeCartData(user.getName());

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

            } else if (response == 2) { // search case with keywords
                String keyword = null;
                Product product;
                do {
                    System.out.println(ENTERKEY);
                    keyword = scan.nextLine();
                    System.out.println(keywordSearch(keyword));
                    if (keywordSearch(keyword) == "") {
                        System.out.println("no product with keyword");
                    }
                } while(keywordSearch(keyword) == "");
                do {
                    response = scan.nextInt();
                    scan.nextLine();
                    product = keywordProduct(response, keyword);
                } while (product == null);
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
                    user.addToCart(user.getName(), product, response);
                    user.storeCartData(user.getName());

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
            System.out.println(SHOPPING);
            do {
                response = scan.nextInt();
                scan.nextLine();
            } while (response != 1 && response != 2);
            if (response == 1) {
                user.addProductHistory(user.getName(), null, 0);
                user.buyCart(user.getName());
                user.addNewUserToCart(user.getName());
            } else {
                BufferedReader f = new BufferedReader(new FileReader("ShoppingCart.txt"));

                //user.removeFromCart(user.getName(), product, quantity);// Need work done here for removing
            }
        } else if (response == 3) {
            marketplace.statisticsFour(user);
        } else if (response == 4) {
            marketplace.statisticsTwo();
        } else if (response == 5) {
            marketplace.statisticsThree();
        } else if (response == 6) {
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

        return output;
    }
    public static String storeList() throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        int i = 1;
        while (product != null) {
            if (!list.contains(product.split(",")[5])) {
                list += Integer.toString(i) + ". " + product.split(",")[5] + "\n";
                i++;
            }
            product = f.readLine();
        }
        return list;
    }
    public static String chooseStore(int input) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        int i = 1;
        String store = null;
        while (product != null) {
            if (!list.contains(product.split(",")[5])) {
                list += Integer.toString(i) + ". " + product.split(",")[5] + "\n";
                if (i == input) {
                    store = product.split(",")[5];
                }
                i++;
            }
            product = f.readLine();
        }

        return store;
    }
    public static String storeProductDisplay(String storeName) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        int i = 1;
        while (product != null) {
            if (product.split(",")[5].equals(storeName)) {
                list += Integer.toString(i) + ". " + product.split(",")[0] + " " + product.split(",")[1] + " left in stock" + "\n"
                        + "$ " + product.split(",")[2] + "\n";
                i++;
            }
            product = f.readLine();
        }
        return list;
    }
    public static Product storeProductChoose(String storeName, int input) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        Product realProduct = null;
        int i = 1;
        while (product != null) {
            if (product.split(",")[5].equals(storeName)) {
                list += Integer.toString(i) + product.split(",")[0] + " " + product.split(",")[1] + "left in stock" + "\n"
                        + "$" + product.split(",")[2] + "\n";
                if (i == input) {
                    realProduct = new Product(product.split(",")[0], Double.parseDouble(product.split(",")[2]),
                            Integer.parseInt(product.split(",")[1]), Integer.parseInt(product.split(",")[3]), product.split(",")[4], product.split(",")[5]);
                }
                i++;
            }
            product = f.readLine();
        }
        return realProduct;
    }

    public static String keywordSearch(String keyword) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        int i = 1;
        while (product != null) {
            if (product.contains(keyword)) {
                list += Integer.toString(i) + ". "+ product.split(",")[0] + " " + product.split(",")[1] + " left in stock" + "\n"
                        + "$" + product.split(",")[2] + "\n";
                i++;
            }
            product = f.readLine();
        }
        return list;
    }
    public static Product keywordProduct(int input, String keyword) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("productList.txt"));
        String product = f.readLine();
        String list = "";
        Product realProduct = null;
        int i = 1;
        while (product != null) {
            if (product.contains(keyword)) {
                list += Integer.toString(i) + product.split(",")[0] + " " + product.split(",")[1] + "left in stock" + "\n"
                        + "$" + product.split(",")[2] + "\n";
                if (i == input) {
                    realProduct = new Product(product.split(",")[0], Float.parseFloat(product.split(",")[2]),
                            Integer.parseInt(product.split(",")[1]), Integer.parseInt(product.split(",")[3]), product.split(",")[4], product.split(",")[5]);
                }
                i++;
            }
            product = f.readLine();
        }
        return realProduct;
    }



    public static void main(String[] args) throws IOException, UserNotFoundException {
        Scanner scan = new Scanner(System.in);
        User user;
        Marketplace marketplace = new Marketplace();
        boolean loop = false;
        System.out.println(WELCOME);
        do {
            user = login(scan);
        } while (user.getName() == null);
        if(user.isBuyer()) {
            user.loadCartData(user.getName());
            do {
                loop = buyMenu(user, scan, marketplace);
            } while (loop);
        }
        if (user.isSeller()) {
            System.out.println("What is the name of your store?");
            String storeName = scan.nextLine();
            if (user.doesStoreExist(storeName)) {
                System.out.println("Store found.");
            } else {
                System.out.println("Creating store...");
            }

            System.out.println(SELLMENU);
            int choice = 0;
            while (choice != 5) {
                choice = scan.nextInt();
                scan.nextLine();
                if (choice == 1) {
                    System.out.println("Enter the name of the product you want to edit:");
                    String newName = scan.nextLine();
                    System.out.println("Enter the price of the product you want to edit:");
                    double newPrice = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("How many units are in stock currently?");
                    int newQuantity = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the number of units you have sold so far:");
                    int newSold = scan.nextInt();
                    scan.nextLine();

                    Product product = new Product(newName, newPrice, newQuantity, newSold, user.getName(), storeName);
                    if (!editProduct(product)) {
                        System.out.println("Product doesn't exist");
                    }

                    System.out.println("Product edited");
                    System.out.println("Would you like to quit?");


                }
                if (choice == 2) {
                    System.out.println("Enter the name of the product:");
                    String name = scan.nextLine();
                    System.out.println("Enter the price of the product:");
                    double price = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("How many units would you like to add?");
                    int quantity = scan.nextInt();
                    scan.nextLine();


                    Product product = new Product(name, price, quantity, 0, user.getName(), storeName);
                    user.addProduct(product);


                }
                if (choice == 3) {
                    System.out.println("Enter the name of the product you would like to delete:");
                    String deletedProduct = scan.nextLine();
                    if (!deleteProduct(deletedProduct)) {
                        System.out.println("This product doesn't exist.");
                    }
                }

                if (choice == 4) {
                    //jai

                }


            }
        }

    }
}
