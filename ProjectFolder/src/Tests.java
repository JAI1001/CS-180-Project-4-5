import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class Tests { //written by Jane
    @Test

    public void TestWriteUser() throws UserNotFoundException {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        user.writeUser();
        User otherUser = new User("user1");
        Assert.assertEquals(otherUser.getName(), user.getName());
        Assert.assertEquals(otherUser.getEmailAddress(), user.getEmailAddress());
        Assert.assertEquals(otherUser.getPassword(), user.getPassword());
        Assert.assertEquals(otherUser.isBuyer(), user.isBuyer());
        try {
            User user3 = new User("user3");
            throw new RuntimeException("Overloaded constructor didn't throw UserNotFoundException");
        } catch (UserNotFoundException e) {

        }

    }

    @Test
    public void TestAddProduct() {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addProduct(apples);
        File f = new File("productList.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            Assert.assertEquals(apples.toString(), line);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test

    public void TestAddToCart() {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        Assert.assertEquals(user.getProductCart().get(0), "Apples");
        Assert.assertEquals(5, user.getQuantity().get(0).intValue());
        user.addToCart("Apples", apples, 5);
        Assert.assertEquals(10, user.getQuantity().get(0).intValue());

    }


    @Test
    public void TestRemoveFromCart() {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        user.removeFromCart("Apples", apples, 4);
        Assert.assertEquals(1, user.getQuantity().get(0).intValue());


    }

    @Test
    public void TestLoadStoreCartData() throws UserNotFoundException {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        user.storeCartData("user1");
        user.writeUser();
        User otherUser = new User("user1");
        otherUser.loadCartData("user1");
        Assert.assertEquals(user.getProductCart().get(0), "Apples");


    }

    @Test
    public void TestAddProductHistory() throws IOException {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        user.storeCartData("user1");
        user.buyCart("user1");
        user.storeCartData("user1");
        user.addProductHistory("user1", "Apples", 5);
    }


    @Test
    public void TestBuyCart() {
        //didn't empty the cart
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        user.storeCartData("user1");
        user.buyCart("user1");
        user.storeCartData("user1");
        Assert.assertEquals(new ArrayList<>(), user.getProductCart());
    }


    @Test
    public void TestEditProduct() {
        //file is not writing
        User user = new User("seller1", "user1@gmail.com", "acbd123", false);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.importFile("productList.txt", "Fruit Store");
        Product applesTwo = new Product("Apples", 3.00, 10, 3, "seller1", "FruitStore");
        Marketplace.editProduct(applesTwo);


    }

    @Test
    public void TestDeleteProduct() throws IOException {
        User user = new User("seller1", "user1@gmail.com", "acbd123", false);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.importFile("productList.txt", "Fruit Store");
        Marketplace.deleteProduct("Apples");
        File f = new File("productList.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);


        Assert.assertEquals(null, bfr.readLine());
        //works
    }

    //user exists
    @Test
    public void TestUserExists() {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        user.writeUser();
        Assert.assertEquals(true, user.userExists("user1"));
        //works

    }


    //import file
    @Test
    public void TestAddStore() {
        User user = new User("user1", "user1@gmail.com", "acbd123", false);
        user.addStore("Fruit Store");
        File f = new File("sellerStores.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            Assert.assertTrue(line.contains("Fruit Store"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //getstorename
    @Test
    public void TestStoreExists() {
        User user = new User("user1", "user1@gmail.com", "acbd123", false);
        user.addStore("Fruit Store");
        Assert.assertTrue(user.doesStoreExist("Fruit Store"));
        //works
    }

    @Test
    public void TestGetStoreName() throws UserNotFoundException {
        User user = new User("user1", "user1@gmail.com", "acbd123", false);
        user.addStore("Fruit Store");
        Assert.assertEquals(user.getStoreName(), "Fruit Store");
    }

    @Test
    //toString
    //works
    public void TestToString() {
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        Assert.assertEquals("Apples,10,5.00,3,seller1,Fruit Store", apples.toString());
    }

}
