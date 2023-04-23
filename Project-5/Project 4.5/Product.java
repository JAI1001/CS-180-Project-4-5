/**
 * Project-4 Marketplace
 *
 * <p>Purdue University -- CS18000 -- Spring 2023 -- Project-4</p>
 *
 * @version April 10, 2023
 *
 * @author: Seungjae Baik, Jane Billa, Thomas Birk, Kuan-Yu Chen, Jai Nanda
 */

public class Product {

    private String name;
    private double price;
    private int quantity;
    private int quantitySold;
    private String seller;
    private String storeName;
    private String description;


    //constructor - jane

    public Product(String name, double price, int quantity, int quantitySold, String seller, String storeName, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.seller = seller;
        this.storeName = storeName;
        this.description = description;


    }

    //getters - jane

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

    public String getDescription() {
        return description;
    }

    //setters - jane
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    //THOMAS - ADDING TOSTRING
    public String toString() { //puts class to string in same format as productList.txt file
        return String.format("%s,%d,%f,%d,%s,%s", name, quantity, price, quantitySold, seller, storeName);
    }

}
//product list extends this class
//description
