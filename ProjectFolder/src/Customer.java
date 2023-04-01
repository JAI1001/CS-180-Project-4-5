public class Customer extends User {
    private double currency;

    public Customer(String name, int phoneNumber, String emailAddress, String password, String deliveryAddress, double currency) {
        super(name, phoneNumber, emailAddress, password, deliveryAddress);
        this.currency = currency;
    }

    public double getCurrency() {
        return currency;
    }
    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public void addCurrency(double amount) {
        this.currency = currency + amount;
    }
    public void subtractCurrency(double amount) {
        this.currency = currency - amount;
    }
}
