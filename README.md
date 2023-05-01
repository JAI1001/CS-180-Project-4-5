1) Compile all .java files. Run Server.java first and then MarketplaceClient.java to actually use the program.


2) Jane - Submitted Report on Brightspace. Jai - Submitted Vocareum workspace.


3) a) Server.java - This class contains the server side of the client server connection as well as the concurrency implementation. All array lists that are used in the project are also in this class. This class was where all the processing of the user interaction in the client class happened. 
   
   The server had several if statements to process actions. The action listeners were parsed into an integer, clientAction. When each actionlistener was clicked in the client side, the server side ensured it was going to the matching if statement, where the intended process was executed. 

   b) MarketplaceClient.java - This class contains every GUI as well as the action listeners that are used in the Server class. The creation of each frame and the user interaction was in this class. Each action listener is under methods labeled with a number, which matches the clientAction integers in the Server class. 

c) User.java – This class deals with the User objects which are used in the Server class, especially for the login and create new account processes.  

d) Product.java – This class deals with product objects, which are used when sellers create products or when buyers interact with them. 

e) UserNotFoundException.java – This class is an exception that can be thrown if a user is not found in any method where a user is expected to be found. It should be used with a string message that is common to the general Exception class (super.message). 



Notes: There are several System.out.printlns that are commented out in the Server class. These were created to show what was actually working and going through in the terminal as a method of testing. They are left in there to show our thought processes and can be uncommented to see what is being printed out in the terminal. 

