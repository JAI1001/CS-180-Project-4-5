# CS-180 (Project-4 & 5)

## The objective of this Project is to create an online marketplace(like Amazon). ##

### There will be total six classes (Market, Sellers, Customers, Files, Stastics, Shopping Cart), Market being the main class.

The implementations must have the following :-
1. Market

   - The marketplace will be a centralized page listing available products for purchase. 
    
          Products will include the following information: 
          
             a.  Name of the product.
          
             b.  The store selling the product. 
          
             c.  A description of the product.
            
             d.  The quantity available for purchase.
          
             e.  The price.
          
   - The marketplace listing page will show the store, product name, and price of the available goods. Customers can select a specific product to be taken  to that product's page, which will include a description and the quantity available. 
    
   - When items are purchased, the quantity available for all users decreases by the amount being purchased. 
    
    
2. Sellers

   - Sellers can create, edit, or delete products associated with their stores. 
   
   - Sellers can view a list of their sales by store, including customer information and revenues from the sale. 


3. Customers

   - Customers can view the overall marketplace listing products for sale, search for specific products using terms that match the name, store, or description, and sort the marketplace on price or quantity available. 

   - Customers can purchase items from the product page and review a history of their previously purchased items. 
    
4. Files

   - All file imports must occur as a prompt to enter the file path.
  
   - Sellers can import or export products for their stores using a csv file.
   
     	 a. All product details should be included, with one row per product. 
  
   - Customers can export a file with their purchase history.
  
5. Statistics

   - Sellers can view a dashboard that lists statistics for each of their stores.
   
     	 a.  Data will include a list of customers with the number of items that they have purchased and a list of products with the number of sales. 
     
     	 b.  Sellers can choose to sort the dashboard.
  
   - Customers can view a dashboard with store and seller information.
   
     	 a.  Data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer.
     
     	 b.  Customers can choose to sort the dashboard.
			 
6. Shopping Cart

	 - Customers can add products from different stores to a shopping cart to purchase all at once, and can remove any product if they choose to do so. The shopping cart is preserved between sessions, so a customer may choose to sign out and return to make the purchase later.  
	 
	 - Sellers can view the number of products currently in customer shopping carts, along with the store and details associated with the products. 



Sample outputs:

Sample 1 where the username exists for a buyer 

Welcome

1. I have an existing account

2. I would like to create an account 

[1]

Enter your username

[username]

welcome (username) 

What would you like to buy?

[keyword]

What is your price range?

[some double]

Do you have a store in mind?

1. Yes

2. No

[1]
Enter the store name

[store name]

Best matches for what you are looking for:
-
-
-
-

(choose an item)

Would you like to add this item to your cart?

1. Yes

2. no


[1]
Item added!

1. Keep browsing

2. checkout

[2]

Your total is (cost total of the items)

Are you ready to checkout?

1. Yes

2. no

[1]

(will have to discuss how we do this checking out thing)

Keep browsing

Log off

[2]

Thank you!


Sample 2 where the username exists for a seller

Welcome

I have an existing account

I would like to create an account

[1]

Enter your username

[username]

Welcome (username)

View buyer carts 

Add new product

[2]

What is the name of the product? 

(i was thinking if the scanner input here matches an existing product, it adds to that instead of having to ask if the product already exists)

How many of this product would you like to add?

[int]

 Action complete. 
1. Keep doing stuff
2. Log off
[2] 

Thank you!

