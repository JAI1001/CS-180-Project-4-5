public class User {  //user superclass for all users, will be extended for customers and sellers
    private String name;
    private int phoneNumber;
    private String emailAddress;
    private String password;
    private String deliveryAddress;

    public User(String name, int phoneNumber, String emailAddress, String password, String deliveryAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
    }

    //getters below
    public String getName() {
        return name;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    //setters below
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public boolean testPassword (String password) { //compare password argument to this password, return true if equal
        return (password.equals(this.password));
    }

    @Override
    public String toString() { //puts user class into readable string
        String s = String.format("Name: %s\nPhone Number: %d\nEmail Address: %s\nPassword: %s\nDelivery Address: %s\n",
                name, phoneNumber, emailAddress, password, deliveryAddress);
        return s;
    }

    @Override
    public boolean equals(Object o) { //returns true if passed argument is equal to this user object
        if (this == o) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        }
        User equalsTest = (User) o;
        return (this.name.equals(equalsTest.name)) && (this.phoneNumber == equalsTest.phoneNumber) && (this.emailAddress.equals(equalsTest.emailAddress))
                && (this.password.equals(equalsTest.password)) && (this.deliveryAddress.equals(equalsTest.deliveryAddress));
    }

}
