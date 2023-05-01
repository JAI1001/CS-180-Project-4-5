User test

Seller:
1. User starts the server.java
2. User starts MarketplaceClient.java
3. User clicks on the signup button
4. User enters username, password, email with string “sup” and chooses seller
5. User presses create account
6. User enters “supstore”
7. User clicks ok on store registration tab
8. User clicks exit button
9. User logs back in with entered username and password “sup”

Buyer:
1. User starts MarketplaceClient.java
2. User clicks on the signup button
3. User enters username, password, email with string “pop” and chooses buyer
4. User presses create account
5. User clicks exit button
6. User logs back in with entered username and password “pop”

Expected result: Application verifies the user's username and password and loads their homepage automatically for both buyer and seller. 
Test Status: Passed. 


Add product test

1. User logs in to account “sup”
2. User clicks on create button
3. Enter name “orange”, Price: “2”, quantity “5”, description “fresh oranges”
4. User clicks ok button
5. User clicks ok button
6. User checks to see if the product information shows in the terminal

Expected result: User will create an product  
Test Status: Passed. 


Edit product test

1. User logs in to account “sup”
2. User clicks on edit button
3. User enters “orange” as product name to edit
4. User enters name “orange”, Price “4”, Quantity “8”, description “fresh oranges”
5. User clicks ok button
6. User clicks ok button


Expected result: User will edit the product
Test Status: Passed.

See statistics test

1. User logs in to account “sup”
2. User clicks on statistics button

Expected result: User will see displayed statistics

Test Status: Passed?????????

Purchase product test
Shopping cart test

Buy shopping cart test

Test Status: Not passed

Empty shopping cart test

Test Status: Not passed
