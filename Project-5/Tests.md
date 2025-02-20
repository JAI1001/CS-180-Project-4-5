**User test**

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


**Add product test**

1. User logs in to account “sup”
2. User clicks on create button
3. Enter name “orange”, Price: “2”, quantity “5”, description “fresh oranges”
4. User clicks ok button
5. User clicks ok button
6. User checks to see if the product information shows in the terminal

Expected result: User will create an product

Test Status: Passed.


**Edit product test**

1. User logs in to account “sup”
2. User clicks on edit button
3. User enters “orange” as product name to edit
4. User enters name “orange”, Price “4”, Quantity “8”, description “fresh oranges”
5. User clicks ok button
6. User clicks ok button

Expected result: User will edit the product

Test Status: Passed.


**See statistics test**

1. User logs in to account “sup”
2. User clicks on statistics button

Expected result: User will see displayed statistics

Test Status: Not passed


**Purchase product test**


**Search product test**

1. User starts the server.java
2. User starts MarketplaceClient.java
3. User clicks on the signup button
4. User enters username, password, email with string “sup” and chooses seller
5. User presses create account
6. User enters “supstore”
7. User clicks ok on store registration tab
8. User clicks exit button
9. User logs back in with entered username and password “sup”
10. User clicks the create button
11. Enter name “orange”, Price: “2”, quantity “5”, description “fresh oranges”
12. User clicks ok button
13. User clicks ok button
14. User checks to see if the product information shows in the terminal
15. User clicks exit button
16. User starts MarketplaceClient.java
17. User clicks on the signup button
18. User enters username, password, email with string “pop” and chooses buyer
19. User presses create account
20. User clicks exit button
21. User logs back in with entered username and password “pop”
22. User clicks Search button
23. User clicks show entire product list button
24. User selects orange
25. User clicks the ok button
26. User clicks the add product button

Action stopped because we can not view the products in the cart

Expected result: User will purchase product and purchase will be recorded in purchase history

Test Status: not passed


**Shopping cart test**

**Buy shopping cart test**

Action stopped because we can not view the products in the cart

Expected result: User will purchase items in cart and record purchase history

Test Status: Not passed


**Empty shopping cart test**

Action stopped because we can not view the products in the cart

Expected result: User will empty items in cart and re add the items in the product list

Test Status: Not passed

