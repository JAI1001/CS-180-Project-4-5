public class Product {

    private String name;
    private double price;
    private int quantity;
    private int quantitySold;
    private String seller;
    private String storeName;


    //constructor

    public Product(String name, double price, int quantity, int quantitySold, String seller, String storeName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.seller = seller;
        this.storeName = storeName;


    }

    //getters

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public String getSeller() {
        return seller;
    }

    public String getStoreName() {
        return storeName;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    //THOMAS - ADDING TOSTRING
    public String toString() { //puts class to string in same format as productList.txt file
        return String.format("%s,%d,%f,%d,%s,%s", name, quantity, price, quantitySold, seller, storeName);
    }

}
//product list extends this class
//description