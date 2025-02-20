import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

            while (true) {






                //new thread for each user
                while (true) {

                    String actionString = null;
                    while (actionString == null) {
                        actionString = reader.readLine();


                    }


                    int clientAction = Integer.parseInt(actionString);
                    System.out.println(clientAction);
                    if (clientAction == 4) {

                        for (String inputUsername : userNames) {
                                User thisUser;
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
                                            System.out.println("The username or password is incorrect");
                                            //gui to error saying password doesn't match

                                    }
                                }
                                if (!(isFound)) {
                                    //needs to be displayed in gui
                                    System.out.println("User doesn't exist, try again");
                                }
                            }

                            //go through each element in user array list, if username matches with any check if password
                            //matches. if it does, then get all other info and create new user with user constructor






                        }
                    }else if (clientAction == 8) { //user wants to create product
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
                        System.out.println(productList.size());

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

                        Product product = new Product(null, 0.00, 0, 0, null, null, null);

                        //productCart.add(null);
                        //


                    } else if (clientAction == 34){//looping back
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
            }
        } catch (IOException e) {

        }
    }



}











//login method
//create new account method
//return if user is seller or buyer
//exit for client action == 0

/*
        public boolean createProduct(String productString) {
            //create new product with constructor
            try {
                //Product testProduct = new Product("test", 1, 1, 1, "userName", "storeName");
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        public boolean editProduct(String productString) {
            //edit product with method
            return true;
        }
        public boolean deleteProduct(String productString) {
            //delete product with method
            return true;
        }
        */



