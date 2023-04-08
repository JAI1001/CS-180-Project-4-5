import java.io.*;
import java.util.ArrayList;
/*
   Class for all users
   Made by Thomas, feel free to ask questions for clarification
   Shopping cart methods and fields made by Kuanyu Chen
*/


public class User {
    private String username;
    private String emailAddress;
    private String password;
    private boolean buyer; //a user can only be a buyer or a seller, not both
    private boolean seller; //a user can only be a buyer or a seller, not both

    //===============================================================
    //shopping cart fields
    //productCart is for Product names
    //quantity is for product quantities
    //
    private ArrayList<String> productCart = new ArrayList<>(); //ArrayList to store each product added to the shopping cart
    private ArrayList<Integer> quantity = new ArrayList<>(); //ArrayList to store quantities of each product
    // specific shopping cart fields by Kuanyu Chen
    //===============================================================

    public User(String username, String emailAddress, String password, boolean buyer) { //constructor for a new user - Thomas

        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        if (buyer) {
            this.buyer = true;
            this.seller = false;
        } else {
            this.buyer = false;
            this.seller = true;
        }
    }


        public User(String username) throws UserNotFoundException { //constructor to load already existing user - Thomas
        /*
        IMPORTANT: This constructor assumes that an existing user is expected to have been created with the username.
        If the username is not found in the file, a UserNotFoundException is thrown. This should be used upon loading
        the program during user login.
         */

        File f = new File("userInfo.txt"); //userInfo contains all users username, password, email, and buyer/seller role (comma separated
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) { //If the userInfo.txt file is not correctly formatted, this will probably break
                if (line.substring(0, line.indexOf(',')).equals(username)) { //if the username matches a username in userInfo file
                   /*
                   The username argument matches with an existing user, so set the password, email, and role to that user
                   BEGIN...
                    */
                    this.username = line.substring(0, line.indexOf(','));

                    line = line.substring(line.indexOf(',') + 1); //set line to start with password string;
                    this.password = line.substring(0, line.indexOf(',')); //set this password to password from file
                    line = line.substring(line.indexOf(',') + 1); //set line to start with email string
                    this.emailAddress = line.substring(0, line.indexOf(',')); //set this email to email from file
                    line = line.substring(line.indexOf(',') + 1); //set line to start with b or s for buyer or seller
                    if (line.equals("b")) { //if line from file ends with a 'b', set the user to be a buyer
                        this.buyer = true;
                        this.seller = false;
                    } else { //if line from file is not a 'b', it should be an 's' and the user should be a seller
                        this.buyer = false;
                        this.seller = true;
                    }
                   /*
                   ...END
                   We now have loaded the user and set this object to match it
                    */

                    bfr.close();
                    break; //get out of while loop going over lines because we found the line we want
                } else {
                    bfr.readLine();
                }
                bfr.close();
            }
            if (line == null) {
                //throw exception if there is no user with the username called in the constructor
                throw new UserNotFoundException("There is not an existing user with that username");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //getters below
    public String getName() {
        return username;
    }


    public String getEmailAddress() {
        return emailAddress;
    }


    public String getPassword() {
        return password;
    }
    public boolean isBuyer() {
        return buyer;
    }
    public boolean isSeller() {
        return seller;
    }


    //setters below
    public void setName(String username) {
        this.username = username;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }
    public void setSeller(boolean seller) {
        this.seller = seller;
    }
    public boolean testPassword (String password) { //compare password argument to this password, return true if equal
        return (password.equals(this.password));
    }


    public void writeUser() { // -Thomas
        /*
        This method will either write a new user to the userInfo.txt file or, if the user already exists, will update
        the file to include the new user info and not the old user info
         */

        ArrayList<String> lines = new ArrayList<String>();
        File f = new File("userInfo.txt");
        boolean match = false;
        String matchLine = "";
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(',')).equalsIgnoreCase(username)) {
                    matchLine = line;
                    match = true;
                    break;
                }
                lines.add(line);
                line = bfr.readLine();
            }
            if (match) {
                /*
                If there is a match (the user exists already), we will write the updated user to a blank file and then
                re-write all of the other lines
                 */
                line = bfr.readLine();
                while (line != null) { //read in the rest of the lines
                    lines.add(line);
                    line = bfr.readLine();
                }
                bfr.close();

                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(toString()); //first write the updated user to the file
                for (String s : lines) {
                    pw.println(s); //write the rest of the lines of the file back in, completely unchanged
                }
                pw.close();
            } else {
                /*
                If the user does not already exist, we can simply append the string of the product to the file
                 */
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(toString()); //append the new product
                pw.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //===============================================================
    //seller methods - Thomas
    public void addProduct(Product product) {
        /*
        This method will add products to the productList file. If the seller wants to add a new product, a new product
        string will be added to a new line in the file. If the product that the seller wants to add already exists,
        the quantity of the existing product will go up and no other changes will be made to the product.
         */

        ArrayList<String> lines = new ArrayList<String>(); //will contain all non-matching lines
        File f = new File("productList.txt");
        boolean match = false;
        String matchLine = "";
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(',')).equalsIgnoreCase(product.getName())) {
                    matchLine = line;
                    match = true;
                    break;
                }
                lines.add(line);
                line = bfr.readLine();
            }
            if (match) {
                /*
                If there is a match (the product exists already), read the rest of the lines in and then get the
                quantity of the already existing product. Then, add the new quantity to the already existing quantity
                and re-write the productList.txt file to include the product with updated quantity and the rest
                of the lines.
                 */
                line = bfr.readLine();
                while (line != null) { //read in the rest of the lines
                    lines.add(line);
                    line = bfr.readLine();
                }

                matchLine = matchLine.substring(matchLine.indexOf(',') + 1);
                int quantity = Integer.parseInt(matchLine.substring(0, matchLine.indexOf(','))); //parse the quantity
                product.setQuantity(product.getQuantity() + quantity); //set the products quantity to add the existing quantity
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(product.toString()); //first write the updated product to the file
                for (String s : lines) {
                    pw.println(s); //write the rest of the lines of the file back in, completely unchanged
                }
            } else {
                /*
                If the product does not already exist, we can simply append the string of the product to the file
                 */
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(product.toString()); //append the new product
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //===============================================================
    //Make sure you read the stuff below - Kuanyu Chen

    //Don't use the set methods for shopping cart unless necessary. The 2 arraylists are supposed to have
    //the same size. Setting productCarts and quantity to different sizes will lead to a NullPointerException error
    //since the shopping methods ASSUMES the Array lengths to be the same.
    //
    //Shopping cart methods guide
    //
    //The addToCart method simply add the product and its quantity from the arraylists
    //
    //The removeFromCart method removes the product and substract the quantity from the arraylists
    //
    //The loadCartInfo MUST BE USED since we want the shopping cart info to be saved and accessed
    //
    //The writeCartInfo MUST BE USED since it saves the final shopping cart to the file
    //
    //The addToCart and removeFromCart takes 3 parameters, the name(This is currently useless, delete if you want :) ),
    //the product name(name of the product <String>), and the quantity(amount of same product in cart <int>)
    //
    //The loadCartInfo and writeCartInfo takes no parameters
    //
    //All shopping cart methods except the get methods do not return anything

    public ArrayList<Integer> getQuantity() {                // 2 get methods in case needed
        return quantity;
    }

    public ArrayList<String> getProductCart() {
        return productCart;
    }

    public void setProductCart(ArrayList<String> productCart) {    // 2 set methods in case needed
        this.productCart = productCart;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

    public void addToCart(String name , String product , int quantity) {
        for (int i = 0; i < productCart.size(); i++) {    //this loop checks if the Product to add already exists in the shopping cart
            if (product.equals(productCart.get(i))) {
                this.quantity.set(i , this.quantity.get(i) + quantity);
                return;
            }
        }
        productCart.add(product); //add new product to array with new quantity value if not existing in the cart
        this.quantity.add(quantity);
    }

    public void removeFromCart(String name , String product , int quantity) {
        for (int i = 0; i < productCart.size(); i++) {    //this loop checks if the Product to add already exists in the shopping cart
            if (product.equals(productCart.get(i))) {
                this.quantity.set(i, this.quantity.get(i) - quantity);

            }
        }
        for (int i = 0; i < this.quantity.size(); i++) { //remove the product from cart if there is no quantity
            if (this.quantity.get(i) <= 0) {
                this.productCart.remove(i);
            }
        }
    }


    public void loadCartInfo(String username) {
        this.productCart = new ArrayList<>(); //reset the program data
        this.quantity = new ArrayList<>();  //reset the program data
        try {
            BufferedReader cartLoader = new BufferedReader(new FileReader(new File(username + "Cart.txt")));  //Summons a new buffered reader to read the Shopping cart info

            while (cartLoader.ready()) { //loads in the existing cart info to the program list productCart and quantity
                String[] data = cartLoader.readLine().split(";");
                this.productCart.add(data[0]);
                this.quantity.add(Integer.parseInt(data[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ensure that you enter the correct file name");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file");
            e.printStackTrace();
        }
    }

    public void writeCardInfo(String username) {
        ArrayList<String> userInfo = new ArrayList<>(); //this array will be used to store the info of user when writing
        try {
            BufferedWriter cartWriter = new BufferedWriter(new FileWriter(username + "Cart.txt"));
            for (int i = 0; i < productCart.size(); i++) {
                cartWriter.write(productCart.get(i) + ";" + quantity.get(i)); //loads the product cart info into the file by format [product name];[quantity]
                cartWriter.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ensure that you enter the correct file name");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file");
            e.printStackTrace();
        }
    }

    // specific shopping cart methods by Kuanyu Chen
    //===============================================================






    @Override
    public String toString() { //puts user class object into string of the format that userInfo.txt is in - Thomas
        String role;
        if (buyer) {
            role = "b";
        } else {
            role = "s";
        }
        String s = String.format("%s,%s,%s,%s",
                username, password,emailAddress, role);
        return s;
    }

}
