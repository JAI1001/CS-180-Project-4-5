public class Seller extends User {
    private String[] stores;
    public Seller(String name, int phoneNumber, String emailAddress, String password, String deliveryAddress, String[] stores) {
        super(name, phoneNumber, emailAddress, password, deliveryAddress);
        this.stores = stores;
    }

    public String[] getStores() {
        return stores;
    }
    public void setStores(String[] stores) {
        this.stores = stores;
    }

}
