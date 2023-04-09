import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class Tests {
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
        //test to see if the user will be correctly written to file
        //works

    }
    //add product

    @Test
    //didn't write file (will be fixed when jai adds the stuff)
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
        user.addToCart("Apples", "Apples", 5);
        Assert.assertEquals(user.getProductCart().get(0),"Apples");
        Assert.assertEquals(5, user.getQuantity().get(0).intValue());
        user.addToCart("Apples", "Apples", 5);
        Assert.assertEquals(10, user.getQuantity().get(0).intValue());

        //this tests to make sure that products are getting addede to the cart correctly, and if the product exists,
        // it adds to the quantity instead of adding two diff products
        // works

    }


    @Test
    public void TestRemoveFromCart(){
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", "Apples", 5);
        user.removeFromCart("Apples", "Apples", 4);
        Assert.assertEquals(1, user.getQuantity().get(0).intValue());

        //this tests to make sure things are removed correctly
        //works
    }
    //loadCartInfo test
    @Test
    public void TestLoadCartData() throws UserNotFoundException {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", "Apples", 5);
        user.storeCartData("user1");
        user.writeUser();
        User otherUser = new User("user1");
        otherUser.loadCartData("user1");
        Assert.assertEquals(user.getProductCart().get(0), "Apples");
        //test to make sure cart data is showing up correctly
        //works

    }



    @Test
    public void TestLogin(){

    }

    //login test
    //buyer test
    //seller test
    

    @Test
    //toString
    //works
    public void TestToString(){
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        Assert.assertEquals("Apples,10,5.00,3,seller1,Fruit Store", apples.toString());
    }

}

