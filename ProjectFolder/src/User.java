import java.awt.*;
import java.io.*;
import java.security.spec.ECField;
import java.util.ArrayList;
/*
   Class for all users
   Made by Thomas, feel free to ask questions for clarification
*/


public class User {
    private String username;
    private String emailAddress;
    private String password;
    private boolean buyer; //a user can only be a buyer or a seller, not both
    private boolean seller; //a user can only be a buyer or a seller, not both

    //===============================================================
    // shopping cart fields
    private ArrayList<String> productCart = new ArrayList<>(); //ArrayList to store each product added to the shopping cart
    private ArrayList<Integer> quantity = new ArrayList<>(); //ArrayList to store quantities of each product
    private ArrayList<String> storedInfo = new ArrayList<>(); //ArrayList to store file info
    int totalQuantity = 0;
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

    public static boolean userExists(String username) { //returns true if the username being passed in exists
        File f = new File("userInfo.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(',')).equals(username)) { //if the username matches a username in userInfo file
                    return true;
                }
                line = bfr.readLine();
            }
            if (line == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
                    line = bfr.readLine();
                }

            }
            if (line == null) {
                //throw exception if there is no user with the username called in the constructor
                throw new UserNotFoundException("There is not an existing user with that username");
            }
            bfr.close();
        } catch (IOException e) {
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
   
       public void addStore(String storeName) { //adds store name to store file
        File f = new File("sellerStores.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(username + "," + storeName);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStoreName() throws UserNotFoundException { //gets the name of the store for the seller
        try {
            File f = new File("sellerStores.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, line.indexOf(",")).equals(username)) {
                    line = line.substring(line.indexOf(",") + 1);
                    return line;
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException("There is no existing seller with that username.");
    }
   
       public void importFile(String fileName, String storeName) { //seller method to import CSV file with products
        ArrayList<String> lines = new ArrayList<String>();
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line =  bfr.readLine();
            while (line != null) {
                String pName = line.substring(0, line.indexOf("\t"));
                pName = pName.strip();
                line = line.substring(line.indexOf("\t") + 1);
                double pPrice = Double.parseDouble(line.substring(0, line.indexOf("\t")));
                line = line.substring(line.indexOf("\t") + 1);
                int pQuantity = Integer.parseInt(line.substring(0, line.indexOf("\t")));
                line = line.substring(line.indexOf("\t")+ 1);
                int pQuantitySold = Integer.parseInt(line);



                lines.add(pName + "," + pPrice + "," + pQuantity + "," + pQuantitySold + "," + username + "," + storeName);
                line = bfr.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //update to write lines from file into productList file
        f = new File("productList.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            for (String line : lines) {
                pw.println(line);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
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
                pw.close();
            } else {
                /*
                If the product does not already exist, we can simply append the string of the product to the file
                 */
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(product.toString()); //append the new product
                pw.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //===============================================================
    //shopping cart methods - Kuanyu
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void addNewUserToCart(String newUser) {
        try {
            storedInfo.clear();
            BufferedReader bfr = new BufferedReader(new FileReader(new File("ShoppingCart.txt")));
            while ((bfr.ready())) {
                storedInfo.add(bfr.readLine());
            }
            storedInfo.add(newUser + ", ");
            BufferedWriter bfw = new BufferedWriter(new FileWriter("ShoppingCart.txt"));
            for (int i = 0; i < storedInfo.size(); i++) {
                bfw.write(storedInfo.get(i));
                bfw.newLine();
            }
            bfr.close();
            bfw.close();
            storedInfo.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToCart(String username , Product product , int quantity) {
        product.setQuantity(product.getQuantity - quantity);
        for (int i = 0; i < productCart.size(); i++) {    //this loop checks if the Product to add already exists in the shopping cart
            if (product.equals(productCart.get(i))) {
                this.quantity.set(i , this.quantity.get(i) + quantity);
                return;
            }
        }
        productCart.add(product.getName()); //add new product to array with new quantity value if not existing in the cart
        this.quantity.add(quantity);
    }


    public void removeFromCart(String username , String product , int quantity) {
        product.setQuantity(product.getQuantity + quantity);
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

    public void loadCartData(String username) {
        try {
            storedInfo.clear();
            BufferedReader bfr = new BufferedReader(new FileReader(new File("ShoppingCart.txt")));
            while (bfr.ready()) {
                storedInfo.add(bfr.readLine());
            }
            bfr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Invalid Filename");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while reading the file");
            e.printStackTrace();
        }
        String targetData = "";
        for (int i = 0; i < storedInfo.size(); i++) {
            if (storedInfo.get(i).split(", ")[0].equals(username)) {
                targetData = storedInfo.get(i);
            }
        }
        if (targetData.equals("")) {
        }
        String[] data = targetData.split(", ");
        for (int i = 1; i <= data.length - 1; i++) {
            this.productCart.add(data[i].split("; ")[0]);
            this.quantity.add(Integer.parseInt(data[i].split("; ")[1]));
        }
    }

    //loads all the lines from ShoppingCart.txt to an Arraylist<String>
    public void storeCartData(String username) {
        for (int i = 0; i < quantity.size(); i++) {
            totalQuantity += quantity.get(i);
        }
        for (int i = 0; i < storedInfo.size(); i++) {
            if (storedInfo.get(i).split(", ")[0].equals(username)) { //check the username in the storedInfp
                String newTxt = username;
                for (int e = 0; e < productCart.size(); e++) {
                    newTxt += ", " + productCart.get(e) + "; " + quantity.get(e);
                }
                storedInfo.set(i , newTxt + ", " + totalQuantity);
            }
        }


        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("ShoppingCart.txt"));
            for (int i = 0; i < storedInfo.size(); i++) {
                bfw.write(storedInfo.get(i));
                bfw.newLine();
            }
            bfw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Filename");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while writing file");
            e.printStackTrace();
        }
    } //edits and store the new data back to the ShoppingCart.txt


    public void buyCart(String username) {
        try {
            storedInfo.clear();
            BufferedReader bfr = new BufferedReader(new FileReader(new File("ShoppingCart.txt")));
            while (bfr.ready()) {
                storedInfo.add(bfr.readLine());
            }
            for (int i = 0; i < storedInfo.size(); i++) {
                if (storedInfo.get(i).split(", ")[0].equals(username)) {
                    storedInfo.set(i , username + ", ");
                }
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter("ShoppingCart.txt"));
            for (int i = 0; i < storedInfo.size(); i++) {
                bfw.write(storedInfo.get(i));
                bfw.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductHistory(String username, String product, int quantity) { //use this before buyCart Method
        try {
            String checkedData = "";
            BufferedReader bfr = new BufferedReader(new FileReader(new File("UserProductHistory.txt")));
            while (bfr.ready()) {
                String data = bfr.readLine();
                if (data.split(", ")[0].equals(username)) {
                    checkedData = data;
                }
            }
            if (checkedData.equals("")) {
                checkedData = username + ", " + "0";
            }
            for (int i = 0; i < productCart.size(); i++) {
                if (!checkedData.contains(productCart.get(i))) {
                    int newQuantity = Integer.parseInt(checkedData.split(", ")[1]) + this.quantity.get(i);
                    checkedData = username + ", " + newQuantity + checkedData.split(", " , 2)[2];
                    checkedData += ", " + productCart.get(i);

                }
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter("UserProductHistory.txt"));
            bfr.reset();
            storedInfo.clear();
            while (bfr.ready()) {
                storedInfo.add(bfr.readLine());
            }
            for (int i = 0; i < storedInfo.size(); i++) {
                if (storedInfo.get(i).split(", ")[0].equals(username)) {
                    storedInfo.set(i, checkedData);
                }
            }
            for (int i = 0; i < storedInfo.size(); i++) {
                bfw.write(storedInfo.get(i));
                bfw.newLine();
            }

            bfw.close();
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
