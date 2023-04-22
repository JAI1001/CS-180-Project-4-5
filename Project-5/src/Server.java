import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Search Server
 *
 * Server for search bar
 *
 * @author Thomas Birk, lab sec 07
 *
 * @version 04/05/2023
 *
 */

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

                int clientAction = Integer.parseInt(reader.readLine());
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

                    //process for creating a product

                    writer.println("success");
                    writer.flush();
                } else if (clientAction == 9) { //user wants to edit product
                    System.out.println("process for editing a product");
                    String productName = reader.readLine();
                    System.out.println("Product name: " + productName);

                    //process for editing product/gui here

                    writer.println("success");
                    writer.flush();
                } else if (clientAction == 10) {
                    System.out.println("process for deleting a product");
                    String productName = reader.readLine();
                    System.out.println("Delete product: " + productName);

                    //process for deleting product

                    writer.println("success");
                    writer.flush();
                }
            }




        }


    }

    public boolean createProduct(String productString) {
        //create new product with constructor
        try {
            Product testProduct = new Product("test", 1, 1, 1, "userName", "storeName");
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

}
