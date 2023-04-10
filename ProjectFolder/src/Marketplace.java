import java.io.*;
import java.util.*;
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

   ArrayList<String> productName=new ArrayList<String>();
   ArrayList<String> productQuantitySold=new ArrayList<String>();
   ArrayList<String> productStoreName=new ArrayList<String>();       
   ArrayList<String> customerName=new ArrayList<String>();
   ArrayList<String> qtyPurchased=new ArrayList<String>();
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
    
    
    public void statisticsOne(){
        try {
            FileReader fr = new FileReader("UserProductHistory.txt");
            BufferedReader bfr=new BufferedReader(fr);
            String line= bfr.readLine();
            while (line!=null){
                String[] customerData=line.split(", ");
                line= bfr.readLine();
                customerName.add(customerData[0]);
                qtyPurchased.add(customerData[1]);
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
    
    public static void editProduct(Product product) {
        ArrayList<String> lines = new ArrayList<String>();
        File f = new File("productList.txt");
        String line;
        boolean found;
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



    }
    
    public static void deleteProduct(Product product) {
        ArrayList<String> lines = new ArrayList<String>();
        File f = new File("productList.txt");
        String line;
        boolean found;
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
            for (String s : lines) {
                pw.println(s);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
