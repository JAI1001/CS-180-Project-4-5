public class Product {

    private String name;
    private double price;
    private int quantity;
    private int quantitySold;
    private String seller;
    private String storeName;

    public Product(String name, double price, int quantity, int quantitySold, String seller, String storeName){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.seller = seller;
        this.storeName = storeName;



    }
    //getters setters for name, price, quantity, quantity sold, seller, store name

    //product list extends this class
    //description
