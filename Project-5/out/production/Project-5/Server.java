import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server {

    /*
    For this project, the server is always waiting for an input from the client. Depending on what the client gives
    the server, the server will process the info and give back to the client what it needs.
    For example, if the server
    gets a string saying that it is creating a new product, it should know to do the processing for creating a new
    product and telling the client that everything went through well.
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {

            ServerSocket serverSocket = new ServerSocket(4242);
            Socket socket = serverSocket.accept();

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


            //new thread for each user
            while (true) {

                String actionString = null;
                while (actionString == null) {
                    actionString = reader.readLine();


                }


                int clientAction = Integer.parseInt(actionString);
                System.out.println(clientAction);
                if (clientAction == 8) { //user wants to create product
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
                    Product product = new Product(productName, price, amount, 0, null, null, description);
                    Marketplace.productList.add(product);
                    System.out.println(Marketplace.productList.size());

                    //process for creating a product

                    writer.println("success");
                    writer.flush();
                } else if (clientAction == 9) { //user wants to edit product
                    System.out.println("process for editing a product");
                    String productName = reader.readLine();
                    System.out.println("Product name: " + productName);
                    Product product = new Product(productName, 0.00, 0, 0, null, null, null);
                    int index = Marketplace.productList.indexOf(product);
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
                    Marketplace.productList.remove(product);
                    System.out.println(Marketplace.productList.size());

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
                    Marketplace.productList.remove(product);
                    Marketplace.productList.add(newProduct);
                    System.out.println(Marketplace.productList.size());

                    writer.println("success");
                    writer.flush();

                    //we have to get 9 to go to 14 automatically

                } else if (clientAction == 23) { //shows product list
                    for (Product product : Marketplace.productList) {
                        System.out.println(product);
                    }
//
                } else if (clientAction == 25) {
                    try (BufferedReader bfr = new BufferedReader(new FileReader("path/to/sellerStores.txt"))) {
                        String line;
                        while ((line = bfr.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }

                } else if (clientAction == 26) { // shows product list
                    try (BufferedReader bfr = new BufferedReader(new FileReader("path/to/productList.txt"))) {
                        String line;
                        while ((line = bfr.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());

                    }
                } else if (clientAction == 27) {
                    //is there an array list for this
                    //should we have this

                } else if (clientAction == 32) {//buys cart
                    Marketplace.clearCart("");
                    //add to array list once stat stuff is done

                } else if (clientAction == 33) {
                    for (ArrayList<String> cart : Marketplace.productCart) {
                        System.out.println(cart);
                    }

                } else if (clientAction == 35) {
                    //nothing to do here

                }
//
//
//                }
            }


        }


    }
}
