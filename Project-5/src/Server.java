import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.ArrayList;





public class Server implements Runnable {

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
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> productList = new ArrayList<Product>();
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

            User uUser = new User(null, null, null, false, null);

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
                        users.add(uUser);
                    }
                }
                if (clientAction == 3) {
                    System.out.println("We are in three");
                    System.out.println("We are reading the store name, and printing after read: ");
                    String uStoreName = reader.readLine();
                    System.out.println(uStoreName);
                    uUser.setStoreName(uStoreName);
                    users.add(uUser);

                }
                if (clientAction == 4) {
                    System.out.println("We are in four");
                    String username = reader.readLine();
                    String password = reader.readLine();
                    System.out.println("Users size: " + users.size());
                    boolean found = false;
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
                } else if (clientAction == 8) { //user wants to create product
                    System.out.println("process for creating a product as a seller");
                    String productName = reader.readLine();
                    System.out.println("Product Name: " + productName);
                    double price = Double.parseDouble(reader.readLine());
                    System.out.println("Price: " + price);
                    int amount = Integer.parseInt(reader.readLine());
                    System.out.println("Amount: " + amount);
                    String description = reader.readLine();
                    System.out.println("Description: " + description);

                    //synchronized block accessing/creating a product

                    //actually create the product and put it in productList ArrayList
                    Product product = new Product(productName, price, amount, 0, null, null, description);
                    productList.add(product);
                    //System.out.println(productList.size());

                    //process for creating a product

                    writer.println("success");
                    writer.flush();
                } else if (clientAction == 9) { //user wants to edit product
                    System.out.println("process for editing a product");
                    String productName = reader.readLine();
                    System.out.println("Product name: " + productName);
                    Product product = new Product(productName, 0.00, 0, 0, null, null, null);
                    int index = productList.indexOf(product);
                    if (index == -1) { //if the product name doesn't exist
                        writer.println("Product does not exist!");
                    } else {
                        writer.println("success");
                    }


                    writer.flush();
                } else if (clientAction == 10) {
                    System.out.println("process for deleting a product");
                    String productName = reader.readLine();
                    System.out.println("Delete product: " + productName);
                    //deletes from arrayList
                    Product product = new Product(productName, 0.00, 0, 0, null, null, null);
                    productList.remove(product);
                    System.out.println(productList.size());

                    //process for deleting product

                    writer.println("success");
                    writer.flush();
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
                    Product product = new Product(newProductname, 0.00, 0, 0, null, null, null);
                    Product newProduct = new Product(newProductname, newPrice, newAmount, 0, null, null, newDescription);
                    productList.remove(product);
                    productList.add(newProduct);
                    System.out.println(productList.size());


                    //we have to get 9 to go to 14 automatically

                } else if (clientAction == 23) { //searches product list

                    String productName = reader.readLine();
                    Product product = new Product(productName, 0.00, 0, 0, null, null, null);
                    int index = productList.indexOf(product);
                    if (index == -1) { //if the product name doesn't exist
                        writer.println("Product does not exist!");
                    } else {
                        writer.println("success");
                    }


//
                } else if (clientAction == 25) {//store list
                    oos.writeObject(productStoreName);


                    oos.flush();

                    oos.close();
                    socket.close();


                    //


                } else if (clientAction == 26) { // show product list
                    oos.writeObject(productList);

                    oos.flush();

                    oos.close();
                    socket.close();


                } else if (clientAction == 27) { //Product name with product details shown, add product to cart array list function
                    //being buggy
                    //create new product
                    //deleted

                    Product product = new Product(null, 0.00, 0, 0, null, null, null);

                    //productCart.add(null);
                    //


                } else if (clientAction == 34) {//looping back
                    //will add once this is figured out


                } else if (clientAction == 35) {//clearing cart
                    productHistory.add(String.valueOf(productCart));
                    productCart.clear();
                    writer.println("success");


                } else if (clientAction == 33) { //cart
                    oos.writeObject(productCart);//writes

                    //for(int i = 0; i < productStoreName.size(); i++){//goes through everything in array list
                    //   productStoreName.get(i);//gets the index

                    // }
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









