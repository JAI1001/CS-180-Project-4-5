1) Compile all .java files. The main method is in the 'Marketplace.java'. When this class is run the program will start.
2) Jane - Submitted Report on Brightspace. Jai - Submitted Vocareum workspace.
3) a) Marketplace.java - This class contains the main method and other methods that deal with running the program, getting user input, and creating objects/instances of other classes. Each method in this class was tested individually, and we worked together to try to debug the main method in the class. Since the main method is large and complicated, we ran through it multiple times and manually debugged everything that we could. This class relates to every other class because it contains the main method and thus needs to, either directly or indirectly, access all functionality.
   
   method login:
      Method to log user in. Recieves user input for username and password to check with the userInfo.txt file to see if user exists. There also is a choice to create new user, where a new user can be created and stored while being initalized in the file to store data.
      
   method statistics one, two, three, four:
      Created local Arraylists to store data for the changes in the productlist and to call specific data from the productlist and UserProductHistory for statistics.
      
   method orderHistory:
      Created method to display the purchase history of that customer
   
   method edit/delete product:
      Method to edit delete products in productlist for sellers in the market
   
   method StoreList/ChooseStore/Storeproductdisplay/storeProductChoose:
      Method to execute searching options going from store to specific item access.
      
   method keywordSearch/keywordProduct:
      Method to search via keyword, display products with keyword and choose from the products
      
   method buyMenu:
      Method to execute the code to run through the buying method by guiding the user through terminal UI with integer mapped choices. User will be able to choose product, add to cart, and purchase the products in the cart which will alter the productlist file.
      User will be also able to access the buyer statistics via options.

   b) User.java - This class deals with user objects. These objects can be either a buyer or the seller, and the methods within this class are meant to be used by one or the other. This class also includes shopping cart methods, as a shopping cart is something that can be accessed by a buyer/customer. This method deals a lot with file I/O, because this is where user persistance and shopping cart persistance is handled. As for other class interactions, it uses Product.java objects a lot, given that buyers can purchase product objects and sellers can create/edit/delete them. There are JUnit tests for the methods in this class
   The Shopping cart methods in the User class includes:

   addNewUserToCart(newUser) 

   Parameters:
   newUser - newly registered Username

   This method adds a new user to ShoppingCart.txt every time a new user registers. Because the loadCartData only loads info which exists in ShoppingCart.txt
	

   addToCart(username , product , quantity)
      The purpose of the addToCart method is to simply add a quantity of a product to a specific user’s shopping cart database and store it in the program (ArrayList storedInfo )

   Parameters:
      username - the name of the user who wants to add the product to their shopping cart
         product - the product which the user wants to add
         quantity - the number of products which the user wants to add

           The method will first find the user information from the Arraylist storedInfo using a for loop. Then, the user information will be splitted into a format of [username, product1;quantity, product2; quantity….., totalquantity]. The method will check if the product already exists in the information. If the product exists, it will only add the new  quantity to the quantity already in cart. If not, the method will add the product to the cart as well as the new quantity.


   loadCartData(username)

      Parameters:
         username - the username 

        The loadCartData will load in the info which already exist in the ShoppingCart.txt.
        it simply loads the file info into the storedInfo ArrayList.

   storeCartData(username)

      parameters:
         username - the username

      The storeCartData will apply the changes done in program to the storedInfo ArrayList and save the storedInfo back into the ShoppingCart.txt

   buyCart(username)

      Parameters:
         username - the username

      simply removes all the items in the ShoppingCart for that user

   addProductHistory(username , product , quantity)
      This methods is used before the buyCart method

      Parameters:
         username - the username for purchase history
         product - the product to add to history
         quantity - the quantity to add to the history

      This method simply handles the UserProductHistory.txt file. Adding new purchase history to it


c) Tests.java – This class has JUnits tests to test other classes/methods. Since this is the class that tests, it was manually checked to ensure that it works properly. 

d) Product.java – This class deals with product objects. The class is used a lot in the User.java class since buyers/sellers need to be able to access products and their info. There are a variety of methods that deal with product objects in this class that are used in the User.java class. Junit testing was done to make sure that methods in this class work as intended. 

e) UserNotFoundException.java – This class is an exception that can be thrown if a user is not found in any method where a user is expected to be found. It should be used with a string message that is common to the general Exception class (super.message). 
