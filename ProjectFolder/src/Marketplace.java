import java.util.Scanner;
public class Marketplace {

    // Strings for Login and Account Creation
    private static String WELCOME = "Welcome to the Marketplace !";
    private static String USER = "Enter username";
    private static String NOSUCHUSER = "Username not found. Would you like to make a new account?";
    private static String ACCTYPE = """
Choose account type:
1. Seller
2. Buyer
""";
    private static String SETPASS = "Set password: ";
    private static String SETEMAIL = "Set email: ";
    private static String PASS = "Enter password";

    private static String WRONGPASS = "Wrong password !";

    // Strings for buyer menu
    private static String BUYMENU = """
1. Enter product search list
2. Show purchase history
3. Show remaining credit
""";
    private static String SEARCH = """
1. Show entire Store List
2. Search with 3. Show remaining credit
4. Back
""";
    // Strings for seller menu
    private static String SELLMENU = """
1. Edit existing product
2. Add new product
3. Show statistics
""";







    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        int response;
        do {
            response = scan.nextInt();
            scan.nextLine();
            switch (response) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please enter valid option !\n");
            }
        } while (response != 1 && response != 2 && response != 3);

    }
}