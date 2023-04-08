import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class Tests {
    @Test
    //didn't write file at all
    //overloaded constructor that reads file didnt throw exception when expected to
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
    //add to cart
    //added two diff apples into array list instead of updating quantity
    @Test

    public void TestAddToCart() {
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        Assert.assertEquals(user.getCart().get(0).getName(),"Apples");
        Assert.assertEquals(5, user.getQuantity().get(0).intValue());
        user.addToCart("Apples", apples, 5);
        Assert.assertEquals(10, user.getQuantity().get(0).intValue());

    }

    //remove from cart
    //didn't remove correctly
    @Test
    public void TestRemoveFromCart(){
        User user = new User("user1", "user1@gmail.com", "acbd123", true);
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        user.addToCart("Apples", apples, 5);
        user.removeFromCart("Apples", apples, 4);
        Assert.assertEquals(1, user.getQuantity().get(0).intValue());
    }

    @Test
    //toString
    //works
    public void TestToString(){
        Product apples = new Product("Apples", 5.00, 10, 3, "seller1", "Fruit Store");
        Assert.assertEquals("Apples,10,5.00,3,seller1,Fruit Store", apples.toString());
    }

}
