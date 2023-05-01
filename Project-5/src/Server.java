import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECField;
import java.sql.SQLOutput;
import java.util.ArrayList;





public class Server extends Thread implements Runnable {
    private static final Object PRODUCT_GATEKEEPER = new Object();
    private static final Object USER_GATEKEEPER = new Object();

    Socket socket;
    //Arraylist product list
    ArrayList<String> productQuantitySold = new ArrayList<String>();
    ArrayList<String> productStoreName = new ArrayList<String>();
    ArrayList<String> customerName = new ArrayList<String>();
    ArrayList<String> qtyPurchased = new ArrayList<String>();
    ArrayList<String> storeListOne = new ArrayList<String>();
    ArrayList<String> storedInfo = new ArrayList<>(); //ArrayList to store file info
    static ArrayList<String> userNames = new ArrayList<>();
    static ArrayList<ArrayList<String>> productCart = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<Integer>> quantities = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> totalQuantity = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Product> productList = new ArrayList<Product>();
    ArrayList<String> productHistory = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
    }
    public static void clearCart(String userName) {
        for (int i = 0; i < userNames.size(); i++) {
            if (userName.equals(userNames.get(i))) {
                productCart.get(i).clear();
                quantities.get(i).clear();
            }
        }
    }

    /*
    For this project, the server is always waiting for an input from the client. Depending on what the client gives
    the server, the server will process the info and give back to the client what it needs.
    For example, if the server
    gets a string saying that it is creating a new product, it should know to do the processing for creating a new
    product and telling the client that everything went through well.
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(4242);


        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            new Thread(server).start();
        }

    }


    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.flush();

            User uUser = new User(null, null, null, false, null,0);
            Product product = new Product(null, 0.00, 0, 0, null, null, null);


            //new thread for each user
            while (true) {
                System.out.println("Beginning Loop: ");
                System.out.println("Current users array list size: " + users.size());

                String actionString = null;
                while (actionString == null) {
                    actionString = reader.readLine();
                }
                System.out.println("Got first string (action string): " + actionString);
                int clientAction = Integer.parseInt(actionString);
                System.out.println("Action string is now client action int: " + clientAction);
                if (clientAction == 2) {
                    System.out.println("We are in 2");
                    System.out.println("Here we read the user info. Reading: ");
                    String uName = reader.readLine();
                    String uPassword = reader.readLine();
                    String uEmail = reader.readLine();
                    String uTypeString = reader.readLine();
                    boolean uType;
                    uType = uTypeString.equalsIgnoreCase("true");
                    System.out.println("Read user info: " + uName + uEmail + uPassword + uType);
                    uUser.setName(uName);
                    uUser.setPassword(uPassword);
                    uUser.setEmailAddress(uEmail);
                    uUser.setBuyer(uType);
                    System.out.println("We have now read the object.");
                    if (uUser.isBuyer()) {
                        synchronized (USER_GATEKEEPER) {
                            users.add(uUser);
                        }


                    }
                }
                if (clientAction == 3){
                    System.out.println("We are in three");
                    System.out.println("We are reading the store name, and printing after read: ");
                    String uStoreName = reader.readLine();
                    System.out.println(uStoreName);
                    uUser.setStoreName(uStoreName);
                    synchronized (USER_GATEKEEPER) {
                        users.add(uUser);
                    }



                }
                if (clientAction == 4) {
                    System.out.println("We are in four");
                    String username = reader.readLine();
                    String password = reader.readLine();
                    System.out.println("Users size: " + users.size());
                    boolean found = false;
                    synchronized (USER_GATEKEEPER) {
                        if (users.size() > 0) {
                            for (User u : users) {
                                if (u.getName().equalsIgnoreCase(username)) {
                                    System.out.println("Username exists...");
                                    if (u.getPassword().equals(password)) {
                                        System.out.println("And password matches!");
                                        found = true;
                                        writer.println("success");
                                        writer.flush();
                                        uUser.setName(u.getName());
                                        uUser.setPassword(u.getPassword());
                                        uUser.setEmailAddress(u.getEmailAddress());
                                        uUser.setBuyer(u.isBuyer());
                                        if ((u.isBuyer())) {
                                            writer.println("b");
                                            writer.flush();
                                        } else {
                                            writer.println("s");
                                            writer.flush();
                                            uUser.setStoreName(u.getStoreName());
                                        }

                                    }
                                }

                            }
                            if (!(found)) {
                                writer.println("unsuccess");
                                writer.println("");
                                writer.flush();
                            }
                        } else {
                            System.out.println("There are no users existant. Writing unsuccess");
                            writer.println("unsuccess");
                            writer.println("");
                            writer.flush();
                        }
                    }





/*
                        for (String inputUsername : userNames) {
                            User thisUser = null;
                            boolean isFound = false;
                            for (User element : users) {
                                inputUsername = reader.readLine();
                                String inputPassword = reader.readLine();
                                if (element.getName().equals(inputUsername)) {
                                    isFound = true;

                                    if (element.getPassword().equals(inputPassword)) {
                                        String thisEmail = element.getEmailAddress();
                                        boolean thisBuyer = element.isBuyer();
                                        thisUser = new User(element.getName(), element.getPassword(), element.getEmailAddress(), element.isBuyer(), "null");
                                    } else {
                                        writer.println("unsuccess");
                                        //gui to error saying password doesn't match

                                    }
                                }
                                if (!(isFound)) {
                                    //needs to be displayed in gui
                                    writer.println("unsuccess");
                                } else {
                                    writer.println("success");
                                }
                            }

                            try {
                                if (thisUser.isBuyer()) {
                                    writer.println("b");
                                } else  {
                                    writer.println("s");
                                }
                            } catch (Exception e) {
                                writer.println("s");
                            }




                            //go through each element in user array list, if username matches with any check if password
                            //matches. if it does, then get all other info and create new user with user constructor






                        }

 */
                }else if (clientAction == 8) {
                    System.out.println("process for creating a product as a seller");
                    String productName = reader.readLine();
                    System.out.println("Product Name: " + productName);
                    double price = Double.parseDouble(reader.readLine());
                    System.out.println("Price: " + price);
                    int amount = Integer.parseInt(reader.readLine());
                    System.out.println("Amount: " + amount);
                    String description = reader.readLine();
                    System.out.println("Description: " + description);


                    //actually create the product and put it in productList ArrayList
                    product = new Product(productName, price, amount, 0, null, uUser.getStoreName(), description);
                    synchronized (PRODUCT_GATEKEEPER) {
                        productList.add(product);
                    }


                    System.out.println("Created product added to product list. Showing size: ");
                    System.out.println(productList.size());

                    //process for creating a product

                    writer.println("success");
                    writer.flush();

                }else if (clientAction == 9) { //user wants to edit product
                    System.out.println("process for editing a product");
                    String productName = reader.readLine();
                    System.out.println("Product name: " + productName);
                    boolean found = false;
                    synchronized (PRODUCT_GATEKEEPER) {
                        for (Product p : productList) {
                            if (p.getName().equalsIgnoreCase(productName)) {
                                System.out.println("There is a product that matches, printing success");
                                writer.println("success");
                                writer.flush();
                                found = true;
                            }
                        }
                    }


                    if (!(found)) {
                        System.out.println("No product that matches, sending unsuccess");
                        writer.println("unsuccess");
                        writer.flush();
                    }
                } else if (clientAction == 10) {
                    System.out.println("process for deleting a product");
                    String productName = reader.readLine();
                    System.out.println("Delete product: " + productName);
                    //deletes from arrayList
                    boolean found = false;
                    int i = 0;
                    synchronized (PRODUCT_GATEKEEPER) {
                        for (Product p : productList) {
                            if (p.getName().equalsIgnoreCase(productName)) {
                                found = true;
                                writer.println("success");
                                writer.flush();
                            }
                            i++;
                        }
                    }




                    if (!(found)) {
                        writer.println("unsuccess");
                        writer.flush();
                    } else {
                        synchronized (PRODUCT_GATEKEEPER) {
                            productList.remove(i - 1);
                        }
                    }
                    System.out.println(productList.size());

                    //process for deleting product
                } else if (clientAction == 14) {
                    System.out.println("process for editing a product");
                    String newProductname = reader.readLine();
                    System.out.println("New product name: " + newProductname);
                    Double newPrice = Double.parseDouble(reader.readLine());
                    System.out.println("New double: " + newPrice);
                    int newAmount = Integer.parseInt(reader.readLine());
                    System.out.println("New amount: " + newAmount);
                    String newDescription = reader.readLine();
                    System.out.println("New string: " + newDescription);
                    //edits as well
                    synchronized (PRODUCT_GATEKEEPER) {
                        for (Product p : productList) {
                            if (p.getName().equalsIgnoreCase(newProductname)) {
                                p.setPrice(newPrice);
                                p.setQuantity(newAmount);
                                p.setDescription(newDescription);
                            }
                        }
                    }
                    System.out.println("Product list size: " + productList.size());



                    //we have to get 9 to go to 14 automatically

                } else if (clientAction == 23) {


                    String pName = reader.readLine();
                    synchronized (PRODUCT_GATEKEEPER){
                        for (Product p:productList){
                            if (p.getName().equalsIgnoreCase(pName)){
                                writer.println("success");
                                writer.flush();
                                writer.println(p.getName());
                                writer.println(p.getPrice());
                                writer.println(p.getQuantity());
                                writer.println(p.getDescription());
                                writer.flush();
                            }
                            else {
                                writer.println("unsuccess");
                                writer.flush();
                            }
                        }
                    }




                }else if (clientAction == 25) {


                    String pName = reader.readLine();
                    synchronized (PRODUCT_GATEKEEPER){
                        for (Product p:productList){
                            if (p.getName().equalsIgnoreCase(pName)){
                                writer.println("success");
                                writer.flush();
                                writer.println(p.getName());
                                writer.println(p.getPrice());
                                writer.println(p.getQuantity());
                                writer.println(p.getDescription());
                                writer.flush();
                            }
                            else {
                                writer.println("unsuccess");
                                writer.flush();
                            }
                        }
                    }


                } else if (clientAction == 22) { // show product list
                    writer.println(productList.size());
                    writer.flush();
                    for (int i=0;i<productList.size();i++){
                        System.out.println(productList.get(i));
                        writer.println(productList.get(i));
                        writer.flush();
                    }
                    //oos.writeObject(productList);
                    //oos.close();
                    //oos.flush();



                } else if (clientAction == 26) { //Product name with product details shown, add product to cart array list function
                    //being buggy
                    //create new product
                    //deleted

                    String productName = reader.readLine();
                    for (Product p : productList) {
                        if (p.getName().equalsIgnoreCase(productName)) {
                            writer.println(productName);
                            writer.println(product.getPrice());
                            writer.println(product.getQuantity());
                            writer.println(product.getDescription());
                        }
                    }



                    //productCart.add(null);
                    //


                }  else if(clientAction == 35){
                    for (int i = 0; i < productCart.size(); i++) {
                            productCart.get(i).clear();
                            quantities.get(i).clear();
                        
                    }
                    writer.println("success");

                }else if (clientAction == 32) {//clearing cart
                    productHistory.add(String.valueOf(productCart));
                    for (int i = 0; i < productCart.size(); i++) {
                            productCart.get(i).clear();
                            quantities.get(i).clear();
                    }
                    writer.println("success");


                } else if (clientAction == 31) { //cart
                            writer.println(productList);
                    oos.flush();

                    oos.close();
                    socket.close();


//
//
//                }
                }


            


            }

        } catch (IOException e) {

        }
    }
}
